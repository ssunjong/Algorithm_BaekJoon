import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3697 {
	private static final String NEW_LINE = "\n";
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[] parent;
	
	private static class Point implements Comparable<Point>{
		int row;
		int col;
		int h;
		
		public Point(int row, int col, int h) {
			this.row = row;
			this.col = col;
			this.h = h;
		}

		@Override
		public int compareTo(Point p) {
			return this.h > p.h ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			int[][] map = new int[n][m];
			PriorityQueue<Point> summit = new PriorityQueue<>();
			
			init(n, m);
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					summit.add(new Point(i, j, map[i][j]));
				}
			}
			
			sb.append(1).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void init(int N, int M) {
		parent = new int[N * M];
		
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
}
