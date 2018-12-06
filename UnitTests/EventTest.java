import org.junit.Test;

public class EventTest {
    @Test
    public void eventTest() {
        EventGroup group = new EventGroup(-1,1,2,2,50);
        group.generateEvents();
    }

}