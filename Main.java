package org.example;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Config config = new Config("development");
        Config config1 = new Config();
        String content1 = config1.getKey("name");
        System.out.println(content1);
        String content = config.getKey("port");
        System.out.println(content);

        if (args.length > 1){
            config = new Config(args[0]);
            config.getKey("mode");
        }
    }
}