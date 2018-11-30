package Stax; /**
 * Created by benzali on 11/28/2018.
 */
import Stax.datastructure.Event;
import Stax.datastructure.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class StaXParser {
    static final String[] EVENT = {"event", "name", "date", "location", "party", "description"};

    @SuppressWarnings({ "unchecked", "null" })
    public List<Item> readConfig(String configFile) {
        List<Item> items = new ArrayList<Item>();
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Item item = null;
            Event itemEvent = null;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    if (startElement.getName().getLocalPart().equals(EVENT[0])) {
                        item = new Event();
                        itemEvent = (Event) item;

                        Iterator<Attribute> attributes = startElement.getAttributes();
                        itemEvent.setId(attributes.next().getValue());
                        itemEvent.setAttributes(attributes);

                    }
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(EVENT[1])) {
                            event = eventReader.nextEvent();
                            itemEvent.setName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(EVENT[2])) {
                        event = eventReader.nextEvent();
                        itemEvent.setDate(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(EVENT[3])) {
                        event = eventReader.nextEvent();
                        itemEvent.setLocation(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(EVENT[4])) {
                        event = eventReader.nextEvent();
                        itemEvent.setParty(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(EVENT[5])) {
                        event = eventReader.nextEvent();
                        itemEvent.setDescription(event.asCharacters().getData());
                        continue;
                    }


                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(EVENT[0])) {
                        items.add(item);
                    }
                }


            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return items;
    }

}
