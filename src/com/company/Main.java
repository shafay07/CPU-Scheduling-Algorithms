package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        int a[][];
        System.out.println("\t\t***Welcome to CPU-Schedular***");
        a = Input();
        for(int row = 0; row < a.length ; row++){
            for(int col = 0; col < a[row].length ; col++){
                System.out.println(a[row][col]);
            }
            System.out.println("\n");
        }



    }
    public static int [][] Input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        System.out.println("Please enter the number of Processes: ");
        n = Integer.parseInt(br.readLine());
        int proc[][] = new int[n + 1][4];
        for(int i = 1; i <= n; i++)
        {
            System.out.println("Please enter the Burst Time for Process " + i + ": ");
            proc[i][1] = Integer.parseInt(br.readLine());
        }
        System.out.println();
        return proc;
    }

}
