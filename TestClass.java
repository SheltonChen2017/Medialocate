package edu.miis.SimmerBiomet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
    public static void main(String[] args) throws IOException {

        SimmerXLSReader reader = new SimmerXLSReader();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Let me know the absolute path of the Source(English) document");
        String locationSource = br.readLine();
        System.out.println("Let me know the absolute path of the folder where the translation is stored");
        String locationTarget = br.readLine();
        System.out.println("now, give your final combined file a name.");
        String final_location = br.readLine();


        reader.source(locationSource);
        reader.loadFileList(locationTarget);

        reader.write(locationTarget + final_location + ".xlsx", reader.sourceList, "sheet");

    }
}
