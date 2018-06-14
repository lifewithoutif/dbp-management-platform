package com.hna.dbp.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ErgodicDir  {
    public static void main(String[] args) throws Exception {
        File dir = new File("C:\\brc");
        showAllFiles(dir,"aaa" );
    }
    public static void  showAllFiles(File dir, String search) throws Exception {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
          //  System.out.println(files[i].getAbsolutePath());
            if (files[i].isDirectory()) {
                showAllFiles(files[i], search);
            } else {
                fis(files[i], search);
            }
        }
    }

    public static void fis(File file, String search) throws Exception {
        FileInputStream fs = new FileInputStream(file);
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(fs, "utf-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.contains(search)) {
                System.out.println("Path :" + file.getAbsolutePath() + "文件 :" + file.getName() +"----"+ "line :" + br.read() +"----"+ line);
            }
        }
    }
}
