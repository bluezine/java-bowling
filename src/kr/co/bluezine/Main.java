package kr.co.bluezine;

import java.util.Scanner;

public class Main {
	private static final int GAMECOUNT = 10;
	/*
	 * 	Normal 0
	 *  Strike 1
	 *  Spare  2
	 */
	public static void main(String args[]) {
		int score[][] = new int[GAMECOUNT][6];
		for (int i=0;i<GAMECOUNT;i++) {
			score[i][0] = i+1;
			for (int j=0;j<3;j++) {
				if (i != GAMECOUNT-1) {
					if (j==2) {
						break;
					}
				}
				System.out.print("Game " + (i+1) + "-Pitch " + (j+1) + " : ");
				Scanner scanner = new Scanner(System.in);
				score[i][j+1] = scanner.nextInt();
				if (i == GAMECOUNT-1 && j == 1 && score[i][j+1] + score[i][j] != 10) {
					break;
				}
				if (score[i][j+1] == 10 && i!=GAMECOUNT-1) {
					break;
				}
			}
			for (int j=1;j<4;j++) {
				score[i][4]+=score[i][j];
			}
		}
		
		for (int i=0;i<GAMECOUNT;i++) {
			if (score[i][1] == 10) {
				score[i][5] = 1;
				continue;
			}
			if (score[i][1] + score[i][2] == 10)
				score[i][5] = 2;
		}
		
		for (int i=0;i<GAMECOUNT;i++) {
			if (score[i][5] == 1 && i < GAMECOUNT - 2) { // Strike
				if (score[i+1][5] == 1)
					score[i][4]+=20;
				else {
					score[i][4]+=score[i+1][1];
					score[i][4]+=score[i+1][2];
				}
			} else if (score[i][5] == 1 && i == GAMECOUNT - 2) {
				score[i][4]+=score[i+1][1];
				score[i][4]+=score[i+1][2];
			} else if (score[i][5] == 2 && i < GAMECOUNT-1) { // Spare
				score[i][4]+=score[i+1][1];
			}
		}
		
		
		System.out.println("\nDebug : ");
		for (int i=0;i<6;i++) {
			switch (i) {
			case 0 :
				System.out.print("Game    : ");
				break;
			case 1 :
				System.out.print("Pitch 1 : ");
				break;
			case 2 :
				System.out.print("Pitch 2 : ");
				break;
			case 3:
				System.out.print("Pitch 3 : ");
				break;
			case 4:
				System.out.print("Score   : ");
				break;
			case 5:
				System.out.print("Flag    : ");
				break;
			}
			for (int j=0;j<GAMECOUNT;j++) {
				System.out.print(String.format("%2d", score[j][i]) + " ");
			}
			System.out.println();
		}
		
		
		String result[][] = new String[GAMECOUNT][3];
		int sum = 0;
		for (int i=0;i<GAMECOUNT;i++) {
			result[i][0] = String.valueOf(i+1);
			result[i][2] = String.valueOf(sum + score[i][4]);
			sum+=score[i][4];
			if (score[i][5] == 0) {
				result[i][1] = score[i][1] + " " + (score[i][2] == 0 ? "-" : score[i][2]);
			} else if (score[i][5] == 1) {
				result[i][1] = "X";
			} else if (score[i][5] == 2) {
				result[i][1] = score[i][1] + " /";
			}
		}
		
		System.out.println("\nTotal Score : ");
		for (int i=0;i<3;i++) {
			for (int j=0;j<GAMECOUNT;j++) {
				System.out.print(String.format("%6s", result[j][i]));
			}
			System.out.println();
		}
	}
}