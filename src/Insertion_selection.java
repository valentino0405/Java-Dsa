import java.util.Scanner;

public class Insertion_selection {

    public static void insertionSort(int[] arr, int n) {
        for (int j = 1; j < n; j++) {
            int key = arr[j];
            int i = j - 1;

            while (i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i = i - 1;
            }
            arr[i + 1] = key;

            System.out.print("After pass " + j + ": ");
            printArray(arr, n);
        }
    }

    public static void selectionSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

            System.out.print("After pass " + (i + 1) + ": ");
            printArray(arr, n);
        }
    }

    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        int[] originalArray = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            originalArray[i] = arr[i];
        }

        System.out.println("\nInsertion Sort:");
        insertionSort(arr, n);

        System.arraycopy(originalArray, 0, arr, 0, n);

        System.out.println("\nSelection Sort:");
        selectionSort(arr, n);

        sc.close();
    }
}
