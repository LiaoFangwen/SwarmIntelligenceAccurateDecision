import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.RandomDataGenerator;
public class Event {
    private NormalDistribution safeState;
    private NormalDistribution dangerousState;
    private boolean state;
    private double intensitySignal;
    public Event(double safeMean, double dangerousMean, double deviationS, double deviationD, boolean state) {
        safeState = new NormalDistribution(safeMean, deviationS);
        dangerousState = new NormalDistribution(dangerousMean, deviationD);
        this.state = state;
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        if(state == true)
            intensitySignal = randomDataGenerator.nextGaussian(dangerousMean, deviationD);
        if(state == false)
            intensitySignal = randomDataGenerator.nextGaussian(safeMean, deviationS);
    }

    public double getSafeProbability(double x) {
        return safeState.cumulativeProbability(x);
    }
    public double getDangerousProbability(double x) {
        return dangerousState.cumulativeProbability(x);
    }
    public boolean getState() {
        return state;
    }
    public double getIntensitySignal() {
        return intensitySignal;
    }
}
