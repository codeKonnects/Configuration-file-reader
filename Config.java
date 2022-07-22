package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
This is a file reader that reads config files.
Takes the environment as a dictator of which file to read from.
From the environment specified, takes the first value as key,
the values separated by "=" as value.
for production, it should run with main.
for other environments, it should run from the command line.

if an invalid environment is specified, run a default key.
in case of keys similarity, the first key that appears in the config file should be used.

Everything should be in string.
 */

public class Config {
    private String fileName;

    Map<String, String> fileMap = new HashMap<>();


    //Overloading the constructor
    //setting fileName to the different environments

    public Config() throws FileNotFoundException {
        this.fileName = "config.txt";
        mapAm();
    }

    public Config(String enviromentTheDictator) throws FileNotFoundException {
        if (enviromentTheDictator.equalsIgnoreCase("staging")){
            this.fileName = "config.txt.staging";
            mapAm();

        } else if (enviromentTheDictator.equalsIgnoreCase("development")) {
            this.fileName = "config.txt.dev";
            mapAm();
        }
    }

    // getKey() will set the default key if the user enters an invalid environment
    //after the environment has been specified, getKey() is used to set the key.
    //don't forget fileMap is a MAP
    public String getKey(String key){
        String errorMessage = "You can only enter Staging and development environment";
        if (fileMap.size() > 0){
            return fileMap.getOrDefault(key, "I'll knock your head." +
                    "enter the right environment, my Friend");
        }
        return errorMessage;
    }


    /*
    file path can be placed directly inside File theReadFile, it will still run.
    Scanner to read theReadFile
     */
    //beware of my config.txt path bro
    public void mapAm() throws FileNotFoundException {
        String fileSource = "/Users/dec/ConfigPractice/src/main/java/org/example/" + this.fileName;
        File theReadFile = new File(fileSource);
        Scanner theFileReader = new Scanner(theReadFile);

        /*
        if the file contains char '='
        split it with the split() which will return an array of Strings.
        (it's called String delimiter something, can't remember bro)
         */
        while (theFileReader.hasNextLine()){
            String fileContent = theFileReader.nextLine();

            if (fileContent.contains("=")){
                String[] keyTheErrandBoy = fileContent.split("=");

                //Used this, since I already know the content of the file.
                //fileMap cannot contain keyTheErrandBoy because there's nothing inside yet.
                //put the first String as key, the second string after the = as value.

                if (!(fileMap.containsKey(keyTheErrandBoy))){
                    fileMap.put(keyTheErrandBoy[0], keyTheErrandBoy[1]);
                }
            }
        }
    }
}

