package com.ldw.filter;

import com.alibaba.fastjson.JSON;
import com.ldw.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HP刘德伟
 *根据传过来的token，解析
 */

public class TokenVerifyFilter extends BasicAuthenticationFilter {

//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.getAuthenticationManager();
//    }
//
//


    public TokenVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

//        super(authenticationManager);
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
            String username=claims.get("username").toString();
            System.out.println(claims.get("listRole"));

            //    System.out.println(username);
            //    String username = (String) claims.get("username");
            //TODO 放过请求 后续操作要权限
            //这里没办法通过service获取user对象
//            User user = userService.selectByUsername(username);
//            System.out.println(user);
//                Role role=roleService.selectByUserRoleId(user.getRoleId());
                List<GrantedAuthority> listRole = new ArrayList<>();
                String str =(String) claims.get("listRole");
                //去掉第一个和最后一个字符的括号【】
            String str1 = str.substring(1, str.length() - 1);
                listRole.add(new SimpleGrantedAuthority(str1));
            System.out.println(listRole);
            //TODO 根须账号获取相关权限
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,  "",listRole);
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
        result.put("msg","权限不足");
        writer.write(JSON.toJSONString(result));
        writer.flush();
        writer.close();
    }

}
