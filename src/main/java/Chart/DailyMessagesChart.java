package Chart;

import Counter.DateCounter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.List;

/**
 * This method is able to display the daily amount of messages between the 2 communicators.
 * X-Axis shows the amount of messages per day. The Y-Axis shows the amount of days since
 * the first communication started.
 */
public class DailyMessagesChart extends ApplicationFrame
{
    //Constants for the chart
    private final static int SIZE_CHART_PANEL    =  650;
    private final static String TITLE_CHART      = "WhatsApp messages";
    private final static String X_AXIS_TITLE     = "Days since communication started";
    private final static String Y_AXIS_TITLE     = "Amount of messages";
    private final static String TITLE_XY_SERIES  = "Messages per Day";
    private final static int INITIALIZE_DATA_COUNTER  = 1;

    /**
     * Constructor of the DailyMessagesChart.
     *
     * @param title The title of the chart
     * @param data  The data you want to show in the chart
     */
    public DailyMessagesChart(final String title, final List<DateCounter> data)
    {
        super(title);
        initializeChart(data);
    }

    /**
     * Helper function that initializes the DailyMessagesChart.
     *
     * @param data The data passed by the constructor.
     */
    private void initializeChart(final List<DateCounter> data)
    {
        final XYSeries series = new XYSeries(TITLE_XY_SERIES);

        //Adding data to the chart
        addDataToXYSeries(data, series);

        //Finalizing the chart
        final XYSeriesCollection coll = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(TITLE_CHART, X_AXIS_TITLE,
                Y_AXIS_TITLE, coll, PlotOrientation.VERTICAL, true, true, false);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(SIZE_CHART_PANEL, SIZE_CHART_PANEL));
        this.add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Adds data to the chart that is shown later.
     * @param data      The data you want to add
     * @param series    The data will be added to this chart
     */
    private void addDataToXYSeries(final List<DateCounter> data, final XYSeries series)
    {
        int counter = INITIALIZE_DATA_COUNTER;
        //Iterating data and adding it to X and Y axises
        for (final DateCounter tempData : data)
        {
            series.add(counter, tempData.getCounter());
            counter++;
        }
    }
}
