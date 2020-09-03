package com.calculator.application.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.calculator.application.model.City;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CityEntityBuilder {

	public static List<City> readCityXml(String data) throws Exception {
		final String CITY = "city";
		final String NAME = "name";
		final String POPULATION = "population";
		final String AREA = "area";

		List<City> cities = new ArrayList<City>();
		City city = null;
		
		File inputFile = new File(CityEntityBuilder.class.getClassLoader().getResource(data).getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        NodeList nodeList = doc.getElementsByTagName(CITY);
        for (int i = 0; i < nodeList.getLength(); i++) {
        	Node node = nodeList.item(i);
        	 if (node.getNodeType() == Node.ELEMENT_NODE) {
        		 city = new City();
        		 Element element = (Element) node;
        		 city.setName(element.getAttribute(NAME));
        		 city.setPopulation(Integer.parseInt(element.getAttribute(POPULATION)));
        		 city.setArea(Float.parseFloat(element.getElementsByTagName(AREA).item(0).getTextContent()));
        	 }
        	 cities.add(city);
        }

        return cities;
	}
}

