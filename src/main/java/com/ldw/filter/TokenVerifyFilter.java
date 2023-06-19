package com.ldw.filter;

import com.alibaba.fastjson.JSON;
import com.ldw.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HP刘德伟
 *根据传过来的token，解析
 */
public class TokenVerifyFilter extends BasicAuthenticationFilter {

    public TokenVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }



    /**
     * 校验提交的token是否合法
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取请求携带的token信息
        String header=request.getHeader("Authorization");

        //携带token信息并且前面带有我设置的ldw
        if (header != null && header.startsWith("ldw")){
            //1.获取真正的token信息
            String token=header.replace("ldw","");
            //校验token是否合法
            Claims claims = JwtUtils.verifyJwt(token);

            //如果校验失败
            if (claims==null){
                responseLogin(response);
            }


            //合法就获取登录账号的username
            String username = (String) claims.get("username");
            //TODO 放过请求 后续操作要权限
            //TODO 根须账号获取相关权限
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,"",null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
            chain.doFilter(request,response);
        }else {
            //没有携带token或者非法请求
            responseLogin(response);
        }

    }

    private static void responseLogin(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer= response.getWriter();
        Map<String,Object> result=new HashMap<>();
        result.put("code",HttpServletResponse.SC_FORBIDDEN);
        result.put("msg","请先登录");
        writer.write(JSON.toJSONString(result));
        writer.flush();
        writer.close();
    }

}