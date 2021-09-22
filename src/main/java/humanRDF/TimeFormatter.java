package humanRDF;

import java.util.*;

import static humanRDF.TimeConverterUtils.getTheDateInAMap;


public class TimeFormatter {

    public static final String COMMON_SEPARATOR = ", ";
    public static final String FINAL_SEPARATOR = " and ";
    public static final String MESSAGE_NOW = "now";
    public static final String THE_SING_OF_THE_PLURAL = "s";

    private TimeFormatter() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatDuration(int numberToBeConverted) {
        validate(numberToBeConverted);
        if (isTheNumberToBeConvertedZero(numberToBeConverted)) return MESSAGE_NOW;
        Map<Units, Integer> readableDate = getTheDateInAMap(numberToBeConverted);
        return toStringFromMap(readableDate);
    }

    private static boolean isTheNumberToBeConvertedZero(int numberToBeConverted) {
        return numberToBeConverted == 0;
    }


    private static void validate(int numberToBeConverted) {
        if (numberToBeConverted < 0) {
            throw new IllegalTimeFormatException("Time cannot be negative!");
        }
    }

    private static String toStringFromMap(Map<Units, Integer> readableDate) {
        StringBuilder humanReadableDate = new StringBuilder();
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        appendIfOnlyOneUnitValuePairInTheMap(readableDate, humanReadableDate);
        if (!humanReadableDate.isEmpty()) {
            return humanReadableDate.toString();
        }
        appendUnitAndValueWithCommonSeparator(readableDate, humanReadableDate);
        humanReadableDate.append(FINAL_SEPARATOR);
        appendUnitAndValue(readableDate, humanReadableDate, unitsFromMap.size() - 1);

        return humanReadableDate.toString();
    }

    private static void appendUnitAndValueWithCommonSeparator(Map<Units, Integer> readableDate, StringBuilder humanReadableDate) {
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        for (int i = 0; i < unitsFromMap.size() - 1; i++) {
            appendUnitAndValue(readableDate, humanReadableDate, i);
            appendCommonSeparator(unitsFromMap, humanReadableDate, i);
        }
    }

    private static List<Units> getUnitsFromMap(Map<Units, Integer> readableDate) {
        return readableDate.keySet().stream().toList();
    }

    private static void appendUnitAndValue(Map<Units, Integer> readableDate, StringBuilder humanReadableDate, int indexOfUnit) {
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        humanReadableDate.append(String.format("%d %s", readableDate.get(unitsFromMap.get(indexOfUnit)),
                (readableDate.get(unitsFromMap.get(indexOfUnit)) == 1) ? unitsFromMap.get(indexOfUnit)
                        .getNameOfTheUnit() : unitsFromMap.get(indexOfUnit).getNameOfTheUnit() + THE_SING_OF_THE_PLURAL));
    }

    private static void appendCommonSeparator(List<Units> unitsFromMap, StringBuilder humanReadableDate, int indexOfUnit) {
        if (indexOfUnit < unitsFromMap.size() - 2) {
            humanReadableDate.append(COMMON_SEPARATOR);
        }
    }

    private static void appendIfOnlyOneUnitValuePairInTheMap(Map<Units, Integer> readableDate, StringBuilder humanReadableDate) {
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        if (unitsFromMap.size() == 1) {
            appendUnitAndValue(readableDate, humanReadableDate, 0);
        }
    }
}

