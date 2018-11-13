import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.junit.Test;

import java.util.Random;

public class EventTest {
    @Test
    public void EventTest() {
        Random rand = new Random(2);
        boolean state;
        Event[] events = new Event[10];
        for(int i = 0; i<10; i++) {
            state = rand.nextBoolean();
            events[i] = new Event(-1,1,2,2,state);
            System.out.println(events[i].getState());
            System.out.println(events[i].getIntensitySignal());
        }
    }
}
