// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

	@Override
	public int length() {
		boolean hasNext = true;
		int length = 0;
		Node n = first;
		while (hasNext) {
			length++;
			if (n.next == null) {
				hasNext = false;
			} else {
				n = n.next;
			}
		}
		return length;
	}

	@Override
	public double upperBound() {
		double max = first.number;
		Node n = first;
		while (n != null) {
			if (n.number > max) {
				max = n.number;
			}
			n = n.next;
		}
		return max;
	}

	@Override
	public double lowerBound() {
		double min = first.number;
		Node n = first;
		while (n != null) {
			if (n.number < min) {
				min = n.number;
			}
			n = n.next;
		}
		return min;
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException {
		if (position < 0 || position >= length()) {
			throw new IndexOutOfBoundsException("Position " + position + " is out of bounds");
		}
		Node n = first;
		for (int i = 0; i < position; i++) {
			n = n.next;
		}
		return n.number;
	}

	@Override
	public int positionOf(double number) {
		Node n = first;
		for (int i = 0; i < length(); i++) {
			if (n.number == number) {
				return i;
			}
			n = n.next;
		}
		return -1;
	}

	@Override
	public boolean isIncreasing() {
		Node n = first;
		while (n.next != null) {
			if (n.number > n.next.number) {
				return false;
			}
			n = n.next;
		}
		return true;
	}

	@Override
	public boolean isDecreasing() {
		Node n = first;
		while (n.next != null) {
			if (n.number < n.next.number) {
				return false;
			}
			n = n.next;
		}
		return true;
	}

	@Override
	public boolean contains(double number) {
		Node n = first;
		for (int i = 0; i < length(); i++) {
			if (n.number == number) {
				return true;
			}
			n = n.next;
		}
		return false;
	}

	@Override
	public void add(double number) {
		Node n = first;
		for (int i = 0; i < length(); i++) {
			if (n.next == null) {
				n.next = new Node(number);
				return;
			}
			n = n.next;
		}
	}

	@Override
	public void insert(int position, double number) throws IndexOutOfBoundsException {
		if (position < 0 || position > length()-1){
			throw new IndexOutOfBoundsException("Position " + position + " is out of bounds");
		} else {
			Node n = first;
			for (int i = 1; i < position; i++) {
				n = n.next;
			}
			Node newNode = new Node(number);
			newNode.next = n.next;
			n.next = newNode;
		}
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
		if (position < 0 || position > length()-1){
			throw new IndexOutOfBoundsException("Position " + position + " is out of bounds");
		} else if (length() < 3) {
			throw new IllegalStateException("There must be at least two numbers in the sequence");
		} else {
			Node n = first;
			for (int i = 1; i < position; i++) {
				n = n.next;
			}
			n.next = n.next.next;
		}
	}

	@Override
	public double[] asArray() {
		double[] array = new double[length()];
		Node n = first;
		for (int i = 0; i < length(); i++) {
			array[i] = n.number;
			n = n.next;
		}
		return array;
	}
}