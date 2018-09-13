package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Lines {


    public static String[] getAccount() {

        String[] acct = new String[3];

        String fileName = "lines.txt";
        Object[] s = null;
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {


            s = stream.toArray();

            for (int i = 0; i < s.length; i++) {
                String line = s[i].toString();
                if (line.length() != 0 && !line.contains("favorite")) {


                    acct[0] = line.substring(line.indexOf("<name>") + 5, line.indexOf("<key>"));
                    acct[1] = line.substring(line.indexOf("<<2>>") + 5, line.indexOf("<<3>>"));
                    acct[2] = line.substring(line.indexOf("<<3>>") + 5, line.indexOf("<<4>>"));

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return acct;

    }

    private static void acctPull(String line) {




    }


}
