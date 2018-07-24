import converter.Converter;

import java.io.*;


public class Main {

    static String PATH_INPUTFILE = "src/resources/inputFile.csv";


    public static void main(String[] args) throws IOException {

        Converter converter = new Converter();
        converter.execute(PATH_INPUTFILE);


    }
}
