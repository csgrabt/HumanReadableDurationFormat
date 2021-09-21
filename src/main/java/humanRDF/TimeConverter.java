package humanRDF;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimeConverter {

    private static List<Units> units = Arrays.stream(Units.values()).toList();

    public static Map<Units, Integer> getTheDateInAMap(int numberToBeConverted) {
        Map<Units, Integer> readableDate = new TreeMap<>();
        calculateNumberOfYear(readableDate, numberToBeConverted);
        for (int i = 1; i < units.size(); i++) {
            calculateTheValueOfTheUnit(numberToBeConverted, readableDate, i);
        }
        return readableDate;
    }

    private static void calculateNumberOfYear(Map<Units, Integer> readableDate, int numberToBeConverted) {
        int numberOfYear = numberToBeConverted / units.get(0).getConvertNumberToSecond();
        putTheValueToTheMap(readableDate, 0, numberOfYear);
    }

    private static void calculateTheValueOfTheUnit(int numberToBeConverted, Map<Units, Integer> readableDate, int indexOfTheUnit) {
        int value = (numberToBeConverted % units.get(indexOfTheUnit - 1).getConvertNumberToSecond()) / units.get(indexOfTheUnit).getConvertNumberToSecond();
        putTheValueToTheMap(readableDate, indexOfTheUnit, value);

    }

    private static void putTheValueToTheMap(Map<Units, Integer> readableDate, int indexOfTheUnit, int value) {
        if ((value) != 0) {
            readableDate.put(units.get(indexOfTheUnit), value);
        }
    }

}
