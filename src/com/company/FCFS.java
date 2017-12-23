package com.company;

/**
 * Created by Shafay Haseeb on 12/17/2017.
 */
import java.io.*;

public class FCFS {

    public void FCFS() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n,diff = 0,last_job = 0;;
        System.out.println("Please enter the number of Processes: ");
        n = Integer.parseInt(br.readLine());
        int proc[][] = new int[n + 1][6];
        int btime[] = new int[n+1];
        for(int i = 1; i <= n; i++)
        {
            System.out.println("Please enter the arrival Time for Process " + i + ": ");
            proc[i][5] = Integer.parseInt(br.readLine());
            System.out.println("Please enter the Burst Time for Process " + i + ": ");
            proc[i][1] = Integer.parseInt(br.readLine());
            btime[i] = proc[i][1];
            System.out.println("Please enter the I/O Time for Process " + i + ": ");
            proc[i][4] = Integer.parseInt(br.readLine());
            if(i == 1 && proc[i][5] != 0){
                diff += proc[i][1] - proc[i][5];
            }
            if(i != 1){
                int temp_1 = i;
                temp_1--;
                last_job = proc[temp_1][5] + proc[temp_1][1];
                diff += proc[i][5] - last_job;
            }

        }
        System.out.println();

        //Calculation of Total Time and Initialization of Time Chart array
        int total_time = 0;
        for(int i = 1; i <= n; i++)
        {
            total_time += proc[i][1];
        }
        int time_chart[] = new int[total_time];

        int sel_proc = 1;

        for(int i = 0; i < total_time; i++)
        {
            //Assign selected process to current time in the Chart
            time_chart[i] = sel_proc;

            //Decrement Remaining Time of selected process by 1 since it has been assigned the CPU for 1 unit of time
            proc[sel_proc][1]--;

            //WT and TT Calculation
            for(int j = 1; j <= n; j++)

            {
                if(proc[j][1] != 0)
                {
                    proc[j][3]++;//If process has completed execution its TT is incremented by 1
                    if(j != sel_proc)//If the process has not been currently assigned the CPU its WT is incremented by 1
                        proc[j][2]++;
                }

                else if(j == sel_proc)//This is a special case in which the process has been assigned CPU and has completed its execution
                    proc[j][3]++;

            }

            //Printing the Time Chart
            if(i != 0)
            {
                if(sel_proc != time_chart[i - 1])
                //If the CPU has been assigned to a different Process we need to print the current value of time and the name of
                //the new Process
                {
                    System.out.print("--" + i + "--P" + sel_proc);
                }
            }
            else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
                System.out.print(i + "--P" + sel_proc);
            if(i == total_time - 1)//All the process names have been printed now we have to print the time at which execution ends
                System.out.print("--" + (i + 1));

            //If current process has been completed we select the next process from the list
            if(proc[sel_proc][1] == 0)
                sel_proc++;
        }
        System.out.println();
        System.out.println();

        //Printing the WT and TT for each Process
        System.out.println("P\t AT\t\t BT\t\t I/O\t WT \t TT ");
        for(int i = 1; i <= n; i++)
        {
            System.out.printf("%d\t%2dms\t%2dms\t%2dms\t%2dms\t%2dms",i,proc[i][5],btime[i],proc[i][4],proc[i][2],proc[i][3]);
            System.out.println();
        }

        System.out.println();

        //Printing the average WT & TT
        float WT = 0,TT = 0, IO = 0,AT = 0,BT = 0;
        for(int i = 1; i <= n; i++)
        {
            WT += proc[i][2];
            TT += proc[i][3];
            IO += proc[i][4];
            AT += proc[i][5];
            BT += btime[i];

        }
        WT /= n;
        TT /= n;
        System.out.println("The Average WT is: " + WT + "ms");
        System.out.println("The Average TT is: " + TT + "ms");
        //System.out.println("The total Burst time is : " + BT);
        //System.out.println("The total IO time is : " + IO);
       // System.out.println("The total arrival time is : " + AT);
        //System.out.println("The total difference in arrival time is : " + diff);
        float ans = CPU_utilization(BT,IO,diff);
        System.out.println("The CPU Utilisation is : "+ans+"%");



    }
    //btime has the burst times.
    //IO is proc[i][4].
    //arrival time is proc[i][5].
    public float CPU_utilization(float b_time,float io_time,float idle_time){
        float usage = 0,temp_2 = 0;
        temp_2 = idle_time += io_time;
        usage = temp_2 / b_time;
        usage *= 100;
        return usage;
    }
}