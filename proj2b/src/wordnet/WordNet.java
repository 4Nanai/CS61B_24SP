package wordnet;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    protected final Map<String, List<Integer>> wordToIndex;
    protected final Map<Integer, WordNode> indexToNode;
    protected final Map<Integer, LinkedList<Integer>> edges;

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

    // Simple dfs to find all words that are connected to the given word
    public Set<String> getWordSet(String word) {
        Set<String> wordSet = new HashSet<>();
        List<Integer> indices = this.wordToIndex.get(word);
        if (indices == null) {
            return wordSet;
        }
        Stack<Integer> stk = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        for (Integer index : indices) {
            stk.push(index);
            visited.add(index);
        }
        while (!stk.isEmpty()) {
            Integer index = stk.pop();
            WordNode node = this.indexToNode.get(index);
            if (node == null) {
                continue;
            }
            wordSet.addAll(node.getWords());
            LinkedList<Integer> list = this.edges.get(index);
            if (list != null) {
                for (Integer childIndex : list) {
                    if (visited.contains(childIndex)) {
                        continue;
                    }
                    stk.push(childIndex);
                    visited.add(childIndex);
                }
            }
        }
        return wordSet;
    }
}
