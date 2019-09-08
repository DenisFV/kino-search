package com.example.kino.config.util;

import com.example.kino.entity.MovieEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParsingMovies {

    private static final Logger logger = LogManager.getLogger(ParsingMovies.class);
    private static boolean startParse = true;
    private static final Pattern pattern = Pattern.compile(
            "(\\d+)\\.\\s" + //1.
                    "(([А-ЯЁа-яёa-zA-Z]|\\d|\\+|,|\"|'|`|«|»|:|·|é|è|\\s)+)" + //Побег из Шоушенка
                    "\\((\\d+)\\)\\s" + //(1994)
                    "(([А-ЯЁа-яёa-zA-Z]|\\d|\\+|,|\"|'|`|«|»|:|·|é|è|\\s)+)?" + //The Shawshank Redemption
                    "(\\d\\.\\d+)\\s" + //9.190
                    "\\((\\d+\\s?\\d+)\\)"); //515 205


    private static Document getCheckedUrl(LocalDate date){
        String url = startParse ? "https://www.kinopoisk.ru/top" : "https://www.kinopoisk.ru/top/day/" + date; //2019-09-07
        startParse = false;

        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            logger.error("Ошибка при загрузке страницы");
            return null;
        }
    }

    public static List<MovieEntity> parse(LocalDate date) {
        List<MovieEntity> movieEntities = new ArrayList<>();

        Document document = getCheckedUrl(date);
        if (document == null) return movieEntities;

        for (int i = 1, fl = 1; fl <= 10; i++) {
            String question = document.select("#top250_place_" + i).text();
            logger.info("movie #" + fl + ": " + question);

            Matcher m = pattern.matcher(question);
            if (m.matches()) {
                long topPosition = Long.parseLong(m.group(1));
                String originalName = (m.group(5) == null ? m.group(2) : m.group(5)).trim();
                int movieYear = Integer.parseInt(m.group(4));
                double rating = Double.parseDouble(m.group(7));
                long voted = Long.parseLong(m.group(8).replace(" ", ""));

                movieEntities.add(new MovieEntity(topPosition, originalName, movieYear, rating, voted, date));
                fl++;
            } else logger.error("Невозможно распознать фильм \"" + question + "\". Проверьте выражение regex");
        }
        return movieEntities;
    }
}
