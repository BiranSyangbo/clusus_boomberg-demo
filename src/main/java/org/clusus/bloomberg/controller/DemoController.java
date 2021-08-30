package org.clusus.bloomberg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.clusus.bloomberg.model.RequestDemoEntity;
import org.clusus.bloomberg.service.DemoService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("clusus")
@RequiredArgsConstructor
@Slf4j
public class DemoController {

    private final DemoService demoService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveRequestInfo(@Valid @RequestBody RequestDemoEntity requestDemoEntity) {
        log.debug("The Request Object is ==> " + requestDemoEntity.toString());
        log.warn("The Post Request");
        return demoService.saveRequestDemo(requestDemoEntity).isPresent()
                ? new ResponseEntity<>("Request Save Successfully", HttpStatus.CREATED)
                : new ResponseEntity<>("Issue", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRequestInfo(@PathVariable("id") Long id) {
        log.warn("Delete Request with Id "+ id);
        return demoService.delete(id)
                ? new ResponseEntity<>("Delete Successfully", HttpStatus.OK)
                : new ResponseEntity<>("Not exists", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.warn("Get Object By Id "+id);
        Optional<RequestDemoEntity> requestById = demoService.getRequestById(id);
        return requestById.isPresent()
                ? new ResponseEntity<>(requestById.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        log.warn("Get all Object Request");
        List<RequestDemoEntity> requestList = demoService.getRequestList();
        return CollectionUtils.isEmpty(requestList)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(requestList, HttpStatus.OK);
    }

}
