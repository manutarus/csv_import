package csv;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadExcel {

    private String inputFile;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void read() throws IOException {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        Commons cm = new Commons();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String all = "", cols = "";
        int count =0;
        String outFIle ="/tmp/port_patient_" + dateFormat.format(date) + ".sql";
        try {
            FileWriter fStream = new FileWriter(outFIle);
            BufferedWriter out = new BufferedWriter(fStream);

            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
            int no_col = sheet.getColumns();
            out.write(cm.mysql());
            for (int i = 1; i < sheet.getRows(); i++) {
                for (int j = 0; j < no_col; j++) {
//
                    if (!(sheet.getCell(j, i).getContents().isEmpty())){
////                        if(!(sheet.getCell(j, 0).getContents().equals("uuid")||sheet.getCell(j, 0).getContents().equals("patient_id"))){
                            if(sheet.getCell(j, i).getContents().contains(" ")) {
                                all += "'"+cm.formatDate(sheet.getCell(j, i).getContents()) + "',";
                                 cols += sheet.getCell(j, 0).getContents() + ",";
                            }
                            else {
                                all += sheet.getCell(j, i).getContents() + ",";
                                cols += sheet.getCell(j, 0).getContents() + ",";
                            }
                        }
                }

                System.out.println("INSERT INTO patient (" + cols.substring(0,cols.length()-1) + ") VALUES (" + all.substring(0,all.length()-1)
                        + ");");

                out.write("INSERT INTO patient (" + cols.substring(0,cols.length()-1) + ") VALUES (" + all.substring(0,all.length()-1)
                         + ");");
                count++;
                all = cols = "";
                System.out.println("\n");
            }
            out.close();
            System.out.println("............");
            System.out.println("....Done....");
            System.out.println("....Found....");
            System.out.println("...."+count+"....");
            System.out.println("....records....");
            System.out.println("created sql file below....");
            System.out.println(outFIle);
        } catch (BiffException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ReadExcel rx = new ReadExcel();
        rx.setInputFile("/home/manu/Documents/patient.xls");
        rx.read();
    }

}