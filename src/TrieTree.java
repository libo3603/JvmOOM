import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieTree {

		public static void main(String[] args) {
			List<List<Integer>> grid = new ArrayList<List<Integer>>();
			for (int i=0;i<5;i++) {

				List<Integer> row = new ArrayList<Integer>();
				for (int j=0;j<5;j++) {
					row.add(0);
				}
				grid.add(row);
			}
			
			grid.get(0).set(0, 1);
			grid.get(0).set(2, 1);
			grid.get(0).set(3, 1);
			grid.get(1).set(1, 1);
			grid.get(1).set(4, 1);
			grid.get(2).set(0, 1);
			grid.get(2).set(2, 1);
			grid.get(2).set(3, 1);
			grid.get(3).set(0, 1);
			grid.get(3).set(2, 1);
			grid.get(3).set(3, 1);
			grid.get(4).set(1, 1);
			grid.get(4).set(4, 1);
			
			List<Integer> queries = new ArrayList<Integer>();
			queries.add(1);
			queries.add(2);
			queries.add(3);
			queries.add(4);
			queries.add(5);
			Result.onesGroups(grid, queries );
		}
	
}


class Result {

    /*
     * Complete the 'onesGroups' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY grid
     *  2. INTEGER_ARRAY queries
     */

    public static List<Integer> onesGroups(List<List<Integer>> grid, List<Integer> queries) {
    // Write your code here
        List<Integer> result = new ArrayList<Integer>();
        Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();

        int m = grid.size();
        if (m==0) {
            int qsize = queries.size();
            for (int i=0;i<qsize;i++) {
                result.add(0);
            }
            return result;
        }

        int n = grid.size();
        if (n==0) {
            int qsize = queries.size();
            for (int i = 0; i < qsize; i++) {
                result.add(0);
            }
            return result;
        }

        int[][] cflags = new int[m][n];
        int groups = 1;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {

                int gval = grid.get(i).get(j);
                int cflag = cflags[i][j];
                if (gval == 1 && cflag==0) {
                    searchOneGroup(grid, cflags, i,j, groups++);
                }
            }
        }

        Map<Integer, Integer> groupCount = new HashMap<Integer,Integer>();
        for (int i=0; i<m; i++) {
          for (int j=0; j<n;j++) {
            int cflag = cflags[i][j];
            if (cflag>0) {
              Integer gcnt = groupCount.get(cflag);
              if (gcnt==null) {
                groupCount.put(cflag, 1);
              } else {
                groupCount.put(cflag, gcnt+1);
              }
            }
          }
        }

        for (Integer gcnt: groupCount.values()) {
          Integer cntVal = cntMap.get(gcnt);
          if (cntVal==null) {
            cntMap.put(gcnt, 1);
          } else {
            cntMap.put(gcnt, cntVal+1);
          }
        }

        for (Integer query: queries) {
            if (cntMap.containsKey(query)) {
                result.add(cntMap.get(query));
            } else {
                result.add(0);
            }
        }

        return result;
    }

    public static void searchOneGroup(List<List<Integer>> grid, int[][] cflags, int m, int n, int group) {
       if (m<0 || m>=grid.size() || n>=grid.get(0).size() || n<0) {
         return;
       }

       int flag = cflags[m][n];
       if (flag>0) {
         return;
       }

       int gval = grid.get(m).get(n);
       if (gval==0) {
         return;
       }

       cflags[m][n] = group;
       searchOneGroup(grid, cflags, m+1, n, group);
       searchOneGroup(grid, cflags, m-1, n, group);
       searchOneGroup(grid, cflags, m, n+1, group);
       searchOneGroup(grid, cflags, m, n-1, group);
    }
}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int gridRows = Integer.parseInt(bufferedReader.readLine().trim());
        int gridColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> grid = new ArrayList<>();

        for (int i = 0; i < gridRows; i++) {
            String[] gridRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> gridRowItems = new ArrayList<>();

            for (int j = 0; j < gridColumns; j++) {
                int gridItem = Integer.parseInt(gridRowTempItems[j]);
                gridRowItems.add(gridItem);
            }

            grid.add(gridRowItems);
        }

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = new ArrayList<>();

        for (int i = 0; i < queriesCount; i++) {
            int queriesItem = Integer.parseInt(bufferedReader.readLine().trim());
            queries.add(queriesItem);
        }

        List<Integer> result = Result.onesGroups(grid, queries);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
