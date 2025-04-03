import java.util.Scanner;

public class MergeQuickSort {

    public static void merge(int[] A, int low, int mid, int high, int[] pass) {
        int h = low, i = low, j = mid + 1;
        int[] B = new int[A.length];

        while (h <= mid && j <= high) {
            if (A[h] <= A[j]) {
                B[i] = A[h];
                h++;
            } else {
                B[i] = A[j];
                j++;
            }
            i++;
        }

        while (h <= mid) {
            B[i] = A[h];
            h++;
            i++;
        }

        while (j <= high) {
            B[i] = A[j];
            j++;
            i++;
        }

        for (int k = low; k <= high; k++) {
            A[k] = B[k];
        }

        System.out.print("After merging, pass " + pass[0]++ + ": ");
        printArray(A, low, high);
    }

    public static void mergeSort(int[] A, int low, int high, int[] pass) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(A, low, mid, pass);
            mergeSort(A, mid + 1, high, pass);
            merge(A, low, mid, high, pass);
        }
    }

    public static int partition(int[] A, int lb, int ub, int[] pass) {
        int pivot = A[lb];
        int up = ub;
        int down = lb;

        while (down < up) {
            while (A[down] <= pivot && down < ub) {
                down++;
            }
            while (A[up] > pivot) {
                up--;
            }
            if (down < up) {
                int temp = A[down];
                A[down] = A[up];
                A[up] = temp;
            }
        }

        A[lb] = A[up];
        A[up] = pivot;

        System.out.print("After partitioning, pass " + pass[0]++ + ": ");
        printArray(A, lb, ub);

        return up;
    }

    public static void quickSort(int[] A, int lb, int ub, int[] pass) {
        if (lb < ub) {
            int pivot = partition(A, lb, ub, pass);
            quickSort(A, lb, pivot - 1, pass);
            quickSort(A, pivot + 1, ub, pass);
        }
    }

    public static void printArray(int[] A, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] A1 = new int[n];
        int[] A2 = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            A1[i] = sc.nextInt();
            A2[i] = A1[i];
        }

        System.out.print("\nOriginal Array: ");
        printArray(A1, 0, n - 1);

        int[] mergePass = {1};
        int[] quickPass = {1};

        System.out.println("\nMerge Sort Passes:");
        mergeSort(A1, 0, n - 1, mergePass);

        System.out.print("\nSorted Array using Merge Sort: ");
        printArray(A1, 0, n - 1);

        System.out.println("\nQuick Sort Passes:");
        quickSort(A2, 0, n - 1, quickPass);

        System.out.print("\nSorted Array using Quick Sort: ");
        printArray(A2, 0, n - 1);

        sc.close();
    }
}
