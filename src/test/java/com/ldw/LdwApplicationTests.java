package com.ldw;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ldw.dto.UserLoginDTO;
import com.ldw.entity.User;
import com.ldw.mapper.UserMapper;
import com.ldw.service.UserService;
import com.ldw.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class LdwApplicationTests {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
	}

	/**
	 * jwt验证测试
	 */
	@Test
	void testJWT() {
		User user = new User();
		//user.setId(1);
		user.setUsername("ldw");
		user.setPassword("ldw");
		System.out.println(JwtUtils.generateTokenByUser(user));
		//	String token="eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6ImxkdyIsInJvbGVJZCI6bnVsbCwiaWQiOjEsImV4cCI6MTY4MjUwMTA2MCwiaWF0IjoxNjgyNDk4NDY4LCJqdGkiOiJ0b2tlbklkIiwidXNlcm5hbWUiOiJsZHcifQ.F8oxFfNg2BeoheaV23Xlv_sCd68rhDY97Tnjr55-WU8";
		//	JwtUtils.verifyJwt(token);
	}

	@Test
	public void login() {
		User user=new User();
		user.setUsername("ldw");
		user.setPassword("ldw");
//		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//		wrapper.eq(User::getUsername, user.getUsername())
//				.eq(User::getPassword, user.getPassword()).last("limit 1");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername()).eq("password", user.getPassword());

		User userInfo = this.userMapper.selectOne(wrapper);
		System.out.println(userInfo);

	}
	@Test
	public void login2() {
		UserLoginDTO user=new UserLoginDTO();
		user.setUsername("ldw");
		user.setPassword("ldw");
//		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//		wrapper.eq(User::getUsername, user.getUsername())
//				.eq(User::getPassword, user.getPassword()).last("limit 1");
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", user.getUsername()).eq("password", user.getPassword());

		User userInfo = this.userMapper.selectOne(wrapper);
		System.out.println(userInfo);
		if (userInfo!=null){
			String token=JwtUtils.generateTokenByUser(userInfo);
			System.out.println(token);
			HashMap<Object, Object> map = new HashMap<>();
			map.put("token",token);
			map.put("username",userInfo.getUsername());
			map.put("roleId",userInfo.getRoleId());
			System.out.println(map);
		}else {
			System.out.println("账号密码错误");
		}


	}
}