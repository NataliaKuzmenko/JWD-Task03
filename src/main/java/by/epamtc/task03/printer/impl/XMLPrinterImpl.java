package by.epamtc.task03.printer.impl;

import by.epamtc.task03.entity.Node;
import by.epamtc.task03.entity.NodeType;
import by.epamtc.task03.printer.XMLPrinter;

public class XMLPrinterImpl implements XMLPrinter {

    @Override
    public void printData(Node node) {
        if (node.getType() == NodeType.CHARACTERS) {
            System.out.println(node.getContent());
        }
    }
}
