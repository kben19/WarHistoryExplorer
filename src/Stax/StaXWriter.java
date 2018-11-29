package Stax;

/**
 * Created by benzali on 11/29/2018.
 */
import java.io.FileOutputStream;
import java.util.List;

import Stax.datastructure.Event;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaXWriter {
    private String configFile;

    public void setFile(String configFile) {
        this.configFile = configFile;
    }

    public void saveConfig(List<Event> eventList) throws Exception {
        // create an XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        // create XMLEventWriter
        XMLEventWriter eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(configFile));
        // create an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);
        eventWriter.add(end);

        // create config open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", eventList.get(0).getType() + "s");
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        for (Event eventItem : eventList) {

            eventItem.nextData();
            eventWriter.add(eventFactory.createStartElement("", "", eventItem.nextData()));
            eventWriter.add(end);
            while(eventItem.hasNextData()) {
                // Write the different nodes
                createNode(eventWriter, eventItem.nextData(), eventItem.nextData());
            }
            eventWriter.add(eventFactory.createEndElement("", "", eventItem.nextData()));
            eventWriter.add(end);
        }

        eventWriter.add(eventFactory.createEndElement("", "", eventList.get(0).getType() + "s"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
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


