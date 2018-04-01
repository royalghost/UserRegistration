/**
 * 
 */
package com.user.registration.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * @author prabinpaudel
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonRegistrationTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void validatePersonRegistrationWhenBorthUserNameAndPasswordIsNull() throws Exception {
		MockHttpServletRequestBuilder registerPerson = post("/").param("userName", "").param("password", "");
		mockMvc.perform(registerPerson).andExpect(model().hasErrors());
	}
	
	@Test
	public void validatePersonRegistrationWhenSuccess() throws Exception {
		MockHttpServletRequestBuilder registerPerson = post("/").param("userName", "foobar").param("password", "FooBar10");
		mockMvc.perform(registerPerson).andExpect(model().hasNoErrors());
	}

}
