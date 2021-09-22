package humanRDF;

/*
https://www.codewars.com/kata/human-readable-duration-format?utm_source=newsletter&utm_medium=email&utm_campaign=java_fejleszto_probafeladat&utm_term=2021-09-20
*/


import java.util.*;

import static humanRDF.TimeConverterUtils.getTheDateInAMap;


public class TimeFormatter {

    public static final String COMMON_SEPARATOR = ", ";
    public static final String FINAL_SEPARATOR = " and ";
    public static final String THE_NUMBER_OF_SECOND_IS_ZERO = "now";

    private TimeFormatter() {
        throw new IllegalStateException("(Utility class");
    }

    public static String formatDuration(int numberToBeConverted) {
        validate(numberToBeConverted);

        if (isTheNumberToBeConvertedZero(numberToBeConverted)) return THE_NUMBER_OF_SECOND_IS_ZERO;

        Map<Units, Integer> readableDate = getTheDateInAMap(numberToBeConverted);

        return toStringFromMap(readableDate);
    }

    private static boolean isTheNumberToBeConvertedZero(int numberToBeConverted) {
        return numberToBeConverted == 0;
    }


    private static void validate(int i) {
        if (i < 0) {
            throw new IllegalTimeFormatException("Time cannot be negative!");
        }
    }

    private static String toStringFromMap(Map<Units, Integer> map) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Units> unitsFromMap = getUnitsFromMap(map);
        String justOneValueIsNotZero = oneUnitOnly(map, stringBuilder);
        if (!stringBuilder.isEmpty()) {
            return justOneValueIsNotZero;
        }
        appendUnitWithCommonSeparator(map, stringBuilder, unitsFromMap);
        stringBuilder.append(FINAL_SEPARATOR);
        appendUnit(map, stringBuilder, unitsFromMap.size() - 1);

        return stringBuilder.toString();
    }

    private static void appendUnitWithCommonSeparator(Map<Units, Integer> map, StringBuilder stringBuilder, List<Units> fromMap) {
        for (int i = 0; i < fromMap.size() - 1; i++) {
            appendUnit(map, stringBuilder, i);
            appendCommonSeparator(fromMap, stringBuilder, i);
        }
    }

    private static List<Units> getUnitsFromMap(Map<Units, Integer> map) {
        return map.keySet().stream().toList();
    }

    private static void appendUnit(Map<Units, Integer> map, StringBuilder stringBuilder, int indexOfUnit) {
        List<Units> unitsFromMap = getUnitsFromMap(map);
        stringBuilder.append(String.format("%d %s", map.get(unitsFromMap.get(indexOfUnit)),
                (map.get(unitsFromMap.get(indexOfUnit)) == 1) ? unitsFromMap.get(indexOfUnit)
                        .getNameOfTheUnit() : unitsFromMap.get(indexOfUnit).getNameOfTheUnit() + "s"));
    }

    private static void appendCommonSeparator(List<Units> units, StringBuilder stringBuilder, int indexOfUnit) {
        if (indexOfUnit < units.size() - 2) {
            stringBuilder.append(COMMON_SEPARATOR);
        }
    }

    private static String oneUnitOnly(Map<Units, Integer> map, StringBuilder stringBuilder) {
        List<Units> unitsFromMap = getUnitsFromMap(map);
        if (unitsFromMap.size() == 1) {
            appendUnit(map, stringBuilder, 0);
        }

        return stringBuilder.toString();
    }
}

