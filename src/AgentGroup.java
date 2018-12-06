import java.util.ArrayList;
import java.util.Random;

public class AgentGroup {
    private Agent[] agents;
    private EventGroup eventGroup;
    private int agentNumber = 0;
    private int totalDecisionNumber = 0;
    private double[] quorumSet;
    ArrayList<double[]> resultList = new ArrayList<>();
    public AgentGroup(int agentNumber, EventGroup eventGroup){
        this.agentNumber = agentNumber;
        this.eventGroup = eventGroup;
        this.agents = new Agent[agentNumber];
        this.quorumSet = new double[agentNumber];
        Random random = new Random();
        for(int i=0; i<agentNumber; i++) {
            agents[i] = new Agent();
        }
        generateTrueFalsePositiveProbabilitiesAndInitialQuorums();
    }
    public void generateTrueFalsePositiveProbabilitiesAndInitialQuorums() {
        for(Agent agent : agents) {
            Random random1 = new Random();
            Random random2 = new Random();
            agent.setTrueFalsePositiveProbabilities(eventGroup.getBounds(), eventGroup.getDistributions(), random1);
            agent.generateInitialQuorum(random2);
        }
    }
    public void firstDecision(double signal) {
        for(Agent agent : agents) {
            agent.makeFirstDecision(signal);
            totalDecisionNumber += agent.submitFirstDecision();
        }
    }
    public void secondDecisionAndLearn(boolean realState) {
        for(int i = 0; i<agentNumber; i++) {
            double total = totalDecisionNumber/(double)agentNumber;
            agents[i].makeSecondDecision(total);
            agents[i].learn(realState);
            quorumSet[i] = agents[i].getQuorumThreshold();

        }
    }
    public void findingQuorum() {
        for(Event event : eventGroup.getEvents()) {
            totalDecisionNumber = 0;
            boolean state = event.getState();
            double signal = event.getIntensitySignal();
            firstDecision(signal);
            secondDecisionAndLearn(state);
            resultList.add(calculateDeviation());
        }
    }
    public double[] calculateDeviation() {
        double[] result = new double[2];
        double sum = 0;
        double deviation = 0;
        double length = quorumSet.length;
        for(int i=0; i<length; i++) {
            sum += quorumSet[i];
        }
        double mean = sum/length;
        for(int i=0; i<length; i++) {
            sum += quorumSet[i];
        }
        for(int j=0; j<length; j++) {
            deviation += java.lang.Math.pow(quorumSet[j] - mean, 2) / length;
        }
        result[0] = mean;
        result[1] = java.lang.Math.sqrt(deviation);
        return result;
    }
    public ArrayList<double[]> getResultList() {
        return resultList;
    }
}
