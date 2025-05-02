package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import wordnet.WordNet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordNet wordNet;

    public HyponymsHandler() {
        super();
        wordNet = new WordNet("data/wordnet/synsets16.txt", "data/wordnet/hyponyms16.txt");
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        if (words.isEmpty()) {
            return "No words provided.";
        }
        StringBuilder result = new StringBuilder();
        String word = words.get(0);
        Set<String> hyponymsSet = wordNet.getWordSet(word);
        ArrayList<String> hyponyms = new ArrayList<>(hyponymsSet);
        hyponyms.sort(String::compareTo);
        if (hyponyms.isEmpty()) {
            result.append("No hyponyms found for ").append(word).append(".\n");
        } else {
            result.append("Hyponyms of ").append(word).append(": ").append(hyponyms).append("\n");
        }
        return result.toString();
    }
}
