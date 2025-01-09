package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.List;
import java.util.Objects;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private final NGramMap wordsMap;

    public HistoryTextHandler(NGramMap map) {
        super();
        wordsMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        StringBuilder response = new StringBuilder();
        for (String word : words) {
            response.append(word).append(": ");
            String weightHistoryString = wordsMap.weightHistory(word, startYear, endYear).toString();
            response.append(!Objects.equals(weightHistoryString, "{}") ? weightHistoryString + "\n" : "Cannot find the word\n");
        }
        return response.toString();
    }
}
