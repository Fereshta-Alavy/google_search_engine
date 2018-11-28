import java.util.Comparator;
import java.util.List;

//This class will call quicksort on a list that will sort it
//it has two functions one that is called on each partition
//and the other one is partitioning and stiching the list
public class Quicksort {

    public int Partition (List<urlObjects> arr, int p, int r) {
        //getting the right element of the array
        urlObjects x = arr.get(r);
        int i = p - 1;  //this is getting a counter starts from one before left

        //this for loop is comparing and swapping
        for (int j = p; j <= r - 1; j++) {
            if (arr.get(j).totalScore <= x.totalScore) {
                i = i + 1;

                //swapping
                urlObjects temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j,temp);
            }
        }

        //another swap before returning
        urlObjects temp = arr.get(i + 1);
        arr.set(i+1 , arr.get(r));
        arr.set(r , temp);
        return i + 1;

    }

    public void QuickSort(List<urlObjects> arr, int p, int r) {
        if (p < r) {
            //calling quick sort on right and left partition of the array
            int q = Partition(arr, p, r);
            QuickSort(arr, p, q - 1);
            QuickSort(arr, q + 1, r);

        }
    }
}
