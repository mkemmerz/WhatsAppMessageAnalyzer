package Chart;

import Util.WamDataStructure;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.LinkedList;

/**
 * This chart describes the amount of message per month since the communication between the 2 communicators started.
 * X-Axis shows the amount of messages per month. Y-Axis shows the days since the first communication started.
 * 'SCS' in the class name stands for 'since communication started'.
 */
public class MonthlyMessagesSCSChart extends ApplicationFrame
{
    private final static String TITLE_CHART = "WhatsApp messages";
    private final static int SQUARE_CHART_PANEL = 650;
    private final static String X_AXIS_TITLE     = "Days since communication started";
    private final static String Y_AXIS_TITLE     = "Amount of messages per Month";
    private final static String XYSERIES_TITLE   = "Message per Month";
    private final static String FRAME_TITLE      = "Monthly messages";

    public MonthlyMessagesSCSChart(LinkedList<WamDataStructure> data)
    {
        super(FRAME_TITLE);

        final XYSeries series = new XYSeries(XYSERIES_TITLE);

        for (final WamDataStructure tempData : data)
        {
            series.add(tempData.getDaysSinceCommunicationStarted(), tempData.getAmountOfMessages());
        }

        final XYSeriesCollection coll = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(TITLE_CHART,
                X_AXIS_TITLE,Y_AXIS_TITLE,
                coll, PlotOrientation.VERTICAL, true, true, false);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(SQUARE_CHART_PANEL, SQUARE_CHART_PANEL));
        this.add(chartPanel, BorderLayout.CENTER);
        setContentPane(chartPanel);
    }
}
