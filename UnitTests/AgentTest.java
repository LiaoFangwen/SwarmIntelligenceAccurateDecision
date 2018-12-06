import org.junit.Test;

import java.util.Random;

public class AgentTest {
    @Test
    public void agentTest() {
        EventGroup group = new EventGroup(-1,1,2,2,10);
        Agent agent = new Agent();
        Agent agent1 = new Agent();
        Random random = new Random();
        agent.setTrueFalsePositiveProbabilities(group.getBounds(),group.getDistributions(),random);
        agent1.setTrueFalsePositiveProbabilities(group.getBounds(),group.getDistributions(),random);

    }
    @Test
    public void generateInitialQuorumTest() {
        EventGroup group = new EventGroup(-1,1,2,2,10);
        group.getAbilityLimitation();
        Agent agent = new Agent();
        Agent agent1 = new Agent();
        Random random = new Random(87);
        agent.setTrueFalsePositiveProbabilities(group.getBounds(),group.getDistributions(),random);
        agent1.setTrueFalsePositiveProbabilities(group.getBounds(),group.getDistributions(),random);
        agent.generateInitialQuorum(random);
        agent1.generateInitialQuorum(random);
    }
    @Test
    public void makeFirstDecisionTest() {
        EventGroup group = new EventGroup(-1,1,2,2,100);
        group.generateEvents();
        Agent agent = new Agent();
        Random random = new Random();
        agent.setTrueFalsePositiveProbabilities(group.getBounds(),group.getDistributions(),random);
        for(int i=0;i<10;i++) {
            System.out.println(group.getEvent(i).getState()+"...");
            agent.makeFirstDecision(group.getEvent(i).getIntensitySignal());
        }
    }
}