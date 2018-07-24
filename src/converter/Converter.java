package converter;


import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    private String path = "src/resources/outputFile.csv";

    public void execute(String inputPath) throws IOException {
        List<String> mails = getMail(readFile(inputPath));
        getFio(mails);
    }

    public List readFile(String path) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(path))));
        String str;
        while ((str = reader.readLine()) != null) {
            list.add(str);
        }
        reader.close();
        return list;
    }

    public List<String> getMail(List<String> list) {
        ArrayList<String> emails = new ArrayList();
        String[] mass = new String[0];

        for (String m : list
                ) {
            mass = m.split(",");

            for (int i = 0; i < mass.length; i++) {
                if (mass[i].contains("@") && checkSameName(emails, mass[i])) {
                    emails.add(mass[i]);
                }
            }
        }
        return emails;
    }

    public void getFio(List<String> emails) throws IOException {

        for (String m : emails
                ) {
            String name_lastname = m.substring(0, m.indexOf('@')); //get name.lastname

            String rez = name_lastname.replace(".", ", ");
            System.out.println(rez);
            if (rez.equals("administrator")) {
                writeFile(rez + ", " + rez + "\n");
            } else
                writeFile(rez + "\n");

        }

    }

    public void writeFile(String text) throws IOException {
       // int count = 0;
        File file = new File(path);

        if (file.exists()) {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } else {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(new File(path), true);
            fileWriter.write("First Name, Last Name\n");
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        }
    }

    //check dublicats fio
    public boolean checkSameName(List<String> emails, String compareMail) {
        boolean result = false;
        if (emails.size() == 0) {
            result = true;

        }
        if(emails.size()>0){
            for (String em : emails
                 ) {
                if(em.equals(compareMail)){
                    result = false;
                    break;
                }else result = true;
            }
        }

        return result;
    }


}
