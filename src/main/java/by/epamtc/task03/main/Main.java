package by.epamtc.task03.main;

import by.epamtc.task03.entity.Node;
import by.epamtc.task03.parser.XMLParser;
import by.epamtc.task03.parser.impl.XMLParserImpl;
import by.epamtc.task03.printer.impl.XMLPrinterImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        XMLParser parser = new XMLParserImpl("D:\\учеба\\exampleXML.txt");

        Node node = null;
        XMLPrinterImpl printer = new XMLPrinterImpl();

        while ((node = parser.getNext()) != null) {
            printer.printData(node);
        }

    }
}
