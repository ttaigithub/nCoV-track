package fun.guan.config;

import com.google.gson.Gson;
import fun.guan.pojo.ResponseResult;
import fun.guan.pojo.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录过滤器
 *
 * @author Jankin
 * @create 2018-12-27 17:02
 */
@Component
@WebFilter(urlPatterns = "/", filterName = "authFilter")
@Slf4j
public class AuthFilterToken extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
        if (isProtectedUrl(httpServletRequest)) {
            // 允许请求头带token
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "token");
            //token有效放行
            String token = httpServletRequest.getHeader(SysConstant.TOKEN);
            if (token != null) {
                System.out.println("处理一下token");
            } else {
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(new Gson().toJson(new ResponseResult(ResultEnum.TOKEN_INVALID)));
                return;
            }
        }
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 我们只对地址 /api 开头的api检查jwt. 不然的话登录/login也需要jwt
     */
    private boolean isProtectedUrl(HttpServletRequest request) {
        return (pathMatcher.match("/index/**", request.getServletPath()) ||
                pathMatcher.match("/evi/**", request.getServletPath())||
                pathMatcher.match("/errorCode/**",request.getServletPath()));
    }

}
