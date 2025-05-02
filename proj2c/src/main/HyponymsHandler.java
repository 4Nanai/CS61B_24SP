package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import wordnet.WordNet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordNet wordNet;

    public HyponymsHandler(WordNet wordNet) {
        super();
        this.wordNet = wordNet;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        if (words.isEmpty()) {
            return "No words provided.";
        }
        StringBuilder result = new StringBuilder();
        Set<String> hyponymsSet = getStringSet(words, q.ngordnetQueryType());
        ArrayList<String> hyponyms = new ArrayList<>(hyponymsSet);
        hyponyms.sort(String::compareTo);
        result.append(hyponyms);
        return result.toString();
    }

    protected Set<String> getStringSet(List<String> words, NgordnetQueryType type) {
        Set<String> hyponymsSet = null;
        for (String word : words) {
            Set<String> wordSet = wordNet.getWordSet(word, type);
            if (hyponymsSet == null) {
                hyponymsSet = wordSet;
                continue;
            }
            Set<String> newSet = new HashSet<>();
            for (String w : wordSet) {
                if (hyponymsSet.contains(w)) {
                    newSet.add(w);
                }
            }
            hyponymsSet = newSet;
        }
        return hyponymsSet;
    }
}
