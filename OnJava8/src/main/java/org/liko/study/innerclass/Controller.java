package org.liko.study.innerclass;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller
 *
 * @author liko
 * @date 2020/5/25
 */
public class Controller {
    private List<Event> eventList = new ArrayList<>();

    public void addEvent (Event e) {
        eventList.add(e);
    }

    public void run() {
        while (eventList.size() > 0) {
            for (Event event : new ArrayList<>(eventList)) {
                if (event.ready()) {
                    System.out.println(event);
                    event.action();
                    eventList.remove(event);
                }
            }
        }
    }
}
