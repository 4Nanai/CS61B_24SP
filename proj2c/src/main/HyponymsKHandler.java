package main;

import browser.NgordnetQuery;
import ngrams.NGramMap;
import wordnet.WordNet;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class HyponymsKHandler extends HyponymsHandler {
    private final NGramMap ngm;

    public HyponymsKHandler(WordNet wordNet, NGramMap ngm) {
        super(wordNet);
        this.ngm = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        int k = q.k();
        if (k == 0) {
            return super.handle(q);
        }
        StringBuilder sb = new StringBuilder();
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        Set<String> stringSet = this.getStringSet(words, q.ngordnetQueryType());
        List<String> stringList = stringSet
                .stream()
                .sorted(
                        Comparator.comparingDouble(
                                (String word) ->
                                ngm.countHistory(word, startYear, endYear)
                                .values()
                                .stream()
                                .mapToDouble(e -> e)
                                .sum())
                        .reversed())
                .limit(5)
                .sorted(String::compareTo)
                .toList();
        int size = Math.min(k, stringList.size());
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(stringList.get(i));
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
