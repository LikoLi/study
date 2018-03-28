package org.liko.event.util;

import org.apache.commons.lang3.StringUtils;
import org.liko.event.Main;
import org.liko.event.dao.java.EventfielddefMapper;
import org.liko.event.dao.java.EventgroupdefMapper;
import org.liko.event.dao.java.EventstructdefMapper;
import org.liko.event.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventUtil {

    @Autowired
    private static EventstructdefMapper eventstructdefMapper;

    @Autowired
    private static EventgroupdefMapper eventgroupdefMapper;

    @Autowired
    private static EventfielddefMapper eventfielddefMapper;

    // 默认事件定义文件
    private static final String PATH = "event.xls";


    public void generator() throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        eventgroupdefMapper = (EventgroupdefMapper) Main.context.getBean("eventgroupdefMapper");
        eventstructdefMapper = (EventstructdefMapper) Main.context.getBean("eventstructdefMapper");
        eventfielddefMapper = (EventfielddefMapper) Main.context.getBean("eventfielddefMapper");

        ExcelUtil excelUtil = new ExcelUtil(PATH);

        //-- 更新事件定义表
        //从excel中获取事件定义表数据
        List<EventstructdefExt> eventstructdefExtList = excelUtil.getInstanceBySheetNameAndType(EventstructdefExt.class.getSimpleName(), new EventstructdefExt());

        // 获取所有的gourp name
        Set<String> groupName = new HashSet<>();

        // 获取所有的 eventName
        List<String> strucEventName = new ArrayList<>();

        eventstructdefExtList.stream().forEach(eventstructdefExt -> {
            groupName.add(eventstructdefExt.getGroupName());
            strucEventName.add(eventstructdefExt.getName());
        });

        // 根据group name 查询 group id
        List<Eventgroupdef> eventgroupdefs = eventgroupdefMapper.selectByNames(DBUtil.toSqlString(groupName.stream().collect(Collectors.toList())));

        // 根据event name 查询 event id
        List<Eventstructdef> eventstructdefs = eventstructdefMapper.selectByNames(DBUtil.toSqlString(strucEventName));

        // 将group name 和 group id 转成map
        Map<String, Integer> groupId = eventgroupdefs.stream().collect(Collectors.toMap(Eventgroupdef::getName, Eventgroupdef::getId));

        // 将event name 和 event id 转成map
        Map<String, Integer> eventNameIdMap = eventstructdefs.stream().collect(Collectors.toMap(Eventstructdef::getName, Eventstructdef::getId));



        // -------------------------------------------

        //-- 更新事件字段表
        //从excel中获取事件字段表
        List<EventfielddefExt> eventfielddefExtList = excelUtil.getInstanceBySheetNameAndType(EventfielddefExt.class.getSimpleName(), new EventfielddefExt());

        // 获取所有的event name 和 element name
        Set<String> eventName = new HashSet<>();
        eventfielddefExtList.stream().forEach(eventfielddefExt -> {
            eventName.add(eventfielddefExt.getEventName());
            eventName.add(eventfielddefExt.getElementName());
        });

        // 根据event name 查询所有的事件定义数据
        List<Eventstructdef> eventstructdefList = eventstructdefMapper.selectByNames(DBUtil.toSqlString(eventName.stream().collect(Collectors.toList())));

        // 将事件定义数据转成map
        Map<String, Integer> eventId = eventstructdefList.stream().collect(Collectors.toMap(Eventstructdef::getName, Eventstructdef::getId));

        // 删除所有事件字段定义
        List<String> ids = new ArrayList<>();
        eventId.values().stream().forEach(id -> ids.add(id.toString()));
        eventfielddefMapper.deleteByIds(DBUtil.toSqlString(ids));

        // 删除之前的事件
        List<String> eventIds = new ArrayList<>();
        eventNameIdMap.values().stream().forEach(id -> eventIds.add(id.toString()));
        eventstructdefMapper.deleteByEventId(DBUtil.toSqlString(eventIds));

        // ------------------------------------------

        // 保存/更新事件定义表
        Iterator<EventstructdefExt> eventstructdefExtIterator = eventstructdefExtList.iterator();
        while (eventstructdefExtIterator.hasNext()) {
            Eventstructdef eventstructdef = new Eventstructdef();
            EventstructdefExt eventstructdefExt = eventstructdefExtIterator.next();
            BeanUtils.copyProperties(eventstructdefExt, eventstructdef);
            eventstructdef.setGroupid(groupId.get(eventstructdefExt.getGroupName()));
            DBUtil.saveOrUpdate(eventstructdefMapper, eventstructdef);
        }

        // 生成新的event id
        // 根据event name 查询所有的事件定义数据
        eventstructdefList = eventstructdefMapper.selectByNames(DBUtil.toSqlString(eventName.stream().collect(Collectors.toList())));

        // 将事件定义数据转成map
        eventId = eventstructdefList.stream().collect(Collectors.toMap(Eventstructdef::getName, Eventstructdef::getId));

        // 插入新的事件字段定义
        Iterator<EventfielddefExt> eventfielddefExtIterator = eventfielddefExtList.iterator();
        while (eventfielddefExtIterator.hasNext()) {
            Eventfielddef eventfielddef = new Eventfielddef();
            EventfielddefExt eventfielddefExt = eventfielddefExtIterator.next();
            BeanUtils.copyProperties(eventfielddefExt, eventfielddef);
            eventfielddef.setEventid(eventId.get(eventfielddefExt.getEventName()));
            eventfielddef.setElementid(eventId.get(eventfielddefExt.getElementName()));

            DBUtil.saveOrUpdate(eventfielddefMapper, eventfielddef);
        }

    }


}
