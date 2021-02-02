package xml.app.util;


import xml.app.util.contract.FileIO;

import java.io.*;

public class FileIOImpl implements FileIO {

    @Override
    public String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true){
                String line = reader.readLine();
                if(line == null) break;
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }
}
