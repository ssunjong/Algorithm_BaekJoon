package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7576번 : 토마토
 *
 *	@see https://www.acmicpc.net/problem/7576
 *
 */
public class Boj7576 {
	public static final String SPACE = " ";
	private static final int RIPED = 1;
	private static final int RARED = 0;
	private static final int NULL = -1;
	
	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		//	버퍼를 통한 값 입력 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[][] isVisited = new int[N][M];
		
		int raredCnt = 0;
		Queue<Point> queue = new LinkedList<>();
		
		for(int row = 0; row < N; row++){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int col = 0; col < M; col++){						// 익은것, 안익은것, 비어있는것을 차례로 담아줌
				switch(Integer.parseInt(st.nextToken())){
				case RIPED :
					map[row][col] = RIPED;
					queue.offer(new Point(row, col));
					isVisited[row][col] = 1;
					break;
					
				case RARED :
					map[row][col] = RARED;
					raredCnt++;					
					break;
					
				case NULL :
					map[row][col] = NULL;
					break;
				}
			}
		}
		
		br.close();
		
		int maxDays = 1;
		
		while(!queue.isEmpty()){							// BFS 알고리즘 실행
			Point current = queue.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
					if(map[nextRow][nextCol] == RARED && isVisited[nextRow][nextCol] == 0){
						
						queue.offer(new Point(nextRow, nextCol));
						map[nextRow][nextCol] = RIPED;														// 익은것이다 표시
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1; // 단계별 계산
						maxDays = Math.max(maxDays, isVisited[nextRow][nextCol]);			// 최대 일수 계산
						raredCnt--;
					}
				}
			}
		}
		if(raredCnt == 0){								// 다 익었으면 걸린 익는데 날짜를 출력 
			System.out.println(maxDays - 1);
		}
		
		else{													// 안익은게 있다면 -1 출력
			System.out.println(NULL);
		}
		
	}

	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
	 */
	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}