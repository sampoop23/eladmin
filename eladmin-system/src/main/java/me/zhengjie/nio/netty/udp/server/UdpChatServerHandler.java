package me.zhengjie.nio.netty.udp.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.equipment.service.EquipmentTrashcanService;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanDTO;
import me.zhengjie.modules.performance.domain.PerformanceDataTrashcan;
import me.zhengjie.modules.performance.domain.PerformanceHisDataTrashcan;
import me.zhengjie.modules.performance.service.PerformanceDataTrashcanService;
import me.zhengjie.modules.performance.service.PerformanceHisDataTrashcanService;
import me.zhengjie.modules.system.service.mapper.DeptMapper;
import me.zhengjie.utils.SpringContextHolder;
import org.springframework.stereotype.Component;

/**
 * 消息处理类
 *
 * @author kokJuis
 * @version 1.0
 * @date 2016-9-30
 */
@Slf4j
@Component
public class UdpChatServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private EquipmentTrashcanService equipmentTrashcanService;
    private PerformanceDataTrashcanService performanceDataTrashcanService;
    private PerformanceHisDataTrashcanService performanceHisDataTrashcanService;
    private DeptMapper deptMapper;

//    @PostConstruct
//    public void postConstruct() {
//        equipmentTrashcanService = SpringContextHolder.getBean(EquipmentTrashcanService.class);
//        performanceDataTrashcanService = SpringContextHolder.getBean(PerformanceDataTrashcanService.class);
//        performanceHisDataTrashcanService = SpringContextHolder.getBean(PerformanceHisDataTrashcanService.class);
//        deptMapper = SpringContextHolder.getBean(DeptMapper.class);
//    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //注意，UDP的通道至始至终只有一个，关了就不能接收了。
        log.info("UDP通道已经连接");
//        UdpChatServer1.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {

        equipmentTrashcanService = SpringContextHolder.getBean(EquipmentTrashcanService.class);
        performanceDataTrashcanService = SpringContextHolder.getBean(PerformanceDataTrashcanService.class);
        performanceHisDataTrashcanService = SpringContextHolder.getBean(PerformanceHisDataTrashcanService.class);
        deptMapper = SpringContextHolder.getBean(DeptMapper.class);


        // 读取收到的数据
        ByteBuf buf = (ByteBuf) packet.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, CharsetUtil.UTF_8);
        log.info("【NOTE】>>>>>> 消息来源 {}:{} ,收到客户端的数据：{}", packet.sender().getHostString(), packet.sender().getPort(), body);

        savePerformanceData(body);

        // 回复一条信息给客户端
        DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer("ACK:" + System.currentTimeMillis()
                , CharsetUtil.UTF_8)
                , packet.sender());
        ctx.writeAndFlush(dp).sync();

//        //消息发送。。。。
//        DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer("消息".getBytes()), packet.sender());
////        UdpChatServer1.channel.writeAndFlush(dp);

    }


    private void savePerformanceData(String json) throws Exception {


        JSONObject jsonObject = JSON.parseObject(json);
        String gpsId = jsonObject.getString("imei");

        EquipmentTrashcanDTO equipment = equipmentTrashcanService.findByGpsId(gpsId);

        //实时工况
        createPerformanceData(jsonObject, gpsId, equipment);

        //历史工况
        createPerformanceHisData(jsonObject, gpsId, equipment);
    }

    private void createPerformanceData(JSONObject jsonObject, String gpsId, EquipmentTrashcanDTO equipment) {
        PerformanceDataTrashcan performanceData = new PerformanceDataTrashcan();
        performanceData.setGpsId(gpsId);
        performanceData.setStatus(jsonObject.getInteger("status"));
        performanceData.setWtdG(jsonObject.getInteger("wtdG"));
        performanceData.setWtnG(jsonObject.getInteger("wtnG"));
        performanceData.setErrInfo(jsonObject.getString("errInfo"));

        //冗余字段
        performanceData.setDept(deptMapper.toEntity(equipment.getDept()));
        performanceData.setAddressProv(equipment.getAddressProv());
        performanceData.setAddressCity(equipment.getAddressCity());
        performanceData.setAddressRegion(equipment.getAddressRegion());
        performanceData.setAddressStreet(equipment.getAddressStreet());
        performanceData.setAddressRoom(equipment.getAddressRoom());
        performanceData.setTrashcanType(equipment.getTrashcanType());
        performanceData.setGarbageType(equipment.getGarbageType());
        performanceDataTrashcanService.deleteByGpsId(gpsId);
        performanceDataTrashcanService.create(performanceData);
    }

    private void createPerformanceHisData(JSONObject jsonObject, String gpsId, EquipmentTrashcanDTO equipment) {
        PerformanceHisDataTrashcan performanceHisData = new PerformanceHisDataTrashcan();
        performanceHisData.setGpsId(gpsId);
        performanceHisData.setStatus(jsonObject.getInteger("status"));
        performanceHisData.setWtdG(jsonObject.getInteger("wtdG"));
        performanceHisData.setWtnG(jsonObject.getInteger("wtnG"));
        performanceHisData.setErrInfo(jsonObject.getString("errInfo"));

        //冗余字段
        performanceHisData.setDept(deptMapper.toEntity(equipment.getDept()));
        performanceHisData.setAddressProv(equipment.getAddressProv());
        performanceHisData.setAddressCity(equipment.getAddressCity());
        performanceHisData.setAddressRegion(equipment.getAddressRegion());
        performanceHisData.setAddressStreet(equipment.getAddressStreet());
        performanceHisData.setAddressRoom(equipment.getAddressRoom());
        performanceHisData.setTrashcanType(equipment.getTrashcanType());
        performanceHisData.setGarbageType(equipment.getGarbageType());
        performanceHisDataTrashcanService.create(performanceHisData);
    }

}