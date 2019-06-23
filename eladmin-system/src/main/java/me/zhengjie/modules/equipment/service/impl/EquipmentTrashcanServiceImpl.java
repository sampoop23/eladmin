package me.zhengjie.modules.equipment.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.modules.equipment.domain.EquipmentTrashcan;
import me.zhengjie.modules.equipment.easyexcel.ReadModel;
import me.zhengjie.modules.equipment.repository.EquipmentTrashcanRepository;
import me.zhengjie.modules.equipment.service.EquipmentTrashcanService;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanDTO;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanQueryCriteria;
import me.zhengjie.modules.equipment.easyexcel.ExcelListener;
import me.zhengjie.modules.equipment.service.mapper.EquipmentTrashcanMapper;
import me.zhengjie.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author cp
 * @date 2019-06-09
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EquipmentTrashcanServiceImpl implements EquipmentTrashcanService {


    @Value("${excel.max-size}")
    private Long maxSize;

    @Autowired
    private EquipmentTrashcanRepository equipmentTrashcanRepository;

    @Autowired
    private EquipmentTrashcanMapper equipmentTrashcanMapper;

    @Override
    public Object queryAll(EquipmentTrashcanQueryCriteria criteria, Pageable pageable) {
        Page<EquipmentTrashcan> page = equipmentTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(equipmentTrashcanMapper::toDto));
    }

    @Override
    public Object queryAll(EquipmentTrashcanQueryCriteria criteria) {
        return equipmentTrashcanMapper.toDto(equipmentTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public EquipmentTrashcanDTO findById(Long id) {
        Optional<EquipmentTrashcan> equipment = equipmentTrashcanRepository.findById(id);
        ValidationUtil.isNull(equipment, "EquipmentTrashcan", "id", id);
        return equipmentTrashcanMapper.toDto(equipment.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EquipmentTrashcanDTO create(EquipmentTrashcan resources) {
        if (equipmentTrashcanRepository.findByGpsId(resources.getGpsId()) != null) {
            throw new EntityExistException(EquipmentTrashcan.class, "gpsId", resources.getGpsId());
        }
        if (equipmentTrashcanRepository.findByEquipmentNo(resources.getEquipmentNo()) != null) {
            throw new EntityExistException(EquipmentTrashcan.class, "equipmentNo", resources.getEquipmentNo());
        }
//        if (equipmentTrashcanRepository.findByEquipmentName(resources.getEquipmentName()) != null) {
//            throw new EntityExistException(EquipmentTrashcan.class, "equipmentName", resources.getEquipmentName());
//        }

        return equipmentTrashcanMapper.toDto(equipmentTrashcanRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(EquipmentTrashcan resources) {
        Optional<EquipmentTrashcan> optionalEquipmentTrashcan = equipmentTrashcanRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalEquipmentTrashcan, "EquipmentTrashcan", "id", resources.getId());

        EquipmentTrashcan equipmentTrashcan = optionalEquipmentTrashcan.get();


        EquipmentTrashcan equipmentTrashcan1 = equipmentTrashcanRepository.findByGpsId(resources.getGpsId());
        EquipmentTrashcan equipmentTrashcan2 = equipmentTrashcanRepository.findByEquipmentNo(resources.getEquipmentNo());
//        EquipmentTrashcan equipmentTrashcan3 = equipmentTrashcanRepository.findByEquipmentName(resources.getEquipmentName());

        if (equipmentTrashcan1 != null && !equipmentTrashcan.getId().equals(equipmentTrashcan1.getId())) {
            throw new EntityExistException(EquipmentTrashcan.class, "gpsId", resources.getGpsId());
        }

        if (equipmentTrashcan2 != null && !equipmentTrashcan.getId().equals(equipmentTrashcan2.getId())) {
            throw new EntityExistException(EquipmentTrashcan.class, "equipmentNo", resources.getEquipmentNo());
        }

//        if (equipmentTrashcan3 != null && !equipmentTrashcan.getId().equals(equipmentTrashcan3.getId())) {
//            throw new EntityExistException(EquipmentTrashcan.class, "equipmentName", resources.getEquipmentName());
//        }

        // 此处需自己修改
//        if (!resources.getDept().equals(equipment.getDept())) {
//            String key = "role::loadPermissionByUser:" + user.getUsername();
//            redisService.delete(key);
//            key = "role::findByUsers_Id:" + user.getId();
//            redisService.delete(key);
//        }

//        //
        equipmentTrashcan.setGpsId(resources.getGpsId());
        equipmentTrashcan.setEquipmentNo(resources.getEquipmentNo());
//        equipmentTrashcan.setEquipmentName(resources.getEquipmentName());
        equipmentTrashcan.setAddressProv(resources.getAddressProv());
        equipmentTrashcan.setAddressCity(resources.getAddressCity());
        equipmentTrashcan.setAddressRegion(resources.getAddressRegion());
        equipmentTrashcan.setAddressStreet(resources.getAddressStreet());
        equipmentTrashcan.setAddressRoom(resources.getAddressRoom());
        equipmentTrashcan.setDept(resources.getDept());
        //
        equipmentTrashcanRepository.save(equipmentTrashcan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        Optional<EquipmentTrashcan> optionalEquipmentTrashcan = equipmentTrashcanRepository.findById(id);
        ValidationUtil.isNull(optionalEquipmentTrashcan, "EquipmentTrashcan", "id", id);
        EquipmentTrashcan equipmentTrashcan = optionalEquipmentTrashcan.get();
        Long eqId = equipmentTrashcan.getId();
        //
        equipmentTrashcanRepository.deleteById(id);
    }

    @Override
    public EquipmentTrashcanDTO findByGpsId(String gpsId) {
        EquipmentTrashcan equipment = equipmentTrashcanRepository.findByGpsId(gpsId);
        ValidationUtil.isNull(equipment, "EquipmentTrashcan", "gpsId", gpsId);
        return equipmentTrashcanMapper.toDto(equipment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean upload(MultipartFile file) {

        Long size = maxSize * 1024 * 1024;
        if (file.getSize() > size) {
            throw new BadRequestException("文件超出规定大小");
        }
//        if(qiniuConfig.getId() == null){
//            throw new BadRequestException("请先添加相应配置，再操作");
//        }
//        /**
//         * 构造一个带指定Zone对象的配置类
//         */
//        Configuration cfg = QiNiuUtil.getConfiguration(qiniuConfig.getZone());
//        UploadManager uploadManager = new UploadManager(cfg);
//        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
//        String upToken = auth.uploadToken(qiniuConfig.getBucket());
        try {

            InputStream inputStream = file.getInputStream();

//            String
//            file.transferTo();
            try {
                // 解析每行结果在listener中处理
                ExcelListener listener = new ExcelListener();

//                List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(2, 1, ReadModel.class));
                ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

                excelReader.read(new Sheet(1, 1, ReadModel.class));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            String key = file.getOriginalFilename();
//            if(qiniuContentRepository.findByKey(key) != null) {
//                key = QiNiuUtil.getKey(key);
//            }
//            Response response = uploadManager.put(file.getBytes(), key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            //存入数据库
//            QiniuContent qiniuContent = new QiniuContent();
//            qiniuContent.setBucket(qiniuConfig.getBucket());
//            qiniuContent.setType(qiniuConfig.getType());
//            qiniuContent.setKey(putRet.key);
//            qiniuContent.setUrl(qiniuConfig.getHost()+"/"+putRet.key);
//            qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(file.getSize()+"")));
//            return qiniuContentRepository.save(qiniuContent);
            return true;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}