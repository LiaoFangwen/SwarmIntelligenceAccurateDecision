import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

import java.util.Random;

public class Agent {
    private double pTrueP;
    private double pFalseP;
    private boolean firstDecision;
    private boolean secondDecision;
    private double escapeThreshold = 0;
    private double quorumThreshold = 0;
    private double initialQuorum = 0;

    /**
     * set pTp and pFp for agents
     * @param bounds bounds in which can choose threshold
     * @param distributions distributions from event groups for calculating probabilities
     */
    public void setTrueFalsePositiveProbabilities(double[] bounds, NormalDistribution[] distributions, Random random) {
        double threshold = bounds[0] + random.nextDouble()*(bounds[1] - bounds[0]);
        escapeThreshold = threshold;
        pTrueP = 1 - distributions[1].cumulativeProbability(threshold);
        pFalseP = 1 - distributions[0].cumulativeProbability(threshold);

        //System.out.println(bounds[0]);
        //System.out.println(bounds[1]);
        //System.out.println(threshold);
        //System.out.println(pTrueP);
       //System.out.println(pFalseP);

    }

    public void generateInitialQuorum(Random rand) {
        quorumThreshold = pFalseP + (pTrueP - pFalseP) * rand.nextDouble();
        initialQuorum = quorumThreshold;
        System.out.println(quorumThreshold);

    }
    public void setQuorumThreshold(int q) {
        Random rand = new Random();
        quorumThreshold = q + rand.nextInt(20);
    }
    /*
    public void makeFirstDecision(boolean state) {
        UniformRealDistribution distribution = new UniformRealDistribution(0,1);
        double sample = distribution.sample();
        System.out.println(sample);
        if(state == true) {
            if(sample<pTrueP)
                firstDecision = true;
            else
                firstDecision = false;
        } else if(state == false) {
            if(sample<pFalseP)
                firstDecision = true;
            else
                firstDecision = false;
        }
        System.out.println(firstDecision);
    }
    */
    public void makeFirstDecision(double intensitySignal) {
        if(intensitySignal >= escapeThreshold)
            firstDecision = true;
        else
            firstDecision = false;
        //System.out.println(firstDecision);
    }
    public int submitFirstDecision() {
        int value = 0;
        if(firstDecision == true)
            value = 1;
        if(firstDecision == false)
            value = 0;
        return value;
    }
    public void makeSecondDecision(double total) {
        if(total >= quorumThreshold)
            secondDecision = true;
        else
            secondDecision = false;
    }
    public void learn(boolean realState) {
        if(secondDecision == true && realState == false)
            quorumThreshold = quorumThreshold + 0.001;
        else if(secondDecision == false && realState == true)
            quorumThreshold = quorumThreshold - 0.001;
    }




    public double getOldQuorum() {
        return initialQuorum;
    }

    public boolean getFirstDecision() {
        return firstDecision;
    }

    public boolean getSecondDecision() {
        return secondDecision;
    }
    public double getQuorumThreshold() {
        return quorumThreshold;
    }
    public double getEscapeThreshold() {
        return escapeThreshold;
    }
    public double getpTrueP() {
        return pTrueP;
    }
    public double getpFalseP() {
        return pFalseP;
    }

    /*
    public boolean poll(boolean state, double f) {
        if(state == true) {
            if (0.0 <= f && f < pTrueP)
                doEscape = true;
            else
                doEscape = false;
        }
        if(state == false) {
            if (0.0 <= f && f < pFalseP)
                doEscape = true;
            else
                doEscape = false;
        }
        return doEscape;
    }
    */
}