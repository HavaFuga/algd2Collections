package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Set;

public class UnsortedSet<E> extends AbstractArrayCollection<E> implements Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	int size = 0;

	public UnsortedSet() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedSet(int capacity) {
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
		if (indexOf(e) < 0) return false;

		data[size++] = e;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		checkNull(o);
		if (size == 0) return false;
		if (!contains(o)) return false;

		int i = 0;
		while (i < size) {
			if (data[i].equals(o)) {
				data[i] = data[--size];
				data[size] = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		checkNull(o);
		if (indexOf(o) < 0 ) return false;
		return true;
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
		UnsortedSet<Integer> bag = new UnsortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
