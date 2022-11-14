package pl.edu.pw.ee;

import java.util.Arrays;

import pl.edu.pw.ee.exceptions.NotImplementedException;
import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private final T del = null;
    private boolean[] isDeleted;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
        this.isDeleted = new boolean[this.size];
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil || isDeleted[hashId]) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        isDeleted[hashId] = false; // można chyba nie trzeba
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int hashCode = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(hashCode, i);

        while ((hashElems[hashId] != nil || isDeleted[hashId]) && i != size - 1) {
            if (hashElems[hashId] == elem) {
                return hashElems[hashId];
            }
            i = (i + 1) % size;
            hashId = hashFunc(hashCode, i);
        }
        return null;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int hashCode = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(hashCode, i);

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId] == elem) {
                hashElems[hashId] = del;
                isDeleted[hashId] = true;
                nElems--;
                break; //grrr
            }
            i = (i + 1) % size;
            hashId = hashFunc(hashCode, i);
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    @Override
    public int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        T[] refArray = Arrays.copyOf(this.hashElems, size);
        this.size *= 2;
        this.hashElems = (T[]) new Comparable[this.size];
        this.nElems = 0;
        this.isDeleted = new boolean[this.size];
        for (T x : refArray) {
            if (x != nil) {
                this.put(x);
            }
        }
    }
}