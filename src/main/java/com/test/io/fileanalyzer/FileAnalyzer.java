package com.test.io.fileanalyzer;

import java.io.*;

public class FileAnalyzer {
    private static final String REGEX = "(?<=[.!?])\\s*";

    public static void main(String[] args) throws IOException {

        String pathToFile = args[0];
        String word = args[1];

        analyzeFile(pathToFile, word);
    }

    static Boolean getMatches(String string, String word) {
        return string.matches("^(.*)(\\b" + word + "\\b)(.*)[.!?]$");
    }


    static String[] getSentences(StringBuilder sentences) {
        return sentences.toString().replaceAll("\r\n|\n", "").replaceAll("( )+", " ").split(REGEX);
    }


    static int splitSentence(String sentence, String word) {
        return sentence.toLowerCase().split("\\b" + word + "\\b").length - 1;
    }

    private static void analyzeFile(String pathToFile, String word) throws IOException {
        File file = new File(pathToFile);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String sentence;
            StringBuilder text = new StringBuilder();

            while ((sentence = br.readLine()) != null) {
                text.append(sentence);
            }
            processReadingFile(text, word);
        }

    }

    private static void processReadingFile(StringBuilder text, String word) {
        String[] sentences = getSentences(text);
        int count = 0;

        for (String sentence : sentences) {
            Boolean matchesString = getMatches(sentence.toLowerCase(), word);
            if (matchesString) {
                count += splitSentence(sentence, word);
                System.out.println(sentence);
            }
        }

        System.out.println(count);
    }


}