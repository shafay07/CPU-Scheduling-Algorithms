package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception {
        int sel;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("***Welcome to the CPU Scheduling Algorithms***");
        System.out.println("Please select the algorithm you would like to use : \n");
        System.out.println("1: FCFS\n2: SRTF\n3: RR\n4: Priority ");
        sel = Integer.parseInt(br.readLine());
        switch (sel) {
            case 1:
                FCFS f = new FCFS();
                f.FCFS();
                break;
            case 2:
                SRTF s = new SRTF();
                s.SRTF();
                break;
            case 3:
                RR r = new RR();
                r.RR();
                break;
            case 4:
                Priority p = new Priority();
                p.Priority();
                break;
            default:
                System.out.println("Invalid selection");

        }
    }
}
