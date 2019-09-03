import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
	HackerLand National Bank has a simple policy for warning clients about possible 
	fraudulent account activity. If the amount spent by a client on a particular day 
	is greater than or equal to 2x the client's median spending for a trailing 
	number of days, they send the client a notification about potential fraud. 
	The bank doesn't send the client any notifications until they have at least 
	that trailing number of prior days' transaction data.
	
	Given the number of trailing days d and a client's total daily expenditures for a 
	period of n days, find and print the number of times the client will receive a 
	notification over all n days.
*/

class FraudulentActivityNotification{
	public static void main(String[] args) {
		// Fraudulent Activity Notification
		int[] expenditure = {2, 3, 4, 2, 3, 6, 8, 4, 5};
		
		System.out.println(Arrays.toString(expenditure));
		
		int d = 5;
		
		System.out.println(activityNotifications(expenditure, d));
	}
	
	public static int activityNotifications(int[] expenditure, int d) {
		int MAX_EXPENDITURE = 200;
		
//		int[] sorted = Arrays.stream(expenditure)
//							.sorted()
//							.toArray();
//		
//		double median = (sorted.length % 2 == 1) ? 
//						sorted[((sorted.length + 1) / 2) - 1] : 
//								(sorted[((sorted.length) / 2) - 1] +
//										sorted[((sorted.length) / 2)]) / 2.0;
						
//		System.out.println(median);
//		System.out.println(7/2.0);
//		
//		System.out.println(5 * 2.5);
//		
//		
//		System.out.println(Arrays.toString(sorted));
//		System.out.println((sorted.length % 2 == 1) ? 
//											sorted[((sorted.length + 1) / 2) - 1] : 
//												(sorted[((sorted.length) / 2) - 1] +" "+
//												sorted[((sorted.length) / 2)]));
		
		
		
		int n = expenditure.length;
		int re = 0;
        int[] histogram = new int[MAX_EXPENDITURE + 1];

        // Carry a histogram of the last d expenditures
        for (int i = 0; i < d; i++) {
            histogram[expenditure[i]] = histogram[expenditure[i]] + 1;
        }
        
        System.out.println(Arrays.toString(histogram));
        
        for (int i = d; i < n; i++) {
            int cursor = 0;
            int currentAmount = expenditure[i];
            double median = 0;
            int left = -1;
            int right = -1;
            for (int e = 0; e <= MAX_EXPENDITURE; e++) {
                cursor += histogram[e];
                System.out.println("\nCursor = "+cursor);
                System.out.println("d = " + d);
                System.out.println("e = " + e);
                if (d % 2 == 1) {
                    // Odd -> Pick middle one for median
                    if (cursor >= d / 2 + 1) {
                        median = e;
                        System.out.println("\nMedian: "+median);
                        break;
                    }
                } else {
                    // Even -> Pick average of two middle values for median
                    if (cursor == d / 2) {
                        left = e;
                        System.out.println("Left: "+left);
                    }

                    if (cursor > d / 2 && left != -1) {
                        right = e;
                        median = (left + right) / 2.0;
                        System.out.println("Right: "+right);
                        System.out.println("Median: "+median);
                        break;
                    }

                    if (cursor > d / 2 && left == -1) {
                        median = e;
                        System.out.println("Median: "+median);
                        break;
                    }
                }
                
                
            }
            if (currentAmount >= 2 * median) {
                re++;
            }
            
//            System.out.println("i = " + i);
//            System.out.println("d = " + d);
            // Update histogram: slide window 1 index to right
            histogram[expenditure[i - d]] = histogram[expenditure[i - d]] - 1;
//            System.out.println(Arrays.toString(histogram));
            histogram[expenditure[i]] = histogram[expenditure[i]] + 1;
            System.out.println(Arrays.toString(histogram));
        }
        
        return re;
		
//		int notification = 0;
//		for(int i = 0; i < expenditure.length - d; i++) {
//			int[] temp = new int[5];
//			int track = 0;
//			for(int j = i; j < i + d; j++) {
//				System.out.print(expenditure[j] + " ");
//				temp[track] = expenditure[j];
//				track++;
//				
//			}
//			int[] sorted = Arrays.stream(temp)
//					.sorted()
//					.toArray();
//
//			double median = (sorted.length % 2 == 1) ? 
//							sorted[((sorted.length + 1) / 2) - 1] : 
//									(sorted[((sorted.length) / 2) - 1] +
//											sorted[((sorted.length) / 2)]) / 2.0;
//							
//			System.out.println("\n" + Arrays.toString(temp));
//			System.out.println(Arrays.toString(sorted));
//			System.out.println(median);
//			System.out.println(expenditure[i + d]);
//			System.out.println();
//			
//			if(expenditure[i + d] >= (2 * median)) {
//				notification++;
//				System.out.println("There: " + expenditure[i + d]);
//			}
//		}
//		
//		
//		return notification;
	}
}