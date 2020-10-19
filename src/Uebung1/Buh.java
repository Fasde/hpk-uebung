package Uebung1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Buh {
    protected Integer id = ++idGenerator;
    private static int idGenerator = 0;

    @Override
    public String toString() {
        return String.format("%s-%d", getClass().getSimpleName(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buh buh = (Buh) o;
        return Objects.equals(id, buh.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    class Foo extends Buh implements Comparable<Foo> {
        @Override
        public int compareTo(Foo that) {
            return this.id.compareTo(that.id);
        }
    }

    class Bar extends Buh {
    }

    public static <T> T[] sort(T[] a) {
        Arrays.sort(a);
        return a;
    }

    public static <T, U extends T, V extends T> List<T> mixit(List<U> uList, List<V> vList) {
        List<T> mixList = new ArrayList<T>();
        if (uList.size() != vList.size()) return null;
        for (int i = 0; i < uList.size(); i++) {
            mixList.add(uList.get(i));
            mixList.add(vList.get(i));
        }
        return mixList;
    }

    public static <T, U extends T, V extends T> T[] mixitA(U[] uList, V[] vList) {
        T[] mixList = (T[]) new Object[uList.length + vList.length];
        for (int i = 0; i < uList.length + vList.length; i++) {
            mixList[i] = uList[i];
            i++;
            mixList[i] = vList[i];
        }
        return mixList;
    }


    public static <T> List<T> createList(Class<T> type, int n) throws Exception {
        List<T> list = new ArrayList<T>(n);
        for (int i = 0; i < n; i++){
            list.add(type.newInstance());
        }
        return list;
    }

    public static <T> T[] createArray(Class<T> type, int n) throws Exception {
        T[] array = (T[]) new Object[n];
        for (int i = 0; i < n; i++){
            array[i] = type.newInstance();
        }
        return array;
    }

    public static void main(String[] args) throws Exception {
        final int n = 4;
        List<Foo> fooList = createList(Foo.class, n);
        List<Bar> barList = createList(Bar.class, n);
        Foo[] fooArray = createArray(Foo.class, n);
        Bar[] barArray = createArray(Bar.class, n);

        List<Buh> mixList = mixit(fooList, barList);
        Buh[] mixArray = mixitA(fooArray, barArray);

        System.out.println(fooList);
        System.out.println(barList);
        System.out.println(mixList);
        System.out.println("\n");
        System.out.println(Arrays.toString(fooArray));
        System.out.println(Arrays.toString(barArray));
        System.out.println(Arrays.toString(mixArray));
    }
}