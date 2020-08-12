import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.*;
public class luckBalance {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        List<Integer> important = new ArrayList<Integer>();
        List<Integer> unimportant = new ArrayList<Integer>();
        int wins = 0;
        Collections.sort(important);

        for (int[] arr: contests){
            if (arr[1] == 1)
                important.add(arr[0]);
            else
                unimportant.add(arr[0]);
        }

        for (int i = 0; i < important.size()-k; i++){
            wins += important.get(i);
        }
        
        int maxLuck = important.stream()
        .skip(important.size()-k)
        .reduce(0,    Integer::sum);
        
        int maxuLuck = unimportant.stream()
        .reduce(0, Integer::sum);

        return (maxLuck + maxuLuck) - wins;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
