import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FindingQuorum {
    public static void main(String args[]) {
        Random eventRand = new Random(1);
        Random escapeRand = new Random(10);
        Random ptruepRand = new Random(20);
        Random pfalsepRand = new Random(25);
        Random quorumRand = new Random(100);
        int eventNumber = 10000;
        int agentNumber = 100;
        double totalDecisionNumber;
        boolean state;
        double[] quorumSet = new double[agentNumber];
        ArrayList<double[]> resultList = new ArrayList<>();
        Event[] events = new Event[eventNumber];
        for(int m = 0; m<eventNumber; m++) {
            events[m] = new Event(-1,1,2,2,eventRand.nextBoolean());
        }
        Agent[] agents = new Agent[agentNumber];
        for(int i = 0; i<agentNumber; i++) {
            agents[i] = new Agent(0.4, 0.8);
            agents[i].generateQuorum(quorumRand);
            //System.out.println(agents[i].getQuorumThreshold());
        }
        /*
        for(int i = 0; i<agentNumber; i++) {
            agents[i] = new Agent(-10+20*escapeRand.nextDouble(),0.8*pfalsepRand.nextDouble(),
                    0.9+0.1*ptruepRand.nextDouble());
            agents[i].generateQuorum(quorumRand);
        }
        */


        for(int j = 0; j<eventNumber; j++) {
            state = events[j].getState();
            totalDecisionNumber = 0;
            for(int k = 0; k<agentNumber; k++) {
                //agents[k].newEventIn(events[j]);
                agents[k].makeFirstDecision(state);
                totalDecisionNumber = totalDecisionNumber + agents[k].submitFirstDecision();
            }

            //System.out.println("State: " + state);
            //System.out.println("Total: " + totalDecisionNumber);
            for(int l = 0; l<agentNumber; l++) {
                double total = totalDecisionNumber/(double)agentNumber;
                //System.out.println(total);
                agents[l].makeSecondDecision(total);
                agents[l].learn(state);
                quorumSet[l] = agents[l].getQuorumThreshold();
                //if(j == 0 || j == eventNumber-1)
                    //System.out.println("before: " + agents[l].getOldQuorum() +
                           //" first decision: " + agents[l].getFirstDecision() +
                           // " after: " + agents[l].getQuorumThreshold());
                          //  " second decision: " + agents[l].getSecondDecision() +
                          //  " escape: " + agents[l].getEscapeThreshold() +
                           // " signal: " + agents[l].getIntensitySignal());

            }
        resultList.add(calculateDeviation(quorumSet));
        }
        showChart(resultList);
    }
    public static void showChart(ArrayList<double[]> list) {
        XYSeriesCollection dataSetM = new XYSeriesCollection();
        XYSeriesCollection dataSetSD = new XYSeriesCollection();
        XYSeries meanValues = new XYSeries("mean");
        XYSeries sdValues = new XYSeries("standard deviation");
        Iterator<double[]> iterator = list.iterator();
        int xValue = 0;
        while (iterator.hasNext()) {
            double[] set = iterator.next();
            meanValues.add(xValue, set[0]);
            sdValues.add(xValue, set[1]);
            xValue++;
        }
        dataSetM.addSeries(meanValues);
        dataSetSD.addSeries(sdValues);
        JFreeChart chartM = ChartFactory.createScatterPlot("results", "agent numbers",
                "", dataSetM,
                PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frameM = new ChartFrame("frame", chartM, true);
        XYPlot plotM = chartM.getXYPlot();
        ValueAxis x = plotM.getDomainAxis();
        x.setRange(0.0, xValue);
        ValueAxis y = plotM.getRangeAxis();
        y.setRange(0.0, 1.0);
        frameM.pack();
        frameM.setVisible(true);

        JFreeChart chartSD = ChartFactory.createScatterPlot("results", "agent numbers",
                "", dataSetSD,
                PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frameSD = new ChartFrame("frame", chartSD, true);
        XYPlot plotSD = chartSD.getXYPlot();
        ValueAxis x1 = plotSD.getDomainAxis();
        x1.setRange(0.0, xValue);
        ValueAxis y1 = plotSD.getRangeAxis();
        double[] r = list.get(0);
        double range = r[1];
        y1.setRange(0.0, 0.1);
        y1.setRange(0.0, range);
        frameSD.pack();
        frameSD.setVisible(true);

    }
    public static double[] calculateDeviation(double quorumSet[]) {
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
}
