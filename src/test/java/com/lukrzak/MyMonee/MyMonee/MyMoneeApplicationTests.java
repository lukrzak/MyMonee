package com.lukrzak.MyMonee.MyMonee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukrzak.MyMonee.MyMonee.models.User;
import com.lukrzak.MyMonee.MyMonee.repositories.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MyMoneeApplicationTests {
	private MockMvc mvc;
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private WebApplicationContext webApplicationContext;


	@BeforeEach
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	void contextLoads() {
	}

	@Test
	void addUserShouldBeIsCreatedStatus() throws Exception {
		String jsonRequest = new JSONObject()
				.put("name", "test2")
				.put("surname", "testt")
				.put("balance", 100.13f)
				.toString();

		this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isCreated());
	}

	@Test
	void changeUserBalanceShouldBeOkStatus() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User(1L, "test", "testt", 100.13f);
		String userRequest = objectMapper.writeValueAsString(user);

		String jsonRequest = new JSONObject()
				.put("user", new JSONObject()
						.put("id", 1)
						.put("name", "test")
						.put("surname", "ttest")
						.put("balance", 100.13))
				.put("balance", 10.40)
				.toString();

		MvcResult res= this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(userRequest))
				.andExpect(status().isCreated())
				.andDo(result -> MockMvcRequestBuilders.post("/api/v1/users/change-balance").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andReturn();

	}

}
