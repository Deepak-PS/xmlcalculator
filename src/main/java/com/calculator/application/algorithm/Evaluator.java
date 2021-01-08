package de.itdesign.application;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {

	public List<Result> evaluate(List<City> cities, List<Operation> operations) {
		final String ATTRIB = "attrib";
		final String SUB = "sub";

		final String POPULATION = "population";
		final String AREA = "area";

		List<Result> results = new ArrayList<Result>();

		List<Result> populationResults = operations.stream().filter(operation -> operation.getType().equals(ATTRIB))
				.filter(operation -> operation.getAttrib().equals(POPULATION)).map(operation -> {
					Result result = new Result();
					result.setName(operation.getName());
					result.setValue(operate(getPopulationList(cities, operation), operation.getFunc()));
					return result;
				}).collect(Collectors.toList());
		results.addAll(populationResults);

		List<Result> areaResults = operations.stream().filter(operation -> operation.getType().equals(SUB))
				.filter(operation -> operation.getAttrib().equals(AREA)).map(operation -> {
					Result result = new Result();
					result.setName(operation.getName());
					result.setValue(operate(getAreaList(cities, operation), operation.getFunc()));
					return result;
				}).collect(Collectors.toList());
		results.addAll(areaResults);

		return results;
	}

	private List<Double> getPopulationList(List<City> cities, Operation operation) {
		// reading from the cities list and creating a list of population of all
		// the filtered cites based on the given filter
		return cities.stream().filter(city -> city.getName().matches(operation.getFilter()))
				.map(city -> city.getPopulation()).collect(Collectors.toList());
	}

	private List<Double> getAreaList(List<City> cities, Operation operation) {
		// reading from the cities list and creating a list of area of all
		// the filtered cites based on the given filter
		return cities.stream().filter(city -> city.getName().matches(operation.getFilter())).map(city -> city.getArea())
				.collect(Collectors.toList());
	}

	private static String operate(List<Double> list, String funcType) {
		final DecimalFormat decimalFormat = new DecimalFormat("#.00");
		decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);

		return decimalFormat.format(OperationFactory.valueOf(funcType.toUpperCase()).apply(list));
	}

}
