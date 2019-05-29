package edu.miis.SimmerBiomet;

import java.io.IOException;
import java.util.ArrayList;

public interface XLSXManager {

    /**
     * An interface that provides basic functions for Excel File Management.
     * <p>
     * read(): Load excel files based on information provided upon class initiation.
     * fill(source, target): incorporate the target translation back into source.
     * write(path, list, sheetname): create excel files at the stated location
     * <p/>
     */

    void read() throws IOException;

    ArrayList<String> fill(ArrayList<String> source, ArrayList<String> target);

    void write(String path, ArrayList<String> list, String sheetname) throws IOException;
}
