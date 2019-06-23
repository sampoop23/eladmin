package me.zhengjie.modules.equipment.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class ReadModel extends BaseRowModel {

    //机构编码
    @ExcelProperty(index = 0)
    protected Long deptId;

    //模块识别码
    @ExcelProperty(index = 1)
    protected String gpsId;

    //设备编号
    @ExcelProperty(index = 2)
    protected String equipmentNo;

    //垃圾桶类型
    @ExcelProperty(index = 3)
    private String trashcanType;

    //垃圾分类
    @ExcelProperty(index = 4)
    private String garbageType;

    @ExcelProperty(index = 5)
    private String addressCode;

    //街道
    @ExcelProperty(index = 6)
    private String addressStreet;

    //门牌
    @ExcelProperty(index = 7)
    private String addressRoom;


}