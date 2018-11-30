package Stax;

/**
 * Created by benzali on 11/29/2018.
 */
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import Stax.datastructure.*;

import javax.xml.stream.*;
import javax.xml.stream.events.*;

public class StaXWriter {
    private String configFile;

    public void setFile(String configFile) {
        this.configFile = configFile;
    }

    public void saveConfig(List<Item> itemList) throws Exception {
        // create an XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        // create XMLEventWriter
        XMLStreamWriter eventWriter = outputFactory
                .createXMLStreamWriter(new FileOutputStream(configFile));
        // create characters
        String end = "\n";
        String tab = "\t";

        // create and write Start Tag
        eventWriter.writeStartDocument();
        eventWriter.writeCharacters(end);
        eventWriter.writeStartElement(itemList.get(0).getHeader());
        eventWriter.writeCharacters(end);

        for (Item item : itemList) {
            eventWriter.writeCharacters(tab);
            eventWriter.writeStartElement(item.getBody());
            eventWriter.writeAttribute(item.nextData(), item.nextData());
            eventWriter.writeCharacters(end);

            while(item.hasNextData()) {
                eventWriter.writeCharacters(tab+tab);
                eventWriter.writeStartElement(item.nextData());
                eventWriter.writeCharacters(item.nextData());
                eventWriter.writeEndElement();
                eventWriter.writeCharacters(end);
            }

            eventWriter.writeCharacters(tab);
            eventWriter.writeEndElement();
            eventWriter.writeCharacters(end);
        }
        eventWriter.writeEndElement();
        eventWriter.writeEndDocument();

        // create config open tag
//        StartElement configStartElement = eventFactory.createStartElement("",
//                "", itemList.get(0).getHeader());
//        eventWriter.add(configStartElement);
//        eventWriter.add(end);
//
//        for (Item item : itemList) {
//
//            XMLEvent attributes = item.getAttributes().next();
//            eventWriter.add(eventFactory.createStartElement("", "", item.getBody()));
//            eventWriter.add(attributes);
//            eventWriter.add(end);
//            item.nextData();
//            item.nextData();
//
//            while(item.hasNextData()) {
//                // Write the different nodes
//                createNode(eventWriter, item.nextData(), item.nextData());
//            }
//
//            eventWriter.add(eventFactory.createEndElement("", "", item.nextData()));
//            eventWriter.add(end);
//        }
//
//        eventWriter.add(eventFactory.createEndElement("", "", itemList.get(0).getHeader()));
//        eventWriter.add(end);
//        eventWriter.add(eventFactory.createEndDocument());
//        eventWriter.close();

        System.out.println("Save Successful");
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }

}


