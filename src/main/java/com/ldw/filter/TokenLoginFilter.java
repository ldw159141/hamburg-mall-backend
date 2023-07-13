package com.ldw.filter;

import com.alibaba.fastjson.JSON;
import com.ldw.entity.User;
import com.ldw.service.RoleService;
import com.ldw.service.UserService;
import com.ldw.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HP刘德伟
 */


public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter{


    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;



    protected AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    /**
     * 具体认证方法
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //这里是数据库实体类User
        User user=new User();
        //前后端分离项目提交的是json字符串,不是表单提交
        try {
            String loginInfo =getRequestJSON(request);
            user = JSON.parseObject(loginInfo, User.class);//转换成user对象

            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
          return   authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String getRequestJSON(HttpServletRequest request) throws IOException {

        BufferedReader streamReader=new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb=new StringBuilder();
        String inputStr = null;
        while ((inputStr = streamReader.readLine()) !=null){
            sb.append(inputStr);
        }
        return sb.toString();
    }

    /**
     * 登陆成功的方法
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain
            , Authentication authResult) throws IOException, ServletException {
//        String username=authResult.getName().toString();
//        User user = userService.selectByUsername(username);
//        Role role=roleService.selectByUserRoleId(user.getRoleId());
//        List<GrantedAuthority> listRole = new ArrayList<>();
//        listRole.add(new SimpleGrantedAuthority(role.getName()));
      //  System.out.println(authResult.getAuthorities().toString());

        //生成Token信息
        Map<String,Object> map=new HashMap<>();
        map.put("username",authResult.getName());
        map.put("listRole",authResult.getAuthorities().toString());
        //TODO 还可以在这加角色,先不加
        String token=JwtUtils.getTokenByMap(map);
        //把生产的token信息返回给客户端
        response.addHeader("Authorization","ldw"+token);
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer= response.getWriter();
        Map<String,Object> result=new HashMap<>();
        result.put("code",200);
        result.put("msg","登录成功");
        result.put("username",authResult.getName());
        result.put("token","ldw"+token);
        writer.write(JSON.toJSONString(result));
        writer.flush();
        writer.close();

    }

    /**
     * 登录失败的方法
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer= response.getWriter();
        Map<String,Object> result=new HashMap<>();
        result.put("code",401);
        result.put("msg","用户名或密码错误");
        writer.write(JSON.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
