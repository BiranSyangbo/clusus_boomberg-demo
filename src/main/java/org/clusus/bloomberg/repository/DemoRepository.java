package org.clusus.bloomberg.repository;

import java.util.List;
import java.util.Optional;

import org.clusus.bloomberg.model.RequestDemoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<RequestDemoEntity, Long> {

}
