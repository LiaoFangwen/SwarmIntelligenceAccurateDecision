import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ShowChart {
    public static void main(String args[]) {
/*
        XYSeriesCollection dataSet= new XYSeriesCollection();
        XYSeries truePositive = new XYSeries("true positive");
        XYSeries falsePositive = new XYSeries("false posirive");
        for(int i = 1; i<=50; i++)
            pTpCalculating(truePositive, i);
        for(int i = 1; i<=50; i++)
            pFpCalculating(falsePositive, i);
        dataSet.addSeries(truePositive);
        dataSet.addSeries(falsePositive);
        JFreeChart chart = ChartFactory.createScatterPlot("polling", "group size",
                "probability true and false positive", dataSet,
                PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frame = new ChartFrame("frame", chart, true);
        XYPlot plot = chart.getXYPlot();
        ValueAxis x = plot.getDomainAxis();
        x.setRange(0.0, 50.0);
        ValueAxis y = plot.getRangeAxis();
        y.setRange(0.0, 1.0);
        frame.pack();
        frame.setVisible(true);
    }
    public static void pTpCalculating(XYSeries truePositive, int number) {
        boolean pollResult;
        double sum = 0;
        Polling polling = new Polling(number, 0.45);
        double pTp[] = new double[1000];
        for(int k = 0; k<1000; k++) {
            for(int i = 0; i < 14; i++) {
                pollResult = polling.allParticipantsPoll(true);
                if (pollResult == true)
                    sum = sum + 1;
            }
            pTp[k] = sum/14;
            sum = 0;
        }
        double mean = 0;
        for(double p : pTp) {
            mean = mean + p;
        }
        truePositive.add(polling.getAgentNumber(), mean/1000);
    }
    public static void pFpCalculating(XYSeries falsePositive, int number) {
        boolean pollResult;
        double sum = 0;
        Polling polling = new Polling(number, 0.45);
        double pFp[] = new double[1000];
        for(int k = 0; k<1000; k++) {
            for(int i = 0; i < 14; i++) {
                pollResult = polling.allParticipantsPoll(false);
                if (pollResult == true)
                    sum = sum + 1;
            }
            pFp[k] = sum/14;
            sum = 0;
        }
        double mean = 0;
        for(double p : pFp) {
            mean = mean + p;
        }
        falsePositive.add(polling.getAgentNumber(), mean/1000);

    }
*/
}}
