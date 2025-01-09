package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private final NGramMap wordsMap;

    public HistoryHandler(NGramMap map) {
        this.wordsMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<TimeSeries> arrayTS = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for (String word : words) {
            arrayTS.add(wordsMap.weightHistory(word, startYear, endYear));
            labels.add(word);
        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, arrayTS);
        return Plotter.encodeChartAsString(chart);
    }
}
