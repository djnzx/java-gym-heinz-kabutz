package io.jgym.warmups.day17;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MonthsGrouping {
    public static void main(String... args) {
        List<String> months = List.of("January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October",
                "November", "December");

        var lastLetterToMonthsMap = iterateJava2(months);
//        var lastLetterToMonthsMap = iterateJava3(months);
//        var lastLetterToMonthsMap = iterateJava5Generics(months);
//        var lastLetterToMonthsMap = iterateJava5Autoboxing(months);
//        var lastLetterToMonthsMap = iterateJava5EnhancedFor(months);
//        var lastLetterToMonthsMap = iterateJava7DiamondOperator(months);
//        var lastLetterToMonthsMap = iterateJava8ComputeIfAbsent(months);
//        var lastLetterToMonthsMap = iterateJava8StreamGroupingBy(months);
//        var lastLetterToMonthsMap = iterateJava10ImmutableCollections(months);

        lastLetterToMonthsMap.entrySet().forEach(System.out::println);
    }


    /**
     * Java 1.2 - Old fashioned loopy looping
     */
    private static Map iterateJava2(List<String> months) {
        Map lastLetterToMonthsMap = new HashMap(); // Character, List of Strings
        Iterator it = months.iterator();
        while (it.hasNext()) {
            String month = (String) it.next();
            Character lastLetter = new Character(month.charAt(month.length() - 1));
            List groupedMonths = (List) lastLetterToMonthsMap.get(lastLetter);
            if (groupedMonths == null) {
                groupedMonths = new ArrayList();
                lastLetterToMonthsMap.put(lastLetter, groupedMonths);
            }
            groupedMonths.add(month);
        }
        return lastLetterToMonthsMap;
    }

    /**
     * Java 1.3 - Changing style from while() to for() loop - iterator variable is contained
     */
    private static Map iterateJava3(List<String> months) {
        Map lastLetterToMonthsMap = new HashMap();
        for (Iterator it = months.iterator(); it.hasNext(); ) {
            String month = (String) it.next();
            Character lastLetter = new Character(month.charAt(month.length() - 1));
            List groupedMonths = (List) lastLetterToMonthsMap.get(lastLetter);
            if (groupedMonths == null) {
                groupedMonths = new ArrayList();
                lastLetterToMonthsMap.put(lastLetter, groupedMonths);
            }
            groupedMonths.add(month);
        }
        return lastLetterToMonthsMap;
    }

    /**
     * Java 1.5 - Generics means we have to do less casting - but the code gets longer
     */
    private static Map<Character, List<String>> iterateJava5Generics(List<String> months) {
        Map<Character, List<String>> lastLetterToMonthsMap =
                new HashMap<Character, List<String>>();
        for (Iterator<String> it = months.iterator(); it.hasNext(); ) {
            String month = it.next();
            Character lastLetter = new Character(month.charAt(month.length() - 1));
            List<String> groupedMonths = lastLetterToMonthsMap.get(lastLetter);
            if (groupedMonths == null) {
                groupedMonths = new ArrayList<String>();
                lastLetterToMonthsMap.put(lastLetter, groupedMonths);
            }
            groupedMonths.add(month);
        }
        return lastLetterToMonthsMap;
    }

    /**
     * Java 1.5 - Autoboxing used to create the Character
     */
    private static Map<Character, List<String>> iterateJava5Autoboxing(List<String> months) {
        Map<Character, List<String>> lastLetterToMonthsMap =
                new HashMap<Character, List<String>>();
        for (Iterator<String> it = months.iterator(); it.hasNext(); ) {
            String month = it.next();
            Character lastLetter = month.charAt(month.length() - 1);
            List<String> groupedMonths = lastLetterToMonthsMap.get(lastLetter);
            if (groupedMonths == null) {
                groupedMonths = new ArrayList<String>();
                lastLetterToMonthsMap.put(lastLetter, groupedMonths);
            }
            groupedMonths.add(month);
        }
        return lastLetterToMonthsMap;
    }

    /**
     * Java 1.5 - Enhanced for loop
     */
    private static Map<Character, List<String>> iterateJava5EnhancedFor(List<String> months) {
        Map<Character, List<String>> lastLetterToMonthsMap =
                new HashMap<Character, List<String>>();
        for (String month : months) {
            Character lastLetter = month.charAt(month.length() - 1);
            List<String> groupedMonths = lastLetterToMonthsMap.get(lastLetter);
            if (groupedMonths == null) {
                groupedMonths = new ArrayList<String>();
                lastLetterToMonthsMap.put(lastLetter, groupedMonths);
            }
            groupedMonths.add(month);
        }
        return lastLetterToMonthsMap;
    }

    /**
     * Java 1.7 - Diamond operator for generics
     */
    private static Map<Character, List<String>> iterateJava7DiamondOperator(List<String> months) {
        Map<Character, List<String>> lastLetterToMonthsMap = new HashMap<>();
        for (String month : months) {
            Character lastLetter = month.charAt(month.length() - 1);
            List<String> groupedMonths = lastLetterToMonthsMap.get(lastLetter);
            if (groupedMonths == null) {
                groupedMonths = new ArrayList<>();
                lastLetterToMonthsMap.put(lastLetter, groupedMonths);
            }
            groupedMonths.add(month);
        }
        return lastLetterToMonthsMap;
    }

    /**
     * Java 8 - computeIfAbsent()
     */
    private static Map<Character, List<String>> iterateJava8ComputeIfAbsent(List<String> months) {
        Map<Character, List<String>> lastLetterToMonthsMap =
                new HashMap<>();
        for (String month : months) {
            Character lastLetter = month.charAt(month.length() - 1);
            List<String> groupedMonths =
                    lastLetterToMonthsMap.computeIfAbsent(
                            lastLetter, k -> new ArrayList<>());
            groupedMonths.add(month);
        }
        return lastLetterToMonthsMap;
    }

    /**
     * Java 8 - Iterating via stream
     */
    private static Map<Character, List<String>> iterateJava8StreamGroupingBy(List<String> months) {
        return months.stream()
                .collect(Collectors.groupingBy(
                        month -> month.charAt(month.length() - 1)));
    }

    /**
     * Java 10 - Making map and lists immutable
     */
    private static Map<Character, List<String>> iterateJava10ImmutableCollections(List<String> months) {
        return Map.copyOf(months.stream()
                .collect(Collectors.groupingBy(
                        month -> month.charAt(month.length() - 1),
                        Collectors.mapping(Function.identity(),
                                Collectors.toUnmodifiableList()))));

        // alternatively
        // return months.stream()
        //         .collect(Collectors.groupingBy(
        //                 month -> month.charAt(month.length() - 1),
        //                 Collectors.mapping(Function.identity(),
        //                         Collectors.toUnmodifiableList())))
        //         .entrySet().stream().collect(Collectors.toUnmodifiableMap(
        //                 Map.Entry::getKey, Map.Entry::getValue
        //         ));
    }
}