package com.juzix.word.util;

import lombok.Cleanup;

import java.io.*;

/**
 * @author jinx
 * @date 2018/11/21 11:01
 * Desc:
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter fw;
        File file = new File("f:\\3.txt");

        @Cleanup OutputStream outputstream = new FileOutputStream(file);
        fw = new OutputStreamWriter(outputstream);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < 100000; i++) {
            fw.write("我" + (i + 1));
            fw.write("\n");
        }
    }
}
