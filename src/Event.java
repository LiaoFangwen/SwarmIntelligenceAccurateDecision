import org.apache.commons.math3.distribution.NormalDistribution;

public class Event {
    private boolean state;
    private double intensitySignal = 0;
    public Event(boolean state, NormalDistribution[] distributions) {
        this.state = state;
        if(state)
            intensitySignal = distributions[1].sample();
        else
            intensitySignal = distributions[0].sample();
       // System.out.println(state);
        //System.out.println(intensitySignal);
    }
    public boolean getState() {
        return state;
    }
    public double getIntensitySignal() {
        return intensitySignal;
    }
}
