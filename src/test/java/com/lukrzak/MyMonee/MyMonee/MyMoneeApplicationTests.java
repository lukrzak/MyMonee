package com.lukrzak.MyMonee.MyMonee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukrzak.MyMonee.MyMonee.models.User;
import com.lukrzak.MyMonee.MyMonee.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MyMoneeApplicationTests {
	private MockMvc mvc;
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private WebApplicationContext webApplicationContext;
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	void contextLoads() {
	}

	@Test
	void addUserShouldBeIsCreatedStatus() throws Exception {
		User user = new User(1L, "test", "testt", 100.13f);
		String jsonRequest = user.toString();
		System.out.println(jsonRequest);
		this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isOk());
	}

}
