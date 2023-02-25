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
        
        int[] count = new int[maxArrayElement + 1]; //java autofills with 0
        
        for(int j = 0; j < arrayLength; j++) {
            count[A[j]] += 1;
        }

        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int k = 1; k < maxArrayElement + 1; k++) {
            if(count[k] > 0){
                for(int l = 0; l < sum; l++) {
                    if(dp[l] >= 0) {
                        dp[l] = count[k];
                    } else if (l >= k && dp[l - k] > 0) {
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
