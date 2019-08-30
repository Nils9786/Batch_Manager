import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {
        Boolean done = false;
        while (done == false) {
            File ordner = new File("Stuff/Skripte");
            FilenameFilter batFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.endsWith(".bat")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };

            File[] dateien = ordner.listFiles(batFilter);
            int countFiles = 0;
            for (File file : dateien) {
                System.out.println(countFiles + "= " + file.getName());
                countFiles++;
            }
            System.out.println("-------------------------------------------------------------------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welche Scripte sollen kopiert werden?");
            String nummer = reader.readLine();
            String[] pick = nummer.split(",");
            BufferedReader readertest = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Wie soll die Outputdatei heißen?");

            String dataname = " ";
            dataname = readertest.readLine();
            if (dataname.isEmpty()) {

                dataname = "Output";
            }
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("Output/"+dataname + ".bat")));
            bwr.write("@echo Bitte Benutzernamen eingeben\n" +
                    "@set /p Benutzer=\n" +
                    "@echo Bitte Kennwort eingeben\n" +
                    "@set /p Kennwort=\n" +
                    "@cls\n" +
                    "@net use * /delete /y\n");
            int i = 0;
            while (i < pick.length) {
                FileReader fr = new FileReader(dateien[Integer.parseInt(pick[i])]);
                BufferedReader reader2 = new BufferedReader(fr);
                String text = reader2.readLine();
                bwr.write(text.toString() + "\n");
                bwr.flush();
                i++;


            }
            System.out.println("Noch ein Skript erstellen? (y/n)");
            BufferedReader reader3 = new BufferedReader(new InputStreamReader(System.in));
            if (reader3.readLine().equals("n") ) {

                done = true;

            }

        }


    }

}


