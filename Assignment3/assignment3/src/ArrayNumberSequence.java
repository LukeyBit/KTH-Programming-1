// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

import java.util.Arrays;

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

	@Override
	public int length() {
		return numbers.length;
	}

	@Override
	public double upperBound() {
		if (isDecreasing()){
			return numbers[0];
		} else if (isIncreasing()) {
			return numbers[numbers.length - 1];
		} else {
			double max = numbers[0];
			for (double number : numbers) {
				if (number > max) {
					max = number;
				}
			}
			return max;
		}
	}

	@Override
	public double lowerBound() {
		if (isIncreasing()) {
			return numbers[0];
		} else if (isDecreasing()){
			return numbers[numbers.length - 1];
		} else {
			double min = numbers[0];
			for (double number : numbers) {
				if (number < min) {
					min = number;
				}
			}
			return min;
		}
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException {
		if (position < 0 || position > numbers.length - 1) {
			throw new IndexOutOfBoundsException("Position must be between 0 and " + (numbers.length - 1) + ".");
		} else {
			return numbers[position];
		}
	}

	@Override
	public int positionOf(double number) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == number) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isIncreasing() {
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] <= numbers[i-1]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isDecreasing() {
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] >= numbers[i-1]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean contains(double number) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == number) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void add(double number) {
		double[] newNumbers = Arrays.copyOf(numbers, numbers.length + 1);
		newNumbers[newNumbers.length - 1] = number;
		numbers = newNumbers;
	}

	@Override
	public void insert(int position, double number) throws IndexOutOfBoundsException {
		double[] newNumbers = new double[numbers.length + 1];
		if (position < 0 || position > numbers.length - 1) {
			throw new IndexOutOfBoundsException("Position must be between 0 and " + (numbers.length - 1) + ".");
		} else {
			for (int i = 0; i < newNumbers.length; i++) {
				if (i < position) {
					newNumbers[i] = numbers[i];
				} else if (i == position) {
					newNumbers[i] = number;
				} else {
					newNumbers[i] = numbers[i - 1];
				}
			}
			numbers = newNumbers;
		}
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
		if (position < 0 || position > numbers.length - 1) {
			throw new IndexOutOfBoundsException("Position must be between 0 and " + (numbers.length - 1) + ".");
		} else if (numbers.length < 3) {
			throw new IllegalStateException("Sequence must have at least two numbers.");
		} else {
			double[] newNumbers = new double[numbers.length - 1];
			for (int i = 0; i < newNumbers.length; i++) {
				if (i != position) {
					newNumbers[i] = numbers[i];
				} else {
					newNumbers[i] = numbers[i + 1];
				}
			}
			numbers = newNumbers;
		}
	}

	@Override
	public double[] asArray() {
		double[] deepCopy = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			deepCopy[i] = numbers[i];
		}
		return deepCopy;
	}
}