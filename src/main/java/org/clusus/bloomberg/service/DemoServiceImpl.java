package org.clusus.bloomberg.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.clusus.bloomberg.exception.EntityAlreadyExist;
import org.clusus.bloomberg.model.RequestDemoEntity;
import org.clusus.bloomberg.repository.DemoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;

    @Override
    public Optional<RequestDemoEntity> saveRequestDemo(RequestDemoEntity requestDemoEntity) {
        if (requestDemoEntity.getId() != null && demoRepository.existsById(requestDemoEntity.getId())) {
            log.warn("Object Already Exist with given id"+ requestDemoEntity.getId());
            throw new EntityAlreadyExist("Request Demo Entity Already Exist By Id:- " + requestDemoEntity.getId());
        } else {
            requestDemoEntity.setCreatedDate(new Date());
            log.info("Set the created date to track the date of request");
            return Optional.of(demoRepository.save(requestDemoEntity));
        }
    }

    @Override
    public boolean delete(Long id) {
        if (demoRepository.existsById(id)) {
            log.info("Object Exist By request Id "+id);
            demoRepository.deleteById(id);
            return true;
        } else {
            log.info("Object Not Found By request Id "+id);
            return false;
        }
    }

    @Override
    public List<RequestDemoEntity> getRequestList() {
        log.info("Get All Request");
        return demoRepository.findAll();
    }

    @Override
    public Optional<RequestDemoEntity> getRequestById(Long id) {
        log.info("Get By Id Request");
        return demoRepository.findById(id);
    }
}
