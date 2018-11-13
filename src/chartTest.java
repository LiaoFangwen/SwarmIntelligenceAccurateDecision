import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Series;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class chartTest {
    public static void main(String args[]) {
        XYSeriesCollection dataSet = new XYSeriesCollection();
        XYSeries aa = new XYSeries("a");
        XYSeries bb = new XYSeries("b");
        for(int i = 0; i<10; i++) {
            aa.add(i, i);
        }
        for(int i = 0; i<10; i++) {
            bb.add(i/2, i/3);
        }
        dataSet.addSeries(aa);
        dataSet.addSeries(bb);
        /*
        for(int i =0; i<2; i++) {
            for(int j = 0; j<2; j++) {
                datas[i][j] = i+j;

            }
            dataSet.addSeries(i, datas);
        }
        */
        JFreeChart chart = ChartFactory.createScatterPlot("a", "b", "c", dataSet,
                PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frame = new ChartFrame("tu", chart, true);
        XYPlot plot = chart.getXYPlot();
        ValueAxis x = plot.getDomainAxis();
        x.setRange(0.0, 5.0);
        ValueAxis y = plot.getRangeAxis();
        y.setRange(0.0, 5.0);
        frame.pack();
        frame.setVisible(true);

    }
}
