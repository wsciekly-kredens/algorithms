package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends pl.edu.pw.ee.HashOpenAdressing<T> {
    double a;
    double b;

    public HashQuadraticProbing() {
        super();
        this.a = 0.5;
        this.b = 0.45;
    }

    public HashQuadraticProbing(double a, double b, int size) {
        super(size);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) ((key % m + a * i + b * i * i) % m);

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
