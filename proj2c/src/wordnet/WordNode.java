package wordnet;

import java.util.List;
import java.util.Objects;

public class WordNode {
    private final List<String> words;
    private final String definition;

    public WordNode(int index, List<String> words, String definition) {
        this.words = words;
        this.definition = definition;
    }

    public List<String> getWords() {
        return words;
    }

    public String getDefinition() {
        return definition;
    }

    @Override
    public String toString() {
        return words + "," + definition;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WordNode wordNode = (WordNode) o;
        return Objects.equals(words, wordNode.words) && Objects.equals(definition, wordNode.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words, definition);
    }
}
