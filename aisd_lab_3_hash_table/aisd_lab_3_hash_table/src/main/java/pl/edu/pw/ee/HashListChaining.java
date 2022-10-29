package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining implements HashTable {

    private final Elem nil = null;
    private Elem[] hashElems;
    private int nElem;

    private class Elem {
        private Object value;
        private Elem next;

        Elem(Object value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new Elem[size];
        initializeHash();
    }

    @Override
    public void add(Object value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) { //powiedziałbym że tak
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value; //ale value jako że to Object to object to może być dowolne, czyli git bo to lista objektów jest tak pan jezus powiedział
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]); //rozumiem że to nie jest jakaś rekurencyjna referencja że tak to nazwę
            nElem++;
        }
    }

    @Override
    public Object get(Object value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : nil;
    }

    @Override
    public void delate(Object value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];
        Elem prev = hashElems[hashId];

        if (elem != nil && elem.value.equals(value)) {
            hashElems[hashId] = elem.next;
            nElem--;
        } else {
            while (elem != nil) {
                if (elem.value.equals(value)) {
                    prev.next = elem.next;
                    nElem--;
                    break;
                } else {
                    prev = elem;
                    elem = elem.next;
                }
            }
        }
        //co jeżeli nie ma takiego elementu? A: nie robisz nic, B: wypierdalasz wyjątek
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n; //ryzyk fizyk
    }

    public int getSize() { //może zmienie nazwe na getNumberOfElements czy coś w tym guście
        return nElem;
    }

    public Elem getNil() {
        return nil;
    }
}