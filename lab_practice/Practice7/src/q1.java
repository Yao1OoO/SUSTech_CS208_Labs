import java.util.ArrayList;
import java.util.Arrays;

public class q1 {
    public static void main(String[] args) {
        int[] arr = {7,2,3,1,8,4};
        System.out.println(mergeSort(arr));
        System.out.println(Arrays.toString(arr));
    }
    public static int mergeSort(int[] nums) {
        return re(nums,0,nums.length-1);
    }

    public static int re(int[]arr, int l, int r){
        if (l >= r) {
            return 0;
        }else {
            int mid = (l+r)/2;
            int num1 = re(arr, l,mid);
            int num2 = re(arr,mid+1,r);
            int num3 = merge(arr,l,mid,r);
            return num1+num2+num3;
        }
    }

    public static int merge(int[] arr, int l, int mid, int r){
        int[] temp = new int[arr.length];
        int i = l,j = mid+1, k = l;
        int sum = 0;
        while(i<=mid && j<=r){
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else{
                sum+= mid +1 - i;
                temp[k++] = arr[j++];
            }
        }

        while(i <= mid){
            temp[k++] = arr[i++];
        }
        while(j<=r){
            temp[k++] =arr[j++];
        }
        for (int m = l; m <=r; m++) {
            arr[m] = temp[m];
        }
        return sum;
    }
}