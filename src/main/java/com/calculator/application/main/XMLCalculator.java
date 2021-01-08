/**
* <h1>XML Calculator</h1>
* <p>This project reads XMLs consisting of
* a list of cities for which the population and area are also provided, and,
* a list of arithmetic operations to be performed on the cities list.
* After, performing the operations, an XML file consisting the result is generated</p>
*
* @author  Deepak Srinivas Pradhan
*/
package com.calculator.application.main;

import com.calculator.application.model.*;
import com.calculator.application.builder.*;
import com.calculator.application.algorithm.Evaluator;

import java.util.List;

public class XMLCalculator {

	public static void main(String[] args) {
		final String DATA_FILE = args[0];
		final String OPERATIONS_FILE = args[1];
		final String OUTPUT_FILE = args[2];
		if (args.length == 3) {
			try {
				// Path to the data file, e.g. data/data.xml
				List<City> cities = CityEntityBuilder.readCityXml(DATA_FILE);

				// Path to the data file, e.g. operations/operations.xml
				List<Operation> operations = OperationEntityBuilder.readOperationXml(OPERATIONS_FILE);

				Evaluator evaluator = new Evaluator();
				List<Result> results = evaluator.evaluate(cities, operations);

				ResultsXMLBuilder resultXmlBuilder = new ResultsXMLBuilder();

				// Path to the output file
				resultXmlBuilder.setFile(OUTPUT_FILE);
				String result = resultXmlBuilder.buildXML(results);
				System.out.println(result);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.exit(1);
		}

	}
}
