package edu.miis.SimmerBiomet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 */

public abstract class XLSXManagerImpl implements XLSXManager {

    public String source;

    public ArrayList<String> sourceList = new ArrayList<String>();
    public ArrayList<String> fileList = new ArrayList<String>();

    public XLSXManagerImpl() {
    }

    public void source(String source) throws IOException {
        this.source = source;
        this.read();
    }


    public void read() throws IOException {
        this.sourceList = this.read0(this.source);
    }


    public ArrayList<String> read0(String path) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        XSSFWorkbook workbook1 = new XSSFWorkbook(path);
        XSSFSheet sheet1 = workbook1.getSheetAt(0);
        int firstRowNum = sheet1.getFirstRowNum();
        int lastRowNum = sheet1.getLastRowNum();
        Row row;

        for (int i = firstRowNum; i <= lastRowNum; i++) {

            row = sheet1.getRow(i);


            if (row != null && row.getCell(0) != null && row.getCell(0).getStringCellValue() != "") {
                list.add(row.getCell(0).getStringCellValue());
            } else {
                list.add(null);
            }
        }
        return list;
    }

    public ArrayList<String> fill(ArrayList<String> source, ArrayList<String> target) {

        int indexS = 0;
        int indexT = 0;

        for (int i = 0; i < source.size(); i++) {
            if (source.get(i) != null && source.get(i) != "") {
                continue;
            } else {
                indexS = i;
                break;
            }
        }

        do {
            if (target.get(indexT) != null) {
                source.set(indexS, target.get(indexT));
            }
            indexS++;
            indexT++;

        } while (indexS < source.size());

        for (int i = indexT; i < target.size(); i++) {
            if (target.get(i) == null) {
                continue;
            } else {
                source.add(target.get(i));
            }
        }


        return source;
    }

    public void write(String path, ArrayList<String> list, String sheetname) throws IOException {

        XSSFWorkbook sheets = new XSSFWorkbook();

        XSSFSheet sheet = sheets.createSheet(sheetname);
        int i = 0;

        for (String s : list) {

            if (s == null) {
                sheet.createRow(i++);
            } else {
                sheet.createRow(i++).createCell(0).setCellValue(s);
            }

        }

        FileOutputStream fos = new FileOutputStream(path);

        sheets.write(fos);
        fos.close();

    }


    public ArrayList<String> fileList(String path) {

        if (path == null) {
            return null;
        }

        File[] files = new File(path).listFiles();

        for (File f : files) {
            if (f.isFile()) {
                this.fileList.add(f.getAbsolutePath());
            } else if (f.isDirectory()) {

                this.fileList(f.getAbsolutePath());
            }
        }

        this.sortByLanguageOrder(this.fileList);

        return this.fileList;
    }

    protected abstract void sortByLanguageOrder(ArrayList<String> fileList);

    public void combine(String path) throws IOException {
        ArrayList<String> targetList = this.read0(path);
        this.sourceList = this.fill(this.sourceList, targetList);
    }

    public void loadFileList(String path) throws IOException {

        ArrayList<String> files = this.fileList(path);
        for (String f : files) {
            this.combine(f);
        }
    }

}
