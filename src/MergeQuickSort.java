import java.util.Scanner;

public class MergeQuickSort {

    public static void merge(int[] A, int low, int mid, int high, int[] pass) {
        int leftIndex = low;
        int rightIndex = mid + 1;
        int mergedIndex = low;

        int[] merged = new int[A.length];  // Temporary merged array

        // Merge elements from both halves into 'merged' array
        while (leftIndex <= mid && rightIndex <= high) {
            if (A[leftIndex] <= A[rightIndex]) {
                merged[mergedIndex] = A[leftIndex];
                leftIndex++;
            } else {
                merged[mergedIndex] = A[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        // Copy remaining elements from Left half (if any)
        while (leftIndex <= mid) {
            merged[mergedIndex] = A[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        // Copy remaining elements from Right half (if any)
        while (rightIndex <= high) {
            merged[mergedIndex] = A[rightIndex];
            rightIndex++;
            mergedIndex++;
        }

        // Copy merged content back to original array A
        for (int k = low; k <= high; k++) {
            A[k] = merged[k];
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
            quickSort(A, lb, pivot - 1, pass);// from previous ub stops that is returned and that is the place where pivot is placed so that till that index is sorted out so pivot -1 and pivot +1
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


//basically first see down<up eg 0<10 then make 1 st element as pivot and check first condition
//1 st condition : incoming elements agar pivot yani arr[0] se chota hai toh ....down ++ karte ja and check everytime down<up
//if 1 st fails then 2 nd condition: arr ka end se shuru kar and check kar ki incoming elements are greater than pivot i.e. arr[0]
//if yes then up--
//if that also fails ...do jidar down stopped and jidar up stopped so swap both of it
//then again check main while loop now down and up eg could be 2 and 10 so yes 2<10
//continue
//continue from down and check whether  from 2 the both conditions
//at one point after these two conditions and swaps
//point will come where up has crossed down so that means down<up wrong
//so we swap place  A[lb] = A[up];
//        A[up] = pivot;
//ellement at lb i.e. one posititon ahead of dwon in the left direction in down ...i.e. of up
//element at up place the pivot
//lb<-up
//up<-pivot element
