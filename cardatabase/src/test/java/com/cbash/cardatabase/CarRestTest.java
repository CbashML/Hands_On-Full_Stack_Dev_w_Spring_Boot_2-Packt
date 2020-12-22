package com.cbash.cardatabase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarRestTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testAuthentication() 
			throws Exception {

		// Testing authentication with correct credentials
		testAuthenticationOk("user", "user");
		
		// Testing authentication with wrong credentials.
		testAuthentication4xxClientError("user", "ådmin");
	}

	@Test
	public void testAuthenticationOk() 
			throws Exception {
		testAuthenticationOk("admin", "admin");
	}
	
	@Test
	public void testAuthentication4xxClientError() 
			throws Exception {
		testAuthentication4xxClientError("admin", "ådmin");
	}
	
	private void testAuthenticationOk(String username, String password) 
			throws Exception {
		
		String content = getContent(username, password);
		ResultMatcher status = status().isOk();
		
		mockMVCTest("POST", "/login", content, status);
	}
	
	private void testAuthentication4xxClientError(String username, String password) 
			throws Exception {
		
		String content = getContent(username, password);
		ResultMatcher status = status().is4xxClientError();
		
		mockMVCTest("POST", "/login", content, status);
	}


	private void mockMVCTest(String method, String path, String content, ResultMatcher status) 
			throws Exception {
		
		this.mockMvc.perform(
				post(path).content(content)
			)
				.andDo(print())

				.andExpect(status);
	}

	private String getContent(String username, String password) {
		String content = 
				"{"
				+ "\"username\":" +  "\"" + username + "\"" + ", "
				+ "\"password\":" +  "\"" + password + "\""
				+ "}";
		
		return content;
	}

}
