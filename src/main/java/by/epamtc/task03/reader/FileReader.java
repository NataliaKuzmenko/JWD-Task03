package by.epamtc.task03.reader;

import java.io.IOException;

public interface FileReader {

    String read() throws IOException;

    boolean isXMLEpmty() throws IOException;

}
