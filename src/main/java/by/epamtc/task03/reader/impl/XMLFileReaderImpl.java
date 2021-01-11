package by.epamtc.task03.reader.impl;

import by.epamtc.task03.reader.FileReader;

import java.io.*;


public class XMLFileReaderImpl implements FileReader {

    private long readedCharactersCounter = 0;
    private String filePath;

    public XMLFileReaderImpl(String filePath) {
        this.filePath = filePath;
    }

    public String read() throws IOException {

        StringBuilder builder = new StringBuilder(0);

        Reader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
        int readedChar;

     bufferReader.skip(readedCharactersCounter);
        while ((readedChar = bufferReader.read()) != -1) {
            char ch = (char) readedChar;
         readedCharactersCounter++;
            builder.append(ch);
        }
        String string = builder.toString();
        return string;
    }

    @Override
    public boolean isXMLEpmty() throws IOException {
       Reader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

       bufferReader.skip(readedCharactersCounter);
        if (bufferReader.read() == -1) {
            return true;
        }
        return false;
    }
}
