package by.epamtc.task03.parser.impl;

import by.epamtc.task03.entity.Node;
import by.epamtc.task03.entity.NodeType;
import by.epamtc.task03.parser.XMLParser;
import by.epamtc.task03.reader.impl.XMLFileReaderImpl;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParserImpl implements XMLParser {

    private XMLFileReaderImpl fileReader;
    private StringBuilder builder = new StringBuilder();

    public XMLParserImpl() {
    }

    public XMLParserImpl(String filePath) {
        fileReader = new XMLFileReaderImpl (filePath);
    }

    @Override
    public Node getNext() throws IOException {
        if ((builder.toString().trim().length() == 0)) {
                  if (fileReader.isXMLEpmty()) {
                return null;
            }
                  builder.append(fileReader.read());
        }
        trimBuffer(builder);
        NodeType nodeType = getNodeType(builder);
        Node node = getNode(builder, nodeType);
        deleteNodeFromBuffer(node);

        return node;
    }

    private NodeType getNodeType(StringBuilder buffer) {

        if (buffer.charAt(0) != '<') {
            return NodeType.CHARACTERS;
        } else if (buffer.charAt(1) == '/') {
            return NodeType.CLOSE_TAG;
        }
        int index = buffer.indexOf(">");
        if (buffer.charAt(index - 1) == '/') {
            return NodeType.EMPTY_TAG;
        }
        return NodeType.OPEN_TAG;

    }

    private Node getNode(StringBuilder buffer, NodeType nodeType) {
        Node node = null;
        if (nodeType == NodeType.CHARACTERS) {
            node = getCaractersNode(buffer, nodeType);
        } else {
            node = getTagNode(buffer, nodeType);
        }

        return node;
    }

    private Node getCaractersNode(StringBuilder buffer, NodeType type) {
        Node node = new Node();
        node.setType(type);
        int index = buffer.indexOf("<");
        if (index == -1) {
            node.setContent(buffer.toString());
        } else {
            node.setContent(buffer.substring(0, index));
        }
        return node;
    }

    private Node getTagNode(StringBuilder buffer, NodeType type) {
        Node node = new Node();
        node.setType(type);
        int index = buffer.indexOf(">");
        node.setContent(buffer.substring(0, index + 1));
        return node;
    }

    private void deleteNodeFromBuffer(Node node) {
        builder.delete(0, node.getContent().length());
    }

    public void trimBuffer(StringBuilder builder) {
        Pattern p = Pattern.compile("^[\\s]+");
        Matcher matcher = p.matcher(builder.toString());
        if (matcher.find()) {
            int index = matcher.end();
            char firstNonWhiteChar = builder.charAt(index);
            if (firstNonWhiteChar == '<') {
                builder.delete(0, index);
            }
        }

    }
}
