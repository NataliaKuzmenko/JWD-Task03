package by.epamtc.task03.parser;

import by.epamtc.task03.entity.Node;

import java.io.IOException;

public interface XMLParser {
    Node getNext() throws IOException;
}
