package Chart;

import Util.WamDataStructure;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 * This method is able to show the monthly amount of messages between the 2 communicators.
 * X-Axis shows the amount of messages per month. Y-Axis shows months of the year.
 */
public class MonthlyMessagesChart extends ApplicationFrame
{
    private final static String TITLE_CHART = "Messages per month";
    private final static int WIDTH_CHART_PANEL = 500;
    private final static int HEIGHT_CHART_PANEL = 270;
    private final static double RECTANGLE_VALUES = 5.0;

    private final static String TITLE_JF_CHART = "Monthly messages";
    private final static String X_AXIS_JF_CHART = "Time";
    private final static String Y_AXIS_JF_CHART = "Amount of messages per month";
    private final static String X_AXIS_JF_DATAFORMAT = "MMM-yyyy";

    public MonthlyMessagesChart(final LinkedList<WamDataStructure> data)
    {
        super(TITLE_CHART);
        XYDataset dataset = createDataset(data);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(WIDTH_CHART_PANEL, HEIGHT_CHART_PANEL));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    /**
     * Helper method that creates a XYDataset out of a List of WamDataStructure.
     * The conversion is needed for the JFreeChart.
     *
     * @param data  The data that shall be shown on the chart
     * @return The XYDataset, created out of the WamDataStructures
     */
    private XYDataset createDataset(final LinkedList<WamDataStructure> data)
    {
        TimeSeries ts = new TimeSeries(TITLE_CHART, Month.class);
        for (final WamDataStructure temp : data)
        {
            ts.add(new Month(temp.getMonth(), temp.getYear()), temp.getAmountOfMessages());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(ts);
        return dataset;
    }

    /**
     * Creates a JFFreeChart, showing a given Dataset.
     *
     * @param dataset  Data containing messages per days
     *
     * @return The chart, filled with the data.
     */
    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(TITLE_JF_CHART, X_AXIS_JF_CHART, Y_AXIS_JF_CHART,
                dataset,true,true,false);
        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = initializeXYPlot(chart);
        final DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat(X_AXIS_JF_DATAFORMAT));
        return chart;

    }

    /**
     * Helper function for initializing the XYPlot.
     *
     * @param chart The chart you want to have the XYPlot for.
     *
     * @return The initialized XYPlot
     */
    private XYPlot initializeXYPlot(final JFreeChart chart)
    {
        final XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(
                RECTANGLE_VALUES, RECTANGLE_VALUES, RECTANGLE_VALUES, RECTANGLE_VALUES));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        return plot;
    }
}
