package edu.miis.Laerdal;

import edu.miis.LaerdalBasic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LaerdalUtil implements LaerdalBasic {

    public static final String regex = "(<source>)(.+)(<\\/source>)(\\s+)(<translation class=\"ws\" type=\"unfinished\">)(<\\/translation>)";

    public LaerdalUtil() {
    }

    @Override
    public ArrayList<String> fileList(String path) throws IOException {

        ArrayList<String> list = new ArrayList<>();

        File[] files = new File(path).listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                this.fileList(f.getAbsolutePath());
            } else {
                list.add(f.getAbsolutePath());
            }
        }

        return list;
    }

    public void renderFiles(ArrayList<String> paths, String destination) {
        try {
            for (String path : paths) {
                this.generateFiles(path, destination);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generateFiles(String path, String destination) throws IOException {

        Pattern pattern = Pattern.compile(regex);
        File f = new File(path);
        FileInputStream fis = new FileInputStream(f);

        byte[] b = new byte[1024];

        StringBuilder stringBuilder = new StringBuilder();
        int length;
        while ((length = fis.read(b)) != -1) {
            stringBuilder.append(new String(b, 0, length));
        }


        String sb = stringBuilder.toString();

        Matcher m = pattern.matcher(sb);
        StringBuilder sbb = new StringBuilder();

        while (m.find()) {

            m.appendReplacement(sbb, "$1$2$3$4$5$2$6");

        }
        m.appendTail(sbb);

        generateFiles0(sbb.toString(), destination, f.getName());

    }

    private void generateFiles0(String content, String destination, String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(destination + "\\" + name));

        System.out.println(destination + "\\" + name);

        fos.write(content.getBytes());
    }


}
