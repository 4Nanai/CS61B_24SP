package wordnet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordNetTest {
    private static WordNet wordNet;
    private static Map<String, List<Integer>> wordToIndex;
    private static Map<Integer, WordNode> indexToNode;
    private static Map<Integer, LinkedList<Integer>> edges;
    @BeforeAll
    static void setUp() {
        // Initialize the WordNet instance with test files
        wordNet = assertDoesNotThrow(() -> new WordNet("data/wordnet/synsets11.txt", "data/wordnet/hyponyms11.txt"));
        wordToIndex = wordNet.wordToIndex;
        indexToNode = wordNet.indexToNode;
        edges = wordNet.edges;
    }

    @Test
    void testReadFiles() {
        assertTrue(wordToIndex.get("jump").containsAll(List.of(4, 6)));
        assertEquals("[jump, parachuting],dummy", indexToNode.get(4).toString());
        assertTrue(edges.get(5).containsAll(List.of(6, 7)));
        assertFalse(edges.get(0).contains(2));
    }

    @Test
    void testDFS() {
        Set<String> descentSet = wordNet.getWordSet("descent");
        assertTrue(descentSet.containsAll(List.of("descent", "jump", "parachuting")));
        assertFalse(descentSet.contains("leap"));
        Set<String> leapSet = wordNet.getWordSet("leap");
        assertTrue(leapSet.containsAll(List.of("leap", "jump")));
        assertFalse(leapSet.contains("descent"));
        Set<String> actionSet = wordNet.getWordSet("action");
        assertTrue(actionSet.containsAll(List.of("action", "change", "demotion")));
    }
}