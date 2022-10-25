package pl.edu.pw.ee;

import java.util.List;

import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;

    public Heap(List<T> data) {
        this.data = data;
    }

    @Override
    public void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't add null");
        }
        data.add(item);
        T tmp = data.get(0);
        data.set(0, item);
        data.set(data.size() - 1, tmp);
        build();
    }

    @Override
    public T pop() {
        if (data.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty");
        }
        if (!isHeap()) {
            throw new IllegalArgumentException();
        }
        T result = data.remove(0);
        build();
        return result;
    }

    @Override
    public void build() {
        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    @Override
    public void heapify(int startId, int endId) {
        int max = startId;
        int left = startId * 2 + 1;
        int right = startId * 2 + 2;

        if (startId < 0 || endId < 0) {
            throw new IndexOutOfBoundsException("Boundaries has to be positive ");
        }

        if (endId <= startId) {
            throw new IllegalArgumentException("endId has to be greater then startId");
        }

        if (left < endId && data.get(left).compareTo(data.get(max)) > 0) {
            max = left;
        }

        if (right < endId && data.get(right).compareTo(data.get(max)) > 0) {
            max = right;
        }

        if (max != startId) {
            T tmp = data.get(startId);
            data.set(startId, data.get(max));
            data.set(max, tmp);
            heapify(max, endId);
        }
    }

    public List<T> getHeap() {
        return data;
    }

    public boolean isHeap() {
        int n = data.size();
        for (int i = 0; i < n; i++) {
            if (i * 2 + 1 < n && data.get(i).compareTo(data.get(i * 2 + 1)) < 0) {
                return false;
            } else if (i * 2 + 2 < n && data.get(i).compareTo(data.get(i * 2 + 2)) < 0) {
                return false;
            }
        }
        return true;
    }
}
