package com.calculator.application.algorithm;

import java.util.Collections;
import java.util.List;

public enum OperationFactory {

	AVERAGE {

		@Override
		public double apply(List<Double> list) {
			double sum = toDoubleSum(list);
			return sum / list.size();
		}

	},

	MAX {

		@Override
		public double apply(List<Double> list) {
			return Collections.max(list);
		}

	},

	MIN {

		@Override
		public double apply(List<Double> list) {
			return Collections.min(list);
		}

	},
	SUM {

		@Override
		public double apply(List<Double> list) {
			return toDoubleSum(list);
		}

	};

	public abstract double apply(List<Double> list);

	private static double toDoubleSum(List<Double> list) {
		return list.stream().mapToDouble(Double::doubleValue).sum();
	}

}
