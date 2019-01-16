package com.test.io.fileanalyzer;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileAnalyzerTest {
    private FileAnalyzer fa = new FileAnalyzer();
    private String word = "duck";
    private StringBuilder sb = new StringBuilder();
    private String expected = "duck to string.\n";


    @Before
    public void before() {
        String string = "aaaaaaaaaaa aaaaaaaaaaaa aaaaaaaaaaaaaaaaaaa?" +
                "\r\n aaaaaaa?" + "duck \r\n to string.";
        sb.append(string);
    }

    @Test
    public void getResultTest() {
        assertEquals(expected, fa.getString(sb, word));
        assertEquals(1, fa.getCount(sb, word));
    }
}