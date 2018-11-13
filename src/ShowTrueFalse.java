import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ShowTrueFalse {
    public static void main(String args[]) {
        XYSeriesCollection dataSet = new XYSeriesCollection();
        XYSeries fptp = new XYSeries("False and Ture Positive");
        Event test1 = new Event(-1, 1, 2, 2, true);
        for(double i = -100; i < 100; i=i+0.1) {
            double x = 1-test1.getDangerousProbability(i);
            double y = 1-test1.getSafeProbability(i);
            fptp.add(x, y);
        }
        dataSet.addSeries(fptp);
        JFreeChart chart = ChartFactory.createScatterPlot("False and True Positive", "False",
                "True", dataSet,
                PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frame = new ChartFrame("frame", chart, true);
        XYPlot plot = chart.getXYPlot();
        ValueAxis x = plot.getDomainAxis();
        x.setRange(0.0, 1.0);
        ValueAxis y = plot.getRangeAxis();
        y.setRange(0.0, 1.0);
        frame.pack();
        frame.setVisible(true);
    }
}
