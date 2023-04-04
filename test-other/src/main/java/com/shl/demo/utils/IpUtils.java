package com.shl.demo.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * CDN
 * CDN是内容分发网络(Content Delivery Network)的缩写，是由一系列互联网服务器构成的网络，位于世界各地。
 * CDN的主要作用是将内容缓存到离用户近的服务器上，以达到更快、更稳定的访问速度。CDN通过在全球范围内部署节点，使得用户可以就近获取所需要的数据，提高网站的响应速度和用户体验。
 * <p>
 * WAF
 * WAF是Web应用程序防火墙(Web Application Firewall)的缩写，是一种特殊的应用程序防火墙。
 * 它通过过滤和监控Web应用程序和Internet之间的HTTP流量，保护Web应用程序免受常见攻击（如跨站脚本攻击、SQL注入等）的影响。
 * WAF通过对传入的HTTP请求进行检查，并且根据规则来阻止不符合安全策略的请求，保护Web应用程序的安全。
 * <p>
 * 高防
 * 高防是一种专门用于网络安全防御服务的产品，通常包括DDoS防护、Web应用程序防护等多种技术手段。
 * 高防通常被用于保护互联网应用、服务器等重要资产，防御各种恶意攻击，确保网络运行的稳定性和安全性。
 * 高防采用的技术手段包括黑白名单、流量清洗、限速、DDoS攻击识别等。
 * <p>
 */
public class IpUtils {

    /**
     * 1 获取用户请求对象HttpServletRequest
     * 2 从请求对象中获取用户的IP地址。可以通过HttpServletRequest对象的getHeader(String name)方法获取HTTP头中的数据，其中包含了客户端的IP地址。
     * 3 但是经过代理后，HTTP头中可能会出现多个IP地址，其中最后一个非unknown的IP地址为客户端的真实IP地址。
     */
    public static String getRealIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isEmpty()) {
            String[] ips = forwardedFor.split(",");
            for (String ip : ips) {
                if (!ip.toLowerCase().contains("unknown")) {
                    return ip.trim();
                }
            }
        }
        return remoteAddr;
    }


    /***
     * 获取客户端ip地址
     */
    public static String getIP(final HttpServletRequest request) throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipStr = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
            ipStr = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipStr.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipStr = str;
                break;
            }
        }
        //目的是将localhost访问对应的ip 0:0:0:0:0:0:0:1 转成 127.0.0.1。
        return ipStr.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ipStr;
    }
}
