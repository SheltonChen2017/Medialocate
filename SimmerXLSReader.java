package edu.miis.SimmerBiomet;

import java.util.ArrayList;

public class SimmerXLSReader extends XLSXManagerImpl {

    String[] filenames;

    public SimmerXLSReader() {
    }

    @Override
    protected void sortByLanguageOrder(ArrayList<String> fileList) {

    }


    public boolean ifDuplicateCountry(ArrayList<String> fileList, String countryCode1, String countryCode2) {

        boolean c1 = false;
        boolean c2 = false;

        for (String s : fileList) {
            if (s.toLowerCase().endsWith(countryCode1)) {
                c1 = true;
            }
            if (s.toLowerCase().endsWith(countryCode2)) {
                c2 = true;
            }
        }

        return (c1 & c2) & (c1 == true);
    }


}