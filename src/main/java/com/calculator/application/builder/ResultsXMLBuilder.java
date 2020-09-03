package com.calculator.application.builder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.calculator.application.model.Result;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ResultsXMLBuilder {
	private String outputFile;
	
	 public void setFile(String outputFile) {
	        this.outputFile = outputFile;
	    }

	public String buildXML(List<Result> results) throws FileNotFoundException, XMLStreamException {
		final String RESULTS = "results";
		final String ENCODING = "UTF-8";
		
		final String CREATED = "XML Created!";
		
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(outputFile), ENCODING);
		
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
        
        StartElement configStartElement = eventFactory.createStartElement("",
                "", RESULTS);
        
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        
        for(Result result: results) {
        	createNode(eventWriter, result.getName(), result.getValue());
        }
        
        eventWriter.add(eventFactory.createEndElement("", "", RESULTS));
        eventWriter.add(end);
        
        return CREATED;
	}
	
	private void createNode(XMLEventWriter eventWriter, String name,
            String value) throws XMLStreamException {
		
		final String RESULT = "result";
		final String NAME = "name";

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", RESULT);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        
        Attribute attribute = eventFactory.createAttribute(NAME, name);
        eventWriter.add(attribute);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", RESULT);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }
}

