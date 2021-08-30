package org.clusus.bloomberg;

import org.clusus.bloomberg.exception.EntityAlreadyExist;
import org.clusus.bloomberg.model.RequestDemoEntity;
import org.clusus.bloomberg.repository.DemoRepository;
import org.clusus.bloomberg.service.DemoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DemoServiceTest {

    @Mock
    DemoRepository demoRepository;

    @InjectMocks
    DemoServiceImpl demoService;

    @Test()
    void test_AlreadyExist_Exception(){
        RequestDemoEntity entity = new RequestDemoEntity();
        entity.setToCurrency("NPR");
        entity.setAmount(134d);
        entity.setFromCurrency("EUR");
        entity.setId(1l);

        when(demoRepository.existsById(entity.getId())).thenReturn(true);
        assertThrows(EntityAlreadyExist.class, () -> {
            demoService.saveRequestDemo(entity);
        });
    }

    @Test()
    void save_Request_object(){
        RequestDemoEntity entity = new RequestDemoEntity();
        entity.setToCurrency("NPR");
        entity.setAmount(134d);
        entity.setFromCurrency("EUR");
        entity.setId(1l);

        when(demoRepository.existsById(entity.getId())).thenReturn(false);
        when(demoRepository.save(entity)).thenReturn(entity);
        Optional<RequestDemoEntity> requestDemoEntity = demoService.saveRequestDemo(entity);
        assertTrue(requestDemoEntity.isPresent());
        assertEquals(entity.getFromCurrency(),requestDemoEntity.get().getFromCurrency());
        assertEquals(entity.getToCurrency(),requestDemoEntity.get().getToCurrency());
        assertNotNull(requestDemoEntity.get().getCreatedDate());
    }
}
