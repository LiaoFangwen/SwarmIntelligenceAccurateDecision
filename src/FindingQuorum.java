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

public class FindingQuorum {
    public static void main(String args[]) {
        EventGroup eventGroup = new EventGroup(-2,2,2,2,10000);
        AgentGroup agentGroup = new AgentGroup(100, eventGroup);
        agentGroup.findingQuorum();
        showChart(agentGroup.getResultList());


            //System.out.println("State: " + state);
            //System.out.println("Total: " + totalDecisionNumber);

                //if(j == 0 || j == eventNumber-1)
                    //System.out.println("before: " + agents[l].getOldQuorum() +
                           //" first decision: " + agents[l].getFirstDecision() +
                           // " after: " + agents[l].getQuorumThreshold());
                          //  " second decision: " + agents[l].getSecondDecision() +
                          //  " escape: " + agents[l].getEscapeThreshold() +
                           // " signal: " + agents[l].getIntensitySignal());


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
        JFreeChart chartM = ChartFactory.createScatterPlot("results", "event numbers",
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

        JFreeChart chartSD = ChartFactory.createScatterPlot("results", "event numbers",
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


}
