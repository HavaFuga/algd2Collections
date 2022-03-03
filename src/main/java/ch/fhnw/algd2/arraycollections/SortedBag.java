package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;

public class SortedBag<E extends Comparable<? super E>> extends AbstractArrayCollection<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	private int size = 0;

	public SortedBag() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedBag(int capacity) {
		data = (E[])new Comparable[capacity];
	}

	private int indexOf(Object o) {
		checkNull(o);
		for (int i = 0; i < size; i++) {
			if (data[i].equals(o)) return i;
		}
		return -1;
	}

	@Override
	public boolean add(E e) {
		if (data.length == size) throw new IllegalStateException("Collection is full");
		checkNull(e);

		int index = Arrays.binarySearch(data, e);

		if (index > 0) {
			int i = size;
			while (size > index) {
				data[i + 1] = data[i];
				i--;
			}
		} else if (index <0) {
			// TODO add
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index = Arrays.binarySearch(data, o);
		if (index < 0) return false;

		int i = index;
		while (index < size) {
			data[i] = data[i-1];
			i++;
		}

		data[size--] = null;
		return true;
	}

	@Override
	public boolean contains(Object o) {
		checkNull(o);
		for (int i = 0; i < size; i++) {
			if (data[i].equals(o)) return true;
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		SortedBag<Integer> bag = new SortedBag<Integer>();
		bag.add(12);
		bag.add(5);
		bag.add(28);
		bag.add(47);
		bag.add(28);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
