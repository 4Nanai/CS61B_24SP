package ngrams;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    Map<String, TimeSeries> wordMap;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        wordMap = new HashMap<>();
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries returnTS = new TimeSeries(wordMap.get(word), startYear, endYear);
        return returnTS;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries returnTS = new TimeSeries(wordMap.get(word), TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
        return returnTS;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries returnTS = new TimeSeries();
        for (TimeSeries ts : wordMap.values()) {
            returnTS = returnTS.plus(ts);
        }
        return returnTS;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries wordTS = new TimeSeries(wordMap.get(word), startYear, endYear);
        TimeSeries returnTS = wordTS.dividedBy(totalCountHistory());
        return returnTS;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries returnTS = wordMap.get(word).dividedBy(totalCountHistory());
        return returnTS;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries returnTS = new TimeSeries();
        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                continue;
            }
            returnTS = returnTS.plus(weightHistory(word, startYear, endYear));
        }
        return returnTS;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries returnTS = new TimeSeries();
        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                continue;
            }
            returnTS = returnTS.plus(weightHistory(word));
        }
        return returnTS;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
