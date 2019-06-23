package me.zhengjie.modules.equipment.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.equipment.domain.EquipmentTrashcan;
import me.zhengjie.modules.equipment.service.EquipmentTrashcanService;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.SpringContextHolder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelListener extends AnalysisEventListener<ReadModel> {

    private EquipmentTrashcanService equipmentTrashcanService = SpringContextHolder.getBean(EquipmentTrashcanService.class);
    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<ReadModel> datas = new ArrayList<>();

    @Override
    public void invoke(ReadModel model, AnalysisContext context) {
        log.info("当前行{{}} {}", context.getCurrentRowNum(), model);
        datas.add(model);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(model);//根据自己业务做处理
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }

    private void doSomething(ReadModel model) {
        //1、入库调用接口
        EquipmentTrashcan resources = new EquipmentTrashcan();
        resources.setGpsId(model.getGpsId());
        resources.setEquipmentNo(model.getEquipmentNo());
        resources.setTrashcanType(model.getTrashcanType());
        resources.setGarbageType(model.getGarbageType());
        //
        String addressCode = model.getAddressCode();
        if (addressCode != null && addressCode.length() == 6) {
            //130000
            resources.setAddressProv(addressCode.substring(0, 2) + "0000");
            //130300
            resources.setAddressCity(addressCode.substring(0, 4) + "00");
            //130304
            resources.setAddressRegion(model.getAddressCode());
        }
        resources.setAddressStreet(model.getAddressStreet());
        resources.setAddressRoom(model.getAddressRoom());
        //
        Dept dept = new Dept();
        if (model.getDeptId() != null && model.getDeptId().longValue() != 0) {
            dept.setId(model.getDeptId());
        } else {
            dept.setId(SecurityUtils.getUserDeptId());
        }
        resources.setDept(dept);
        //
        equipmentTrashcanService.create(resources);
    }

    public List<ReadModel> getDatas() {
        return datas;
    }

    public void setDatas(List<ReadModel> datas) {
        this.datas = datas;
    }
}