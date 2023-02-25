/**
 * Solution written in Java. Source: https://codility.com/media/train/solution-min-abs-sum.pdf
 * 
 */

import java.util.*;

class Solution {
    public int solution(int[] A) {
        
        int arrayLength = A.length;
        int maxArrayElement = 0;
        int sum = 0;
        
        for(int i = 0; i < arrayLength; i++) {
            A[i] = Math.abs(A[i]);
            maxArrayElement = Math.max(A[i], maxArrayElement);
            sum+=A[i];
        }
        
        //create an array whose size is the maximum number in the given array.
        //basically, this is just a counter.
        //
        //e.g given array = {1,1,4,4,4,5}
        //
        //count array = {0,2,0,0,3,1} 
        //i.e. 0th index has count of 0, 1st index has count of 2, 
        //2nd and 3rd are zero since they aren't containted in the given array etc.
        int[] count = new int[maxArrayElement + 1]; //java autofills with 0
        
        for(int j = 0; j < arrayLength; j++) {
            count[A[j]] += 1;
        }
        
        //initialize an array with default value of -1 and size of 
        //"sum + 1" (see above). Set first element to 0.
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        //"k" here pertains to a possible element in the given array. 
        for(int k = 1; k < maxArrayElement + 1; k++) {
            //we only care about existing values in the given array so we filter out others.
            if(count[k] > 0){ 


                for(int l = 0; l < sum; l++) {
                    if(dp[l] >= 0) { //for first time, each loop goes through the else-if block first.
                        dp[l] = count[k];
                    } else if (l >= k && dp[l - k] > 0) {
                        // l -> "sum"
                        // 
                        dp[l] = dp[l - k] - 1;
                    }
                }
            }
        }

        int result = sum;
        for(int p = 0; p < sum / 2 + 1; p++){
            if(dp[p] >= 0) {
                result = Math.min(result, sum - 2*p);
            }
        }

        return result;
    }
}
