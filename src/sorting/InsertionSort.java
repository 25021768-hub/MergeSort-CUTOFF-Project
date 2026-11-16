
package sorting;

public class InsertionSort {

    public static void sort(Comparable[] a, int lo, int hi) {

        for (int i = lo + 1; i <= hi; i++) {

            for (int j = i; j > lo; j--) {

                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }
}
