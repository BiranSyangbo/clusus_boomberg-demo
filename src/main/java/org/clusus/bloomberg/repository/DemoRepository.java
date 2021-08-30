package org.clusus.bloomberg.repository;

import java.util.UUID;

import org.clusus.bloomberg.model.RequestDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<RequestDemoEntity, UUID> {

}
