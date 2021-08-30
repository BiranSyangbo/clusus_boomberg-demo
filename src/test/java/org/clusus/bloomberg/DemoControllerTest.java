package org.clusus.bloomberg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clusus.bloomberg.controller.DemoController;
import org.clusus.bloomberg.model.RequestDemoEntity;
import org.clusus.bloomberg.repository.DemoRepository;
import org.clusus.bloomberg.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {DemoController.class})
class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    DemoService demoService;

    @MockBean
    DemoRepository demoRepository;

    @Test
    public void sendBad_request_return_400BadRequest() throws Exception {

        RequestDemoEntity entity = new RequestDemoEntity();
        entity.setToCurrency("NPR");
        entity.setAmount(134d);
        when(demoService.saveRequestDemo(entity)).thenReturn(Optional.of(entity));

        MockHttpServletRequestBuilder builder = post("/clusus/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(entity));

        mvc.perform(builder).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$",notNullValue()));
    }

    @Test
    public void saveRequestDemo_return201_Created() throws Exception {

        RequestDemoEntity entity = new RequestDemoEntity();
        entity.setToCurrency("NPR");
        entity.setAmount(134d);
        entity.setFromCurrency("EUR");
        when(demoService.saveRequestDemo(entity)).thenReturn(Optional.of(entity));

        MockHttpServletRequestBuilder builder = post("/clusus/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(entity));

        mvc.perform(builder).andExpect(status().isCreated())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$",containsString("Request Save Successfully")));
    }

    @Test
    public void return_bad_requestIf_saveFail() throws Exception {

        RequestDemoEntity entity = new RequestDemoEntity();
        entity.setToCurrency("NPR");
        entity.setAmount(134d);
        entity.setFromCurrency("EUR");
        when(demoService.saveRequestDemo(entity)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = post("/clusus/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(entity));
        mvc.perform(builder).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$",containsString("Issue")));
    }

    @Test
    public void delete_function_200_status() throws Exception {
        when(demoService.delete(1l)).thenReturn(true);
        MockHttpServletRequestBuilder delete = delete("/clusus/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(delete).andExpect(status().isOk())
        .andExpect(jsonPath("$",is("Delete Successfully")));
    }

    @Test
    public void delete_function_204_status() throws Exception {
        when(demoService.delete(1l)).thenReturn(false);
        MockHttpServletRequestBuilder delete = delete("/clusus/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(delete).andExpect(status().isNoContent())
                .andExpect(jsonPath("$",is("Not exists")));
    }

    @Test
    public void getById_200_status() throws Exception {
        RequestDemoEntity entity = new RequestDemoEntity();
        entity.setToCurrency("NPR");
        entity.setAmount(134d);
        entity.setFromCurrency("EUR");
        entity.setCreatedDate(new Date());
        entity.setId(1l);

        when(demoService.getRequestById(1l)).thenReturn(Optional.of(entity));

        MockHttpServletRequestBuilder get = get("/clusus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(get).andExpect(status().isOk())
                .andExpect(jsonPath("$.toCurrency",is(entity.getToCurrency())))
                .andExpect(jsonPath("$.fromCurrency",is(entity.getFromCurrency())));

    }

    @Test
    public void getById_204_status() throws Exception {

        when(demoService.getRequestById(1l)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder get = get("/clusus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(get).andExpect(status().isNoContent());

    }

    @Test
    public void getAll_200_status() throws Exception {

        RequestDemoEntity entity = new RequestDemoEntity();
        entity.setToCurrency("NPR");
        entity.setAmount(134d);
        entity.setFromCurrency("EUR");
        entity.setCreatedDate(new Date());
        entity.setId(1l);
        RequestDemoEntity entity1 = new RequestDemoEntity();
        entity1.setToCurrency("DZD");
        entity1.setAmount(134d);
        entity1.setFromCurrency("USD");
        entity1.setCreatedDate(new Date());
        entity1.setId(1l);

        List<RequestDemoEntity> requestDemoEntities = Arrays.asList(entity, entity1);

        when(demoService.getRequestList()).thenReturn(requestDemoEntities);

        MockHttpServletRequestBuilder get = get("/clusus/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(get).andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].toCurrency",is(entity.getToCurrency())))
        .andExpect(jsonPath("$.[1].toCurrency",is(entity1.getToCurrency())));

    }

    @Test
    public void getAll_204_status() throws Exception {

        when(demoService.getRequestList()).thenReturn(Collections.emptyList());

        MockHttpServletRequestBuilder get = get("/clusus/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(get).andExpect(status().isNoContent());

    }


}
