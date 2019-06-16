package me.zhengjie.modules.performance.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.performance.domain.PerformanceDataTrashcan;
import me.zhengjie.modules.performance.service.PerformanceDataTrashcanService;
import me.zhengjie.modules.performance.service.dto.PerformanceDataTrashcanQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author cp
 * @date 2019-06-16
 */
@RestController
@RequestMapping("api")
public class PerformanceDataTrashcanController {

    @Autowired
    private PerformanceDataTrashcanService performanceDataTrashcanService;

    @Log("查询PerformanceDataTrashcan")
    @GetMapping(value = "/performanceDataTrashcan")
    @PreAuthorize("hasAnyRole('ADMIN','PERFORMANCEDATATRASHCAN_ALL','PERFORMANCEDATATRASHCAN_SELECT')")
    public ResponseEntity getPerformanceDataTrashcans(PerformanceDataTrashcanQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(performanceDataTrashcanService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增PerformanceDataTrashcan")
    @PostMapping(value = "/performanceDataTrashcan")
    @PreAuthorize("hasAnyRole('ADMIN','PERFORMANCEDATATRASHCAN_ALL','PERFORMANCEDATATRASHCAN_CREATE')")
    public ResponseEntity create(@Validated @RequestBody PerformanceDataTrashcan resources) {
        return new ResponseEntity(performanceDataTrashcanService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改PerformanceDataTrashcan")
    @PutMapping(value = "/performanceDataTrashcan")
    @PreAuthorize("hasAnyRole('ADMIN','PERFORMANCEDATATRASHCAN_ALL','PERFORMANCEDATATRASHCAN_EDIT')")
    public ResponseEntity update(@Validated @RequestBody PerformanceDataTrashcan resources) {
        performanceDataTrashcanService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除PerformanceDataTrashcan")
    @DeleteMapping(value = "/performanceDataTrashcan/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERFORMANCEDATATRASHCAN_ALL','PERFORMANCEDATATRASHCAN_DELETE')")
    public ResponseEntity delete(@PathVariable Long id) {
        performanceDataTrashcanService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}