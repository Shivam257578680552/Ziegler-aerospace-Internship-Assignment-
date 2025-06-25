package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

public class CSVReader {
    public static CSVRecord getUserData() {
        try {
            Reader in = new FileReader("testdata.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            return records.iterator().next();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
