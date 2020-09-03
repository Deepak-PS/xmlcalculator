package com.calculator.application.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.calculator.application.model.Operation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class OperationEntityBuilder {

	public static List<Operation> readOperationXml(String data) throws Exception {
		final String OPERATION = "operation";
		final String NAME = "name";
		final String TYPE = "type";
		final String FUNC = "func";
		final String ATTRIB = "attrib";
		final String FILTER = "filter";

		List<Operation> operations = new ArrayList<Operation>();
		Operation operation = null;

		File inputFile = new File(OperationEntityBuilder.class.getClassLoader().getResource(data).getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        NodeList nodeList = doc.getElementsByTagName(OPERATION);
        for (int i = 0; i < nodeList.getLength(); i++) {
        	Node node = nodeList.item(i);
        	 if (node.getNodeType() == Node.ELEMENT_NODE) {
        		 operation = new Operation();
        		 Element element = (Element) node;
        		 operation.setName(element.getAttribute(NAME));
        		 operation.setType(element.getAttribute(TYPE));
        		 operation.setFunc(element.getAttribute(FUNC));
        		 operation.setAttrib(element.getAttribute(ATTRIB));
        		 operation.setFilter(element.getAttribute(FILTER));
        	 }
        	 operations.add(operation);
        }

        return operations;
	}
}

