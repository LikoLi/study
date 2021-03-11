package cn.likoli.study.cfetsdemo;

import imix.Dictionary;
import imix.Group;
import imix.client.core.ImixApplication;
import imix.client.core.ImixSession;
import imix.compatibility.SelfInterpretation;

public class ApiLearning {
    public static void main(String[] args) {
        ImixSession imixSession = new ImixSession();
        SelfInterpretation.setEnable();
        Group group = new Group();

        Dictionary dictionary = new Dictionary("IMIX20.xml");

    }
}
