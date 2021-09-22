package humanRDF;

import java.util.*;

public class TimeConverterUtils {

    private static final List<Units> UNITS = Arrays.stream(Units.values()).toList();

    private TimeConverterUtils() {
        throw new IllegalStateException("(Utility class");
    }


    public static Map<Units, Integer> getTheDateInAMap(int numberToBeConverted) {
        Map<Units, Integer> readableDate = new TreeMap<>();
        calculateTheValueOfTheHighestUnit(readableDate, numberToBeConverted);
        for (int i = 1; i < UNITS.size(); i++) {
            calculateTheValueOfTheUnit(numberToBeConverted, readableDate, i);
        }
        return readableDate;
    }

    //The highest unit means the unit, where the convertNumberToSecond  is the highest.
    private static void calculateTheValueOfTheHighestUnit(Map<Units, Integer> readableDate, int numberToBeConverted) {
        int numberOfYear = numberToBeConverted / UNITS.get(0).getConvertNumberToSecond();
        putTheUnitAndTheValueToTheMap(readableDate, 0, numberOfYear);
    }

    private static void calculateTheValueOfTheUnit(int numberToBeConverted, Map<Units, Integer> readableDate, int indexOfTheUnit) {
        int value = (numberToBeConverted % UNITS.get(indexOfTheUnit - 1).getConvertNumberToSecond()) / UNITS.get(indexOfTheUnit).getConvertNumberToSecond();
        putTheUnitAndTheValueToTheMap(readableDate, indexOfTheUnit, value);

    }

    private static void putTheUnitAndTheValueToTheMap(Map<Units, Integer> readableDate, int indexOfTheUnit, int value) {
        if ((value) != 0) {
            readableDate.put(UNITS.get(indexOfTheUnit), value);
        }
    }

}
