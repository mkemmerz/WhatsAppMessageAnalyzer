package Analyzer.impl;

import Analyzer.WamAnalyzer;
import Chart.*;
import Counter.*;
import java.time.LocalDate;

import Counter.DateCounter;
import Util.WamDataStructure;
import Util.WamPrinter;
import org.jfree.ui.RefineryUtilities;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class WamAnalyzerImpl implements WamAnalyzer
{
    private static final int DEFAULT_VALUE_COUNTER = 0;
    private static final int LENGHT_EMPTY_FILE = 0;

    private List<DateCounter> counterPerDay; /**< Counts the messages for a specific day of a year */
    private LinkedList<WamDataStructure> counterPerMonth;   /**< Counts the messages for a specific month of a year */

    private File chat;                              /**< The exported text file from WhatsApp */

    //The variables for iterating the chat file - line for line
    private String currentLine;                 /**< Holding the current line of the chat*/
    private LocalDate currentDate;              /**< Holding the current date of the chat line */
    private DateCounter currentDateCounter;     /**< The DateCounter for the current date of the chat line */
    private LocalDate lastDate;                 /**< Containing the date of the last read line from the chat */
    private int messagesPerMonth;               /**< Holding the current amount of messages for a specific month */
    private int counterDaysSinceComStarted;     /**< Counting the days between first and last message */
    private int totalMessages ;                 /**< The total amount of all messages for a chat protocol */
    private int daysWithoutCommuncation;        /**< Counting days where no message was sent */

    public WamAnalyzerImpl()
    {
        initializeAndResetAnalyzer();
    }

    public void analyzeWhatsApp(final String filepath)
    {
        //Clean up all left overs from last analyze
        initializeAndResetAnalyzer();

        chat = new File(filepath);      /**< The chat communication that will be analyzed. */
        if (!chat.exists() || chat.isDirectory() || chat.length() == LENGHT_EMPTY_FILE)
        {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(chat)))
        {
            String begin = br.readLine();               /**< The first line of the chat file */
            lastDate = DateCounter.buildDate(begin);    /**< The first date of the chat is also our last date*/
            currentDateCounter = new DateCounter(lastDate);

            //Get every line of the chat file
            while((currentLine = br.readLine()) != null)
            {
                currentDate = buildDateFromString(currentLine);
                if(currentDate == null)
                {
                    //an error occurred while creating the date from the chat line - continue with next line
                    continue;
                }

                //Are we still on the same day or has the day changed since the last line?
                if (!currentDate.equals(lastDate))
                {
                    aNewDayBegins();

                } else {
                    //We have another message on the same day - increase counter for this day
                    currentDateCounter.increaseCounter();
                }
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return;
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        WamPrinter.printResult(counterPerDay);
        System.out.println("Days since communication started: " + counterPerDay.size());

        buildCharts();

    }

    private void buildCharts()
    {
        //Messages per day - DailyMessagesChart
        final DailyMessagesChart demo = new DailyMessagesChart("WhatApp Messages Validation", counterPerDay);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

        //Messages per month - MonthlyMessagesSCSChart
        final MonthlyMessagesSCSChart demo2 = new MonthlyMessagesSCSChart(counterPerMonth);
        demo2.pack();
        RefineryUtilities.centerFrameOnScreen(demo2);
        demo2.setVisible(true);
        //----------------------------

        //Messages per month - with Date MonthlyMessagesChart
        MonthlyMessagesChart demo3 = new MonthlyMessagesChart(counterPerMonth);
        demo3.pack();
        RefineryUtilities.centerFrameOnScreen(demo3);
        demo3.setVisible(true);

        //Getting messages per month
        WamPrinter.printMessagesPerMonthAndYear(new MonthCounter(counterPerDay));
        WamPrinter.printCounterPerMonth(counterPerMonth);
        System.out.println("Days with zero messages: " + daysWithoutCommuncation);
        System.out.println("Average messages per day: " + averageAmountOfTextsPerDay());
    }

    /**
     * The methods process all steps if a new day starts in the chat.
     * It increases and sets all necessary counters and variables.
     */
    private void aNewDayBegins()
    {
        //Another day has begun
        counterDaysSinceComStarted++;

        if (currentDateCounter.getCounter() == DEFAULT_VALUE_COUNTER)
        {
            //We had a day without communication
            daysWithoutCommuncation++;
        }

        //Add messages of the day to the total communicated messages
        totalMessages = totalMessages + currentDateCounter.getCounter();
        messagesPerMonth += currentDateCounter.getCounter();

        //Counting all messages for a day finished so add the Counter to our Data
        counterPerDay.add(currentDateCounter);

        //Has a new month started?
        if (!isSameMonth(currentDate, lastDate))
        {
            aNewMonthBegins();
        }

        currentDateCounter = new DateCounter(currentDate);
        lastDate = currentDate;
    }

    /**
     * The methods process all steps if a new month starts in the chat.
     */
    private void aNewMonthBegins()
    {
        counterPerMonth.add(new WamDataStructure(lastDate.getMonthValue(), lastDate.getYear(),
                messagesPerMonth, counterDaysSinceComStarted));
        messagesPerMonth = DEFAULT_VALUE_COUNTER;
    }

    /**
     * Check if two Dates have the same Month.
     * @param firstDate     The first Date you want to check this
     * @param secondDate    The second Date you want to equal to the FirstDate
     * @return true if the Dates equal in their Month
     */
    private boolean isSameMonth(LocalDate firstDate, LocalDate secondDate)
    {
        return firstDate.getMonthValue() == secondDate.getMonthValue();
    }

    /**
     * Extracts the date when the message was sent out of a line from the chat file.
     *
     * @param currentLine The line of the chat you want to have the date for
     * @return The date, containing year, month and day, the message was sent
     */
    private LocalDate buildDateFromString(final String currentLine)
    {
       return DateCounter.buildDate(currentLine);

    }

    /**
     * Initializes all variables for this class with default values.
     * Guarantees a fresh analyze.
     */
    private void initializeAndResetAnalyzer()
    {
        counterPerDay               = new LinkedList<>();
        counterPerMonth             = new LinkedList<>();
        totalMessages               = DEFAULT_VALUE_COUNTER;
        daysWithoutCommuncation     = DEFAULT_VALUE_COUNTER;
        messagesPerMonth            = DEFAULT_VALUE_COUNTER;
        counterDaysSinceComStarted  = DEFAULT_VALUE_COUNTER;
    }

    public  int averageAmountOfTextsPerDay()
    {
        return (totalMessages / (counterPerDay.size()- daysWithoutCommuncation));
    }
}
