package org.clusus.bloomberg.service;

import org.clusus.bloomberg.model.RequestDemoEntity;

import java.util.List;
import java.util.Optional;

public interface DemoService {

    public Optional<RequestDemoEntity> saveRequestDemo(RequestDemoEntity requestDemoEntity);

    public boolean delete(Long id);

    public List<RequestDemoEntity> getRequestList();

    public Optional<RequestDemoEntity> getRequestById(Long id);
}
