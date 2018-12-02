/**
 * Created by benzali on 11/28/2018.
 */
package Stax;

import Stax.datastructure.*;

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
    static final List<String> TYPE = new ArrayList<String>(){{
        add("event");
        add("figure");
    }};

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
            Figure itemFigure = null;
            int typeVal = -1;

            eventReader.nextEvent();
            eventReader.nextEvent();
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    if (startElement.getName().getLocalPart().equals(TYPE.get(0))) {
                        item = new Event();
                        itemEvent = (Event) item;

                        Iterator<Attribute> attributes = startElement.getAttributes();
                        itemEvent.setNextData(attributes.next().getValue());
                        typeVal = 0;
                    }
                    else if (startElement.getName().getLocalPart().equals(TYPE.get(1))){
                        item = new Figure();
                        itemFigure = (Figure) item;

                        Iterator<Attribute> attributes = startElement.getAttributes();
                        itemFigure.setNextData(attributes.next().getValue());
                        typeVal = 1;
                    }

                    if (event.isStartElement() && !TYPE.contains(event.asStartElement().getName().getLocalPart())) {
                        event = eventReader.nextEvent();
                        if(typeVal == 0) {
                            itemEvent.setNextData(event.asCharacters().getData());
                        }
                        else if (typeVal == 1){
                            itemFigure.setNextData(event.asCharacters().getData());
                        }
                    }


                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (TYPE.contains(endElement.getName().getLocalPart())) {
                        items.add(item);
                    }
                }


            }
        } catch (FileNotFoundException | XMLStreamException | NullPointerException e) {
            e.printStackTrace();
        }
        return items;
    }

}
