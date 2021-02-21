import java.util.Arrays;

public class TanosSort {
    public static void main(String[] args){
        int[] example = {3, 20, 5, 7, 10, -12, 9, 1, 12};
        System.out.println(Arrays.toString(example));
        int[] sortedArray = toSort(example);
        System.out.println(Arrays.toString(sortedArray));
    }

    public static int[] toSort(int[] arrayPart){

        if (arrayPart.length > 1){
            arrayPart = meanSort(arrayPart);
            int meanPosition = arrayPart.length / 2;
            int[] firstPart = Arrays.copyOfRange(arrayPart, 0, meanPosition);
            int[] secondPart = Arrays.copyOfRange(arrayPart, meanPosition, arrayPart.length);
            firstPart = toSort(firstPart);
            secondPart = toSort(secondPart);
            System.arraycopy(firstPart, 0, arrayPart, 0, meanPosition);
            System.arraycopy(secondPart, 0, arrayPart, meanPosition, secondPart.length);
        }
        return arrayPart;
    }

    private static double getMean(int[] arrayToSort){
        double arraySum = 0;
        for (int j : arrayToSort) {
            arraySum += j;
        }
        return (arraySum / arrayToSort.length);
    }

    private static int[] meanSort(int[] arrayToSort){
        int[] sortedArray = new int[arrayToSort.length];
        int frontCount = 0;
        int backCount = 1;
        double mean = getMean(arrayToSort);
        for (int j : arrayToSort) {
            if (j <= mean) {
                sortedArray[frontCount] = j;
                frontCount++;
            } else {
                sortedArray[arrayToSort.length - backCount] = j;
                backCount++;
            }
        }
        return sortedArray;
    }
}