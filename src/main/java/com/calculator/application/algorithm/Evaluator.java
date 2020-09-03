package com.calculator.application.algorithm;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.calculator.application.model.*;

public class Evaluator {
	
	public List<Result> evaluate(List<City> cities, List<Operation> operations) throws Exception {		
		final String ATTRIB = "attrib";
		final String SUB = "sub";
		
		final String POPULATION = "population";
		final String AREA = "area";
		
		List<Result> results = new ArrayList<Result>();
		
		List<Double> listData = null;
		
		for(Operation operation: operations) {
			Result result = new Result();
			listData = new ArrayList<Double>();
			if(operation.getType().equals(ATTRIB)) {
				if(operation.getAttrib().equals(POPULATION)) {
					for(City city: cities) {
						if(city.getName().matches(operation.getFilter()))
						{
							listData.add(city.getPopulation());
						}
					}
					result.setName(operation.getName());
					result.setValue(operate(listData, operation.getFunc()));	
					results.add(result);
				}
			}
			else if(operation.getType().equals(SUB)) {
				if(operation.getAttrib().equals(AREA)) {
					for(City city: cities) {
						if(city.getName().matches(operation.getFilter()))
						{
							listData.add(city.getArea());
						}
					}
					result.setName(operation.getName());
					result.setValue(operate(listData, operation.getFunc()));	
					results.add(result);
				}
			}
		}
		return results;
	}
	

	
	private static String operate(List<Double> list, String funcType) throws Exception
	{
		final DecimalFormat decimalFormat = new DecimalFormat("#.00");
		decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);
		
		final String AVERAGE = "average";
		final String MAX = "max";
		final String MIN = "min";
		final String SUM = "sum";
		
		if(funcType.equals(AVERAGE)) {
			double sum = toDouble(list);
			return decimalFormat.format(sum/ list.size());
		}
		else if(funcType.equals(MAX)) {
			Double max = Collections.max(list);
			return decimalFormat.format(max);
		}
		else if(funcType.equals(MIN)) {
			Double max = Collections.min(list);
			return decimalFormat.format(max);
		}
		else if(funcType.equals(SUM)) {
			double sum = toDouble(list);
			return decimalFormat.format(sum);
		}
		return "0.00";
	}
	
	private static double toDouble(List<Double> list) {
		return list.stream().mapToDouble(Double::doubleValue).sum();
	}
}
