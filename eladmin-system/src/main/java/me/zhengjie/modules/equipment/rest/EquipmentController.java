package me.zhengjie.modules.equipment.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.config.DataScope;
import me.zhengjie.modules.equipment.domain.Equipment;
import me.zhengjie.modules.equipment.service.EquipmentService;
import me.zhengjie.modules.equipment.service.dto.EquipmentQueryCriteria;
import me.zhengjie.modules.system.service.DeptService;
import me.zhengjie.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


/**
 * @author cp
 * @date 2019-06-09
 */
@RestController
@RequestMapping("api")
public class EquipmentController {

    @Autowired
    private DataScope dataScope;

    @Autowired
    private DeptService deptService;

    @Autowired
    private EquipmentService equipmentService;

    @Log("查询设备")
    @GetMapping(value = "/equipment")
    @PreAuthorize("hasAnyRole('ADMIN','EQUIPMENT_ALL','EQUIPMENT_SELECT')")
    public ResponseEntity getEquipments(EquipmentQueryCriteria criteria, Pageable pageable) {
//        return new ResponseEntity(equipmentService.queryAll(criteria, pageable), HttpStatus.OK);

        Set<Long> deptSet = new HashSet<>();
        Set<Long> result = new HashSet<>();

        if (!ObjectUtils.isEmpty(criteria.getDeptId())) {
            deptSet.add(criteria.getDeptId());
            deptSet.addAll(dataScope.getDeptChildren(deptService.findByPid(criteria.getDeptId())));
        }

        // 数据权限
        Set<Long> deptIds = dataScope.getDeptIds();

        // 查询条件不为空并且数据权限不为空则取交集
        if (!CollectionUtils.isEmpty(deptIds) && !CollectionUtils.isEmpty(deptSet)) {

            // 取交集
            result.addAll(deptSet);
            result.retainAll(deptIds);

            // 若无交集，则代表无数据权限
            criteria.setDeptIds(result);
            if (result.size() == 0) {
                return new ResponseEntity(PageUtil.toPage(null, 0), HttpStatus.OK);
            } else {
                return new ResponseEntity(equipmentService.queryAll(criteria, pageable), HttpStatus.OK);
            }
            // 否则取并集
        } else {
            result.addAll(deptSet);
            result.addAll(deptIds);
            criteria.setDeptIds(result);
            return new ResponseEntity(equipmentService.queryAll(criteria, pageable), HttpStatus.OK);
        }
    }

    @Log("新增设备")
    @PostMapping(value = "/equipment")
    @PreAuthorize("hasAnyRole('ADMIN','EQUIPMENT_ALL','EQUIPMENT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Equipment resources) {
        return new ResponseEntity(equipmentService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改设备")
    @PutMapping(value = "/equipment")
    @PreAuthorize("hasAnyRole('ADMIN','EQUIPMENT_ALL','EQUIPMENT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Equipment resources) {
        equipmentService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除设备")
    @DeleteMapping(value = "/equipment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EQUIPMENT_ALL','EQUIPMENT_DELETE')")
    public ResponseEntity delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}