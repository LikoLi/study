package org.liko.study.innerclass;

/**
 * GreenhouseControls
 *
 * @author liko
 * @date 2020/5/25
 */
public class GreenhouseControls extends Controller {
    private boolean light = false;

    public class LightOn extends Event {


        public LightOn(long millisecondDelay) {
            super(millisecondDelay);
        }

        @Override
        public void action() {
            light = true;
        }

        @Override
        public String toString() {
            return "Light is on";
        }
    }
}
