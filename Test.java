package edu.miis.Laerdal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException {

        System.out.println("Source Folder is: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sourceLocation = br.readLine();

        System.out.println("Target Folder is: ");

        String targetLocation = br.readLine();

        LaerdalUtil laerdal = new LaerdalUtil();

        ArrayList<String> list = laerdal.fileList(sourceLocation);

        laerdal.renderFiles(list, targetLocation);

    }


}

