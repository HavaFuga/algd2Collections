package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;

public class UnsortedBag<E> extends AbstractArrayCollection<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	private int size = 0;

	public UnsortedBag() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedBag(int capacity) {
		data = (E[])new Object[capacity];
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
		checkNull(e);
		if (size == data.length) throw new IllegalStateException("Collection is full");
		data[size++] = e;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		// TODO implement unless collection shall be immutable
		int index = indexOf(o);
		if (index < 0) return false;

		data[index] = data[--size];
		data[size] = null;
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
		UnsortedBag<Integer> bag = new UnsortedBag<Integer>();
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
