import org.apache.commons.math3.distribution.UniformRealDistribution;

import java.util.Random;

public class Agent {
    private double pTrueP;
    private double pFalseP;
    private boolean firstDecision;
    private boolean secondDecision;
    private double escapeThreshold = 0;
    private double quorumThreshold = 0;
    private double oldQuorum = 0;
    private double intensitySignal = 0;
    //public int life;
    //public Agent() {}
    //public Agent(double pT, double pF) {};
    //public Agent(double pT, double pF, double maxInterval) {}
    public Agent(double fp, double tp) {
        this.pFalseP = fp;
        this.pTrueP = tp;
    }
    public Agent(double escapeThreshold, double fp, double tp) {
        this.escapeThreshold = escapeThreshold;
        this.pFalseP = fp;
        this.pTrueP = tp;
        //this.quorumThreshold = quorumThreshold;
        //this.oldQuorum = quorumThreshold;
    }

    public void newEventIn(Event event) {
        intensitySignal = event.getIntensitySignal();
    }
    public void generateQuorum(Random rand) {
        /*
        double low = 100*pFalseP;
        int lowerBound = (int)low;
        double high = 100*pTrueP;
        int higherBound = (int)high;
        quorumThreshold = lowerBound + rand.nextInt(higherBound-lowerBound);
        oldQuorum = quorumThreshold;
        */
        quorumThreshold = pFalseP + (pTrueP - pFalseP) * rand.nextDouble();
        //System.out.println(quorumThreshold);

    }
    public void setQuorumThreshold(int q) {
        Random rand = new Random();
        quorumThreshold = q + rand.nextInt(20);
    }
    public void makeFirstDecision(boolean state) {
        UniformRealDistribution distribution = new UniformRealDistribution(0,1);
        double sample = distribution.sample();
        //System.out.println(sample);
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
        //System.out.println(firstDecision);
    }
    public void makeFirstDecision() {
        if(intensitySignal >= escapeThreshold)
            firstDecision = true;
        else
            firstDecision = false;
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
            quorumThreshold = quorumThreshold + 0.01;
        else if(secondDecision == false && realState == true)
            quorumThreshold = quorumThreshold - 0.01;
    }




    public double getOldQuorum() {
        return oldQuorum;
    }

    public boolean getFirstDecision() {
        return firstDecision;
    }

    public boolean getSecondDecision() {
        return secondDecision;
    }
    public double getIntensitySignal() {
        return intensitySignal;
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