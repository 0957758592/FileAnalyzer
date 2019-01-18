package com.test.io.fileanalyzer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FileAnalyzerTest {
    private StringBuilder text = new StringBuilder("aaaaaaaaaaa aaaaaaaaaaaa aaaaaaaaaaaaaaaaaaa?" +
            "\r\n aaaaaaa?" + "duck \r\n to string Duck.\n");

    @Test
    public void analyzeFileTest() {
        int count = 0;
        String word = "duck";
        String expected = "duck to string Duck.";
        String[] sentences = FileAnalyzer.getSentences(text);

        for (String sentence : sentences) {
            if (FileAnalyzer.getMatches(sentence, word)) {
                assertEquals(expected, sentence);
                count += FileAnalyzer.splitSentence(sentence, word);
            }
        }
        assertEquals(2, count);
    }
}