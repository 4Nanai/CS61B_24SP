package wordnet;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    private final Map<String, List<Integer>> wordToIndex;
    private final Map<Integer, WordNode> indexToNode;
    private final Map<Integer, LinkedList<Integer>> edges;

    public WordNet(String synFileName, String hypFileName) {
        wordToIndex = new HashMap<>();
        indexToNode = new HashMap<>();
        edges = new HashMap<>();
        In synFile = new In(synFileName);
        while (synFile.hasNextLine()) {
            String line = synFile.readLine();
            String[] tokens = line.split(",");
            if (tokens.length != 3) {
                throw new IllegalArgumentException("Invalid synset line: " + line);
            }
            int index = Integer.parseInt(tokens[0]);
            String definition = tokens[2];
            ArrayList<String> words = new ArrayList<>(Arrays.asList(tokens[1].split(" ")));
            words.forEach(word -> {
                wordToIndex.computeIfAbsent(word, k -> new ArrayList<>()).add(index);
            });
            indexToNode.put(index, new WordNode(index, words, definition));
        }
        In hypFile = new In(hypFileName);
        while (hypFile.hasNextLine()) {
            String line = hypFile.readLine();
            String[] tokens = line.split(",");
            if (tokens.length < 1) {
                continue;
            }
            int index = Integer.parseInt(tokens[0]);
            LinkedList<Integer> list = edges.computeIfAbsent(index, k -> new LinkedList<>());
            for (int i = 1; i < tokens.length; i++) {
                list.add(Integer.parseInt(tokens[i]));
            }
        }
    }

    public Map<String, List<Integer>> getWordToIndex() {
        return wordToIndex;
    }

    public Map<Integer, WordNode> getIndexToNode() {
        return indexToNode;
    }

    public Map<Integer, LinkedList<Integer>> getEdges() {
        return edges;
    }
}
