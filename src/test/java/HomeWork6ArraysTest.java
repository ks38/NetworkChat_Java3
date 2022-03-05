import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HomeWork6ArraysTest {

    @ParameterizedTest
    @MethodSource("sliceParams")
    void sliceTest(int[] in, int[] out) {
        Assertions.assertArrayEquals(out, HomeWork6Arrays.sliceArray(in));
    }

    static Stream<Arguments> sliceParams() {
        List<Arguments> list = new ArrayList<>();
        list.add(Arguments.arguments(new int[]{1, 2, 3, 5, 5, 5, 7, 4, 0}, new int[]{0}));
        list.add(Arguments.arguments(new int[]{1, 4}, new int[]{}));
        list.add(Arguments.arguments(new int[]{1, 5, 6, 4, 6, 8, 7, 15, 564}, new int[]{6, 8, 7, 15, 564}));
        return list.stream();
    }

    @Test
    void testSliceArrayException() {
        Assertions.assertThrows(RuntimeException.class, () -> HomeWork6Arrays.sliceArray(new int[]{1, 1, 2, 6}));

    }

    @Test
    void testOneAndFour() {
        Assertions.assertTrue(HomeWork6Arrays.oneAndFour(new int[]{1, 4, 1, 4, 1, 4}));
    }
}