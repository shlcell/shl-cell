package com.shl.demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 处理IP地址工具
 */
public class IpAddrUtil {
    private static final Log logger = LogFactory.getLog(IpAddrUtil.class);
    private static final String HEAD_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEAD_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HEAD_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String IP_UNKNOWN = "unknown";
    private static final String IP_V4_LOCAL = "127.0.0.1";
    private static final String IP_V6_LOCAL = "0:0:0:0:0:0:0:1";
    private static final String IP_SEPARATOR = ",";

    public static String getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        logger.debug("攻击者headers为：" + headers.toString());
        String ip = headers.getFirst(HEAD_X_FORWARDED_FOR);
        if (!isInvalidIpAddr(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (isInvalidIpAddr(ip)) {
            ip = headers.getFirst(HEAD_PROXY_CLIENT_IP);
        }
        if (isInvalidIpAddr(ip)) {
            ip = headers.getFirst(HEAD_WL_PROXY_CLIENT_IP);
        }
        if (isInvalidIpAddr(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (isInvalidIpAddr(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (isInvalidIpAddr(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (isInvalidIpAddr(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return ip;
    }


    /**
     * 获取客户端真实ip
     *
     * @param request Http请求
     * @return 真实ip
     */
    public static String getForwardedIpAddrFromHttpRequest(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        logger.debug("headers为：" + headers.toString());
        String ipAddress = headers.getFirst(HEAD_X_FORWARDED_FOR);
        return ipAddress;
    }

    /**
     * 获取客户端真实ip
     *
     * @param request Http请求
     * @return 真实ip
     */
    public static String getClientIpAddrFromHttpRequest(HttpServletRequest request) {
        String ipAddress = request.getHeader(HEAD_X_FORWARDED_FOR);
        if (isInvalidIpAddr(ipAddress)) {
            ipAddress = request.getHeader(HEAD_PROXY_CLIENT_IP);
        }
        if (isInvalidIpAddr(ipAddress)) {
            ipAddress = request.getHeader(HEAD_WL_PROXY_CLIENT_IP);
        }
        if (isInvalidIpAddr(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            ipAddress = resolveLocalHostIp(ipAddress);
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.contains(IP_SEPARATOR)) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(IP_SEPARATOR));
        }
        return ipAddress;
    }

    private static boolean isInvalidIpAddr(String ipAddress) {
        return StringUtils.isEmpty(ipAddress) || IP_UNKNOWN.equalsIgnoreCase(ipAddress);
    }

    public static String getIpAddressFromServerRequest(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        logger.debug("headers为：" + headers.toString());
        String ipAddress = headers.getFirst(HEAD_X_FORWARDED_FOR);
        logger.debug("x-forwarded-for代理ip为:" + ipAddress);
        if (isInvalidIpAddr(ipAddress)) {
            ipAddress = headers.getFirst(HEAD_PROXY_CLIENT_IP);
            logger.debug("Proxy-Client-IP代理ip为:" + ipAddress);
        }
        if (isInvalidIpAddr(ipAddress)) {
            ipAddress = headers.getFirst(HEAD_WL_PROXY_CLIENT_IP);
            logger.debug("WL-Proxy-Client-IPip为:" + ipAddress);
        }
        if (isInvalidIpAddr(ipAddress)) {
            ipAddress = request.getRemoteAddress() == null ? "" : request.getRemoteAddress().getAddress().getHostAddress();
            ipAddress = resolveLocalHostIp(ipAddress);
            logger.debug("主机ip为:" + ipAddress);
        }

        if (ipAddress != null && ipAddress.contains(IP_SEPARATOR)) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(IP_SEPARATOR));
        }
        return ipAddress;
    }

    private static String resolveLocalHostIp(String ipAddress) {
        if (IP_V4_LOCAL.equals(ipAddress) || IP_V6_LOCAL.equals(ipAddress)) {
            //根据网卡取本机配置的IP
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                logger.warn("解析本机IP异常");
            }
            if (null != inetAddress) {
                ipAddress = inetAddress.getHostAddress();
            }
        }
        return ipAddress;
    }
}
