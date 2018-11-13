public class Polling {
    private int agentNumber;
    private double sum;
    private double threshold;
    private boolean aboveThreshold;
    private Agent[] agents;
    public Polling(int agentNumber, double threshold) {
        this.agentNumber = agentNumber;
        this.threshold = threshold;
        agents = new Agent[agentNumber];
    }
    /*
    public boolean allParticipantsPoll(boolean state) {
        for (int i = 0; i < agentNumber; i++) {
            agents[i] = new Agent();
            double ran = Math.random();
            boolean x = agents[i].poll(state, ran);
            if (x == true)
                sum = sum + 1;
        }
        double t = sum/ agentNumber;
        if(t > threshold) {
            aboveThreshold = true;
            //System.out.println("above threshold, " + t + "people escape, " + "the state is " + state);
        }
        if(t < threshold) {
            aboveThreshold = false;
            //System.out.println("below threshold, " + t + "people escape, " + "the state is " + state);
        }
        sum = 0;
        return aboveThreshold;
    }
    */
    public int getAgentNumber() {
        return agentNumber;
    }


}
