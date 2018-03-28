package org.liko.event;

public class Main {

    public static void main(String[] args) {
        // 根据Event.xls 生成 event db 数据
        EventGenerator.generate();

        // 根据事件ID生成xls
//        EventGenerator.getEvent(248);
    }
}
