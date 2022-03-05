import java.util.Arrays;

public class HomeWork6Arrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sliceArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 5, 8, 3})));
        System.out.println(Arrays.toString(sliceArray(new int[]{1, 2, 3, 8, 5, 6, 7, 8, 9, 8, 0, 4})));
        System.out.println(oneAndFour(new int[]{1,1,1,1,4,4,4,4}));
        System.out.println(oneAndFour(new int[]{1,1,1,1,1,1,1}));
        System.out.println(oneAndFour(new int[]{5,1,1,1,4,4,4,5}));
    }

    public static int[] sliceArray(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                return Arrays.copyOfRange(array, i + 1, array.length);
            }
        }
        throw new RuntimeException("4 не найдена");
    }

    public static boolean oneAndFour(int[] arr) {
        boolean one = false;
        boolean four = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                one = true;
            } else if (arr[i] == 4) {
                four = true;
            } else {
                return false;
            }
        }
        return one && four;
    }
}
