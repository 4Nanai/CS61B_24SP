import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        for (Integer i : L) {
            sum += i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> evens = new ArrayList<>();
        for (Integer i : L) {
            if (i % 2 == 0) {
                evens.add(i);
            }
        }
        return evens;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> ans = new ArrayList<>();
        List<Integer> L1_Copy = new ArrayList<>(L1);
        List<Integer> L2_Copy = new ArrayList<>(L2);
        L1_Copy.sort(Integer::compare);
        L2_Copy.sort(Integer::compare);
        int L1_Cur = 0;
        int L2_Cur = 0;
        while (L1_Cur < L1_Copy.size() && L2_Cur < L2_Copy.size()) {
            int val1 = L1_Copy.get(L1_Cur);
            int val2 = L2_Copy.get(L2_Cur);
            if (val1 == val2) {
                ans.add(L1_Copy.get(L1_Cur));
                L1_Cur++;
                L2_Cur++;
            }
            else if (L1_Copy.get(L1_Cur) > L2_Copy.get(L2_Cur)) {
                L2_Cur++;
            }
            else {
                L1_Cur++;
            }
        }
        return ans;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int ans = 0;
        for (String w : words) {
            for (char cc : w.toCharArray()) {
                if (cc == c) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
