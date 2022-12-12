package com.gachon.frimo.pythonEXE;

import java.io.IOException;

public class javaTest {
    public static void main(String[] args) throws IOException, InterruptedException{
        Runtime rt = Runtime.getRuntime();
        String exeFile = "src\\main\\java\\com\\gachon\\frimo\\pythonEXE\\dist\\testPython.exe";
        Process p;
    try {
        p = rt.exec(exeFile);
        p.waitFor();
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    
}
