package com.dawn.aiwriter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootTest
class AiwriterApplicationTests {
    private static final String PATH = "D:\\hello.py";
    @Test
    void contextLoads() throws IOException, InterruptedException {
        final ProcessBuilder processBuilder = new ProcessBuilder("python", PATH);
        processBuilder.redirectErrorStream(true);
        final Process process = processBuilder.start();
        final BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s = null;
        while ((s = in.readLine()) != null)
        {
        System.out.println(s);
        }

        final int exitCode = process.waitFor();
        System.out.println(exitCode == 0);

    }

}
