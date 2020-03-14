import java.util.Arrays;

public class Counter {
    private Object mon = new Object();

    public long method1(float[] arr) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return System.currentTimeMillis() - time;
    }

    public long method2(float[] arr, int half) {
        long time = System.currentTimeMillis();
        float[] destArr1 = new float[half];
        float[] destArr2 = new float[half];

        System.arraycopy(arr, 0, destArr1, 0, half);
        System.arraycopy(arr, half, destArr2, 0, half);

        Thread th1 = new Thread(() -> {
            synchronized (mon) {
                methodCount(destArr1, 0);
            }
        });

        Thread th2 = new Thread(() -> {
            synchronized (mon) {
                methodCount(destArr2, half);
            }
        });

        th1.start();
        th2.start();

        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(destArr1, 0, arr, 0, half);
        System.arraycopy(destArr2, 0, arr, half, half);

        return System.currentTimeMillis() - time;
    }

    public void methodCount(float[] array, int h){
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f +
                    (i + h) / 5) * Math.cos(0.4f + (i + h) / 2));
        }
    }
}


