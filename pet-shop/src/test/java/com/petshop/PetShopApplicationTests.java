package com.petshop;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.petshop.repository.IPetRepository;
import com.petshop.repository.IUserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
class PetShopApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	@MockBean
	IPetRepository petRepository;

	@Test
	void contextLoads() throws Exception {
		when(petRepository.findAll()).thenReturn(Collections.emptyList());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/all/pets").accept(MediaType.APPLICATION_JSON))
				.andReturn();

		System.out.println(mvcResult.getResponse());

		Mockito.verify(petRepository).findAll();
	}

}
