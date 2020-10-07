package pack.Runnable;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import pack.Domain.Song;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class GenerateCsv {

    public void generate(int t,int r, int b)  {

        Date date = new Date();

        try {
            File f = new File("C:/Users/giangiacompi/Desktop/progetti spring/API REST SECURITY/new/Nuovo.csv");
            Boolean flag = f.exists();
            PrintWriter pw = new PrintWriter(new FileOutputStream("Nuovo.csv", true));
            if (!flag) {
                pw.println("TYMESTAMP;TYPE;QUANTITY");
            }
            pw.println(date.toString()+";techno;"+t);
            pw.println(date.toString()+";rock;"+r);
            pw.println(date.toString()+";blues;"+b);
            pw.close();
            System.out.println("CSV CREATO");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    }


