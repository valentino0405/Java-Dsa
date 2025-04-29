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




//i >= 0: We must stay within bounds of the array.
//
//arr[i] > key: If the current element is greater than the key, it means it's out of order. We shift it to the right.
//
//🔄 Each iteration:
//Compares key with arr[i].
//
//If arr[i] is greater, shift arr[i] to the right (arr[i + 1] = arr[i]).
//
//Decrease i to keep checking earlier elements.



//MINE:
//agar i th element is greater than the next element i.e. jth element then ith element ko jth ke place pe daalo by arr[i+1]=arr[i] so both
//both concecutive positions have ith element itself and then ..... in next ..after while loops ends then put up ....key in ith element place