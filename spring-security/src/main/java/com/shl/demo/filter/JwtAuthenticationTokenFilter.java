package com.shl.demo.filter;

import com.shl.demo.domain.LoginUser;
import com.shl.demo.mapper.MenuMapper;
import com.shl.demo.mapper.UserMapper;
import com.shl.demo.utils.JwtUtil;
import com.shl.demo.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final static Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");

        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid = null;
//        try {
//            Claims claims = JwtUtil.parseJWT(token);
//            userid = claims.getSubject();
//        } catch (Exception e) {
//            log.info(e.getMessage());
//            throw new RuntimeException("token非法");
//        }
//        //从redis中获取用户信息
//        String redisKey = "login:" + userid;
//        LoginUser loginUser = redisCache.getCacheObject(redisKey);
//        if(Objects.isNull(loginUser)){
//            throw new RuntimeException("用户未登录");
//        }
        try {
            userid = JwtUtil.parseJWT(token).getSubject();

        } catch (Exception ignored) {
        }
        if(Objects.isNull(userid)){
            filterChain.doFilter(request, response);
            return;
        }


//        //根据用户名查询用户信息
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getId, userid);
//        User user = userMapper.selectOne(wrapper);
//        //如果查询不到数据就通过抛出异常来给出提示
//        if (Objects.isNull(user)) {
//            filterChain.doFilter(request, response);
////            throw new RuntimeException("鉴权失败");
//            return;
//        }
//        List<String> list = menuMapper.selectPermsByUserId(Long.valueOf(userid));
//        LoginUser loginUser = new LoginUser(
//                user.getId(), user.getUserName(), user.getNickName(), user.getPassword(),
//                user.getStatus(), user.getEmail(), user.getPhonenumber(), user.getSex(), user.getAvatar(),
//                user.getUserType(), user.getCreateBy(), user.getCreateTime(), user.getUpdateBy(), user.getUpdateTime(),
//                user.getDelFlag(), list);
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        //放行
//        filterChain.doFilter(request, response);


        LoginUser loginUser = redisCache.getCacheObject("login:" + userid);
        if (null != userid && !Objects.isNull(loginUser)) {
            //存入SecurityContextHolder
            //TODO 获取权限信息封装到Authentication中
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //放行
            filterChain.doFilter(request, response);
        }else {
            filterChain.doFilter(request, response);
            return;
        }
    }
}