import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Random;

public class EventGroup {
    private NormalDistribution safeState;
    private NormalDistribution dangerousState;
    private double boundMean;
    private double lowerBound;
    private double higherBound;
    private double[] bounds = new double[2];
    private int eventNumbers;
    private Event[] events;

    /**
     * create new EventGroup
     * @param safeMean
     * @param dangerousMean
     * @param deviationS
     * @param deviationD
     * @param eventNumbers
     */
    public EventGroup(double safeMean, double dangerousMean, double deviationS, double deviationD, int eventNumbers) {
        safeState = new NormalDistribution(safeMean, deviationS);
        dangerousState = new NormalDistribution(dangerousMean, deviationD);
        boundMean = (safeMean + dangerousMean)/2;
        if((safeState.cumulativeProbability(boundMean) - 0.5) <= 0.1)
            lowerBound = safeMean;
        else
            lowerBound = safeState.inverseCumulativeProbability(safeState.cumulativeProbability(boundMean)-0.1);
        if((0.5 - dangerousState.cumulativeProbability(boundMean)) <= 0.1)
            higherBound = dangerousMean;
        else
            higherBound = boundMean;//dangerousState.inverseCumulativeProbability(dangerousState.cumulativeProbability(boundMean)+0.1);
        bounds[0] = lowerBound;
        bounds[1] = higherBound;
        this.eventNumbers = eventNumbers;
        events = new Event[eventNumbers];
        generateEvents();
    }

    /**
     * create new Events for the group
     */
    public void generateEvents() {
        boolean s;
        for(int i=0; i<eventNumbers; i++) {
            Random random = new Random();
            s = random.nextBoolean();
            events[i] = new Event(s, getDistributions());
        }
    }

    public double[] generateThreshold(int agentNumber) {
        Random random = new Random();
        for(int i = 0; i<agentNumber; i++) {

        }
        double threshold = bounds[0] + random.nextDouble()+(bounds[1] - bounds[0]);
        double[] probabilities = new double[2];
        probabilities[0] = 1 - getSafeProbability(threshold);
        probabilities[1] = 1 - getDangerousProbability(threshold);
        return probabilities;

    }

    /**
     * get two distributions for AgentGroup
     * @return two distributions
     */
    public NormalDistribution[] getDistributions() {
        NormalDistribution[] distributions = new NormalDistribution[2];
        distributions[0] = safeState;
        distributions[1] = dangerousState;
        return distributions;
    }


    public double[] getBounds() {
        return bounds;
    }

    /**
     * see the limit of agents in this event group
     */
    public void getAbilityLimitation() {
        double max = bounds[1];
        double min = bounds[0];
        double pTruepMaxBound = 1 - dangerousState.cumulativeProbability(max);
        double pFalsepMaxbound = 1 - safeState.cumulativeProbability(max);
        double pTruepMinBound = 1 - dangerousState.cumulativeProbability(min);
        double pFalsepMinBound = 1 - safeState.cumulativeProbability(min);
        System.out.println("at max bound "+max+": true= "+ pTruepMaxBound+" ,false= "+ pFalsepMaxbound);
        System.out.println("at min bound "+min+": true= "+ pTruepMinBound+" ,false= "+ pFalsepMinBound);
    }
    public double getSafeProbability(double x) {
        return safeState.cumulativeProbability(x);
    }
    public double getDangerousProbability(double x) {
        return dangerousState.cumulativeProbability(x);
    }

    public Event getEvent(int i) {
        return events[i];
    }
    public Event[] getEvents() {
        return events;
    }

}
