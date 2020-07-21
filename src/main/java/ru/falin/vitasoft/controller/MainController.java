package ru.falin.vitasoft.controller;

import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @GetMapping("{id}")
    public String getMonthName(@PathVariable String id) {
        int number = 0;

        try {
            number = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return "INCORRECT INPUT DATA";
        }

        if (number > 0 && number < 13) {
            char[] litters = Month.of(number).getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"))
                    .toUpperCase()
                    .toCharArray();

            StringBuilder sb = new StringBuilder();
            sb.append(litters[0]);

            for (int i = 1; i < litters.length; i++)
                sb.append('-').append(litters[i]);

            return sb.toString();
        }

        return "INCORRECT INPUT DATA";
    }


    @PostMapping
    public List<String> sortStrings(@RequestBody List<String> strings) {
        strings.sort(Comparator.comparingInt(String::length).thenComparing(o -> o));
        return strings.stream()
                .map(s -> "(" + s.length() + "): " + s)
                .collect(Collectors.toList());
    }
}
