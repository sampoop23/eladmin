package me.zhengjie.modules.system.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.system.domain.AreaCode;
import me.zhengjie.modules.system.service.AreaCodeService;
import me.zhengjie.modules.system.service.dto.AreaCodeDTO;
import me.zhengjie.modules.system.service.dto.AreaCodeQueryCriteria;
import me.zhengjie.modules.system.service.dto.DeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author cp
 * @date -06-12
 */
@RestController
@RequestMapping("api")
public class AreaCodeController {

    @Autowired
    private AreaCodeService areaCodeService;

    @Log("查询AreaCode")
    @GetMapping(value = "/areaCode")
    @PreAuthorize("hasAnyRole('ADMIN','AREACODE_ALL','AREACODE_SELECT')")
    public ResponseEntity getAreaCodes(AreaCodeQueryCriteria criteria, Pageable pageable) {
//        return new ResponseEntity(areaCodeService.queryAll(criteria, pageable), HttpStatus.OK);
        List<AreaCodeDTO> areaCodeDTOS = areaCodeService.queryAll(criteria);
        return new ResponseEntity(areaCodeService.buildTree(areaCodeDTOS),HttpStatus.OK);
    }

    @Log("新增AreaCode")
    @PostMapping(value = "/areaCode")
    @PreAuthorize("hasAnyRole('ADMIN','AREACODE_ALL','AREACODE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AreaCode resources) {
        return new ResponseEntity(areaCodeService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改AreaCode")
    @PutMapping(value = "/areaCode")
    @PreAuthorize("hasAnyRole('ADMIN','AREACODE_ALL','AREACODE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AreaCode resources) {
        areaCodeService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AreaCode")
    @DeleteMapping(value = "/areaCode/{code}")
    @PreAuthorize("hasAnyRole('ADMIN','AREACODE_ALL','AREACODE_DELETE')")
    public ResponseEntity delete(@PathVariable Long code) {
        areaCodeService.delete(code);
        return new ResponseEntity(HttpStatus.OK);
    }
}