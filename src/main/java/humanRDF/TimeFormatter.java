package humanRDF;

/*
https://www.codewars.com/kata/human-readable-duration-format?utm_source=newsletter&utm_medium=email&utm_campaign=java_fejleszto_probafeladat&utm_term=2021-09-20
*/


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
        StringBuilder stringBuilder = new StringBuilder();
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        appendIfOnlyOneUnitValuePairInTheMap(readableDate, stringBuilder);
        if (!stringBuilder.isEmpty()) {
            return stringBuilder.toString();
        }
        appendUnitAndValueWithCommonSeparator(readableDate, stringBuilder);
        stringBuilder.append(FINAL_SEPARATOR);
        appendUnitAndValue(readableDate, stringBuilder, unitsFromMap.size() - 1);

        return stringBuilder.toString();
    }

    private static void appendUnitAndValueWithCommonSeparator(Map<Units, Integer> readableDate, StringBuilder stringBuilder) {
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        for (int i = 0; i < unitsFromMap.size() - 1; i++) {
            appendUnitAndValue(readableDate, stringBuilder, i);
            appendCommonSeparator(unitsFromMap, stringBuilder, i);
        }
    }

    private static List<Units> getUnitsFromMap(Map<Units, Integer> map) {
        return map.keySet().stream().toList();
    }

    private static void appendUnitAndValue(Map<Units, Integer> readableDate, StringBuilder stringBuilder, int indexOfUnit) {
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        stringBuilder.append(String.format("%d %s", readableDate.get(unitsFromMap.get(indexOfUnit)),
                (readableDate.get(unitsFromMap.get(indexOfUnit)) == 1) ? unitsFromMap.get(indexOfUnit)
                        .getNameOfTheUnit() : unitsFromMap.get(indexOfUnit).getNameOfTheUnit() + THE_SING_OF_THE_PLURAL));
    }

    private static void appendCommonSeparator(List<Units> units, StringBuilder stringBuilder, int indexOfUnit) {
        if (indexOfUnit < units.size() - 2) {
            stringBuilder.append(COMMON_SEPARATOR);
        }
    }

    private static void appendIfOnlyOneUnitValuePairInTheMap(Map<Units, Integer> readableDate, StringBuilder stringBuilder) {
        List<Units> unitsFromMap = getUnitsFromMap(readableDate);
        if (unitsFromMap.size() == 1) {
            appendUnitAndValue(readableDate, stringBuilder, 0);
        }
    }
}

