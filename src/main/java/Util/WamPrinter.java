package Util;

import Counter.DateCounter;
import Counter.MonthCounter;

import java.util.List;

public class WamPrinter
{

    public static void printCounterPerMonth(final List<WamDataStructure> list)
    {
        System.out.println("----------------------------------------------");
        int countOfMonths = 0;
        for(WamDataStructure temp: list)
        {
            countOfMonths += temp.getAmountOfMessages();
            System.out.println(temp.getMonth() + ", " + temp.getYear() + ", " + temp.getAmountOfMessages() + ", " + temp.getDaysSinceCommunicationStarted());
        }
        System.out.println("Average messages per month: " + countOfMonths / list.size());
    }

    public static void printMessagesPerMonthAndYear(final MonthCounter monthCounter)
    {
        System.out.println("---------------------------");
        printMessagesForSpecificMonthsAndYear(11, 12, 2015, monthCounter);
        printMessagesForSpecificMonthsAndYear(1, 12, 2016, monthCounter);
        printMessagesForSpecificMonthsAndYear(1, 12, 2017, monthCounter);
        printMessagesForSpecificMonthsAndYear(1, 12, 2018, monthCounter);
        System.out.println("---------------------------");
    }

    public static void printMessagesForSpecificMonthsAndYear(int startMonth, final int endMonth,
                                                             final int year, final MonthCounter monthCounter)
    {
        while(startMonth < endMonth)
        {
            int amount = monthCounter.getAmountOfMessageForMonthAndYear(startMonth, year);
            System.out.println("Month: " + startMonth + ", Year: " + year + ", Amount of messages: " + amount);
            startMonth++;
        }
    }

    public static void printResult(List<DateCounter> list)
    {
        for(DateCounter tempCounter : list)
        {
            System.out.println(tempCounter.toString());
        }
    }
}
