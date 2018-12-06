import org.junit.Test;

import static org.junit.Assert.*;

public class EventGroupTest {
    @Test
    public void eventGroupTest() {
        EventGroup group = new EventGroup(-0.1,0.1,2,2,10);
        System.out.println(group.getBounds()[0]);
        System.out.println(group.getBounds()[1]);
    }
    @Test
    public void generateEventsTest() {
        EventGroup group = new EventGroup(-0.1,0.1,2,2,10);
        group.generateEvents();
        for(int i=0;i<10;i++)
            System.out.println(group.getEvent(i).getState());
    }
    @Test
    public void generateThreshold() {
    }

    @Test
    public void getBounds() {
    }

    @Test
    public void getSafeProbability() {
    }

    @Test
    public void getDangerousProbability() {
    }

    @Test
    public void getIntensitySignal() {
    }

    @Test
    public void getEvent() {
    }
}