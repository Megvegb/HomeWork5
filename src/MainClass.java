import java.util.Arrays;

public class MainClass {
    private static final int SIZE = 10_000_000;

    private static final int HALF = SIZE / 2;
    private static final float[] arr = new float[SIZE];
    private static Counter count = new Counter();


    public static void main(String[] args) {

        Arrays.fill(arr, 1);

        System.out.println("Method 1. Execution time "
                + count.method1(arr));
        System.out.println(Arrays.toString(arr));

        System.out.println("Method 2. Execution time "
                + count.method2(arr, HALF));
        System.out.println(Arrays.toString(arr));
    }
}