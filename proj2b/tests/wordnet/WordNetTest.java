package wordnet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordNetTest {
    private static Map<String, List<Integer>> wordToIndex;
    private static Map<Integer, WordNode> indexToNode;
    private static Map<Integer, LinkedList<Integer>> edges;
    @BeforeAll
    static void setUp() {
        // Initialize the WordNet instance with test files
        WordNet wordNet = assertDoesNotThrow(() -> new WordNet("data/wordnet/synsets11.txt", "data/wordnet/hyponyms11.txt"));
        wordToIndex = wordNet.getWordToIndex();
        indexToNode = wordNet.getIndexToNode();
        edges = wordNet.getEdges();
    }

    @Test
    void testReadFiles() {
        assertTrue(wordToIndex.get("jump").containsAll(List.of(4, 6)));
        assertEquals("[jump, parachuting],dummy", indexToNode.get(4).toString());
    }
}