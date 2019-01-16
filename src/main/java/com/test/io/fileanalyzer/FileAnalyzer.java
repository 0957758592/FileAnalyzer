package com.test.io.fileanalyzer;

import java.io.*;

public class FileAnalyzer {

    public static void main(String[] args) throws IOException {

//        String path = args[0];
//        String word = args[1];

        String path = "C:/test/story.txt";
        String word = "duck";

        readFile(path, word);
    }

    private static void readFile(String path, String word) throws IOException {
        File file = new File(path);

        if (!file.getParentFile().exists()) {
            if (file.getParentFile().mkdirs()) {
                file.createNewFile();
            } else {
                throw new IOException("Failed to create directory " + file.getParent());
            }
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write("asdf as ducj a sduck ddduckss, d uck; duc" +
                    "k?\r\n duckd s djuck duekc] scdsdkjlf duckkk. \r\n" +
                    "duckduckduc.kduckduks! тратата тратата м везем с собой кота." +
                    " asdflkc kl;lksoe kdkld d. jskjlkjf... . Dusk du\r\nck");
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String sentence;
        StringBuilder sb = new StringBuilder();

        while ((sentence = br.readLine()) != null) {
            sb.append(sentence);
        }
        getResult(sb, word);

    }


    public static void getResult(StringBuilder sb, String word) {
        System.out.println(getCount(sb, word));
        System.out.println(getString(sb, word));
    }


    public static int getCount(StringBuilder sb, String word) {
        String regex = getRegex();
        String[] substr = getSubstring(sb, regex);
        int count = 0;

        for (String string : substr) {
            if (string.matches("^(.*)" + word + "(.*)[.!?]$")) {
                count += string.split(word).length - 1;
            }
        }
        return count;
    }


    public static String getString(StringBuilder sb, String word) {
        String regex = getRegex();
        String[] substr = getSubstring(sb, regex);
        String str = new String();

        for (String string : substr) {
            if (string.matches("^(.*)" + word + "(.*)+[.!?]$")) {
                str += string+"\n";
            }
        }
        return str;
    }

    public static String getRegex() {
        return "(?<=[.!?])\\s*";
    }

    private static String[] getSubstring(StringBuilder sb, String regex) {
        return sb.toString().trim().replaceAll("\r\n|\n", "").replaceAll("( )+", " ").split(regex);
    }

}