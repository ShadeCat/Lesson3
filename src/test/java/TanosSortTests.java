import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;


public class TanosSortTests {


    @Test  // Простой тест
    public void simpleTest(){
       int[] originArray = {3, 20, 5, 7, 10, -12, 9, 1, 12};
       int[] expectedResult = {-12, 1, 3, 5, 9, 10, 7, 12, 20};
        Assertions.assertArrayEquals(expectedResult, TanosSort.toSort(originArray));
    }

    @Test
    public void oneMemberArrayTest(){
        int[] originArray = {256};
        int[] expectedResult = {256};
        Assertions.assertArrayEquals(expectedResult, TanosSort.toSort(originArray));
    }

    @Test
    public void twoMemberArrayTest(){
        int[] originArray = {256, 255};
        int[] expectedResult = {255, 256};
        Assertions.assertArrayEquals(expectedResult, TanosSort.toSort(originArray));
    }

    @Test
    public void sortedArrayTest(){ // Баг!!!
        int[] originArray = {-12, 1, 3, 5, 9, 10, 7, 12, 20};
        int[] expectedResult = {-12, 1, 3, 5, 9, 10, 7, 12, 20};
        Assertions.assertArrayEquals(expectedResult, TanosSort.toSort(originArray));
    }

    @TestFactory  // Динамический тест
    DynamicTest[] dynamicTestsFromArray() {
        return new DynamicTest[] {
                dynamicTest("Sort array with negative number",() ->
                                Assertions.assertArrayEquals(
                                        new int[]{-1, 1, 2},
                                        TanosSort.toSort(new int[]{2, -1, 1})
                                )
                ),
                dynamicTest("Sort array with zero",() ->
                        Assertions.assertArrayEquals(
                                new int[]{0, 1, 2},
                                TanosSort.toSort(new int[]{2, 0, 1})
                        )
                ),
                dynamicTest("Sort array with zero and negative",() -> // Баг!
                        Assertions.assertArrayEquals(
                                new int[]{-1, 0, 1},
                                TanosSort.toSort(new int[]{0, -1, 1})
                        )
                ),
                dynamicTest("Sort array with duplicated numbers",() ->
                        Assertions.assertArrayEquals(
                                new int[]{1, 5, 5, 10},
                                TanosSort.toSort(new int[]{5, 1, 5, 10})
                        )
                )
        };
    }

    public static int[][][] dataSet() {
        return new int[][][] {
                {{3, 2, 1}, {1, 2, 3}}, // Баг !!!
                {{121, 4, 125, 8}, {4, 8, 121, 125}},
                {{}, {}},
        };
    }

    @ParameterizedTest // простая параметризация
    @MethodSource("dataSet")
    void parametrizedTest(int[][] dataSet) {
        int[] originArray = dataSet[0];
        int[] expectedResult = dataSet[1];
        Assertions.assertArrayEquals(expectedResult, TanosSort.toSort(originArray));
    }
}


