import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
public class AgentTest {
/*
    @Test
    public void polling1() {
        Agent a = new Agent();
        double f = 0.59;
        boolean q = true;
        boolean testEscape = a.poll(q, f);
        assertEquals(true, testEscape);
        f = 0.01;
        q = true;
        testEscape = a.poll(q, f);
        assertEquals(true, testEscape);
        f = 0.4;
        q = false;
        testEscape = a.poll(q, f);
        assertEquals(false, testEscape);
        f = 0.29;
        q = false;
        testEscape = a.poll(q, f);
        assertEquals(true, testEscape);
    }
*/
    @Test
    public void agentTest() {
        Random random1 = new Random(10);
        Random random2 = new Random(5);
        Random random3 = new Random(20);
        Random random4 = new Random(25);
        Random rand = new Random(1);
        Event[] e = new Event[5];
        for(int i = 0; i<5; i++) {
            e[i] = new Event(-1,1,2,2,rand.nextBoolean());
        }

        Agent[] agents = new Agent[10];
        for(int i = 0; i<10; i++) {
            agents[i] = new Agent(random1.nextDouble(), 0.3*random2.nextDouble(), 0.5+0.3*random3.nextDouble());
            agents[i].generateQuorum(random4);
            System.out.println("truep"+agents[i].getpTrueP());
            System.out.println("falsep"+agents[i].getpFalseP());
            System.out.println(agents[i].getQuorumThreshold());
        }
        /*
        for(int j = 0; j<5; j++) {
            System.out.println("event" + j + " ");
            for(int k = 0; k<10; k++) {
                agents[k].newEventIn(e[j]);
                System.out.println("truep"+agents[k].getpTrueP());
                System.out.println("falsep"+agents[k].getpFalseP());
                //System.out.println(agents[k].getIntensitySignal()
                )
            }
        }
        */
    }
    @Test
    public void agentTest2() {
        Agent[] agents = new Agent[10];
        for(int i = 0; i<10; i++) {
            agents[i] = new Agent(0.1,0.9);
            agents[i].makeFirstDecision(false);
        }
    }
    @Test
    public void generateQuorumTest() {
        Random rand = new Random(1);
        Agent[] agents = new Agent[10];
        for(int i = 0; i<10; i++) {
            agents[i] = new Agent(0.5,0.52);
            agents[i].generateQuorum(rand);
            System.out.println(agents[i].getQuorumThreshold());
        }
    }
}