package org.clusus.bloomberg;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

import org.clusus.bloomberg.controller.DemoController;
import org.clusus.bloomberg.model.RequestDemoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = { DemoController.class })
class ApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	ObjectMapper mapper;

	@Test
	public void save_requestde_testOkStatus() throws Exception {

		RequestDemoEntity entity = RequestDemoEntity.builder().createdDate(new Date())
				.toCurrency("NPR").amount(134d).build();

		// Build post request with vehicle object payload
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/clusus/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(entity));

		mvc.perform(builder).andExpect(status().isCreated());
	}

}
