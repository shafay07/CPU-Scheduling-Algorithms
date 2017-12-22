package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
                FCFS();
                break;
            case 2:
                SRTF();
                break;
            case 3:
                RR();
                break;
            case 4:
                Priority();
                break;
            default:
                System.out.println("Invalid selection");
        }
    }
    //First come - First serve
    public static void FCFS() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        System.out.println("Please enter the number of Processes: ");
        n = Integer.parseInt(br.readLine());
        int proc[][] = new int[n + 1][4];
        int btime[] = new int[n+1];
        for(int i = 1; i <= n; i++)
        {
            System.out.println("Please enter the Burst Time for Process " + i + ": ");
            proc[i][1] = Integer.parseInt(br.readLine());
            btime[i] = proc[i][1];
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
        System.out.println("P\t Bt\t\t WT \t TT ");
        for(int i = 1; i <= n; i++)
        {
            System.out.printf("%d\t%2dms\t%2dms\t%2dms",i,btime[i],proc[i][2],proc[i][3]);
            System.out.println();
        }

        System.out.println();

        //Printing the average WT & TT
        float WT = 0,TT = 0;
        for(int i = 1; i <= n; i++)
        {
            WT += proc[i][2];
            TT += proc[i][3];
        }
        WT /= n;
        TT /= n;
        System.out.println("The Average WT is: " + WT + "ms");
        System.out.println("The Average TT is: " + TT + "ms");
    }
    //Shortest remaining time first
    public static void SRTF() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n;
            System.out.println("Please enter the number of Processes: ");
            n = Integer.parseInt(br.readLine());
            int proc[][] = new int[n + 1][4];//proc[][0] is the AT array,[][1] - RT,[][2] - WT,[][3] - TT
            int btime[] = new int[n+1];
            int atime[] = new int[n+1];
            for(int i = 1; i <= n; i++)
            {
                System.out.println("Please enter the Arrival Time for Process " + i + ": ");
                proc[i][0] = Integer.parseInt(br.readLine());
                atime[i] = proc[i][0];
                System.out.println("Please enter the Burst Time for Process " + i + ": ");
                proc[i][1] = Integer.parseInt(br.readLine());
                btime[i] = proc[i][1];
            }
            System.out.println();

            //Calculation of Total Time and Initialization of Time Chart array
            int total_time = 0;
            for(int i = 1; i <= n; i++)
            {
                total_time += proc[i][1];
            }
            int time_chart[] = new int[total_time];

            for(int i = 0; i < total_time; i++)
            {
                //Selection of shortest process which has arrived
                int sel_proc = 0;
                int min = 99999;
                for(int j = 1; j <= n; j++)
                {
                    if(proc[j][0] <= i)//Condition to check if Process has arrived
                    {
                        if(proc[j][1] < min && proc[j][1] != 0)
                        {
                            min = proc[j][1];
                            sel_proc = j;
                        }
                    }
                }

                //Assign selected process to current time in the Chart
                time_chart[i] = sel_proc;

                //Decrement Remaining Time of selected process by 1 since it has been assigned the CPU for 1 unit of time
                proc[sel_proc][1]--;

                //WT and TT Calculation
                for(int j = 1; j <= n; j++)
                {
                    if(proc[j][0] <= i)
                    {
                        if(proc[j][1] != 0)
                        {
                            proc[j][3]++;//If process has arrived and it has not already completed execution its TT is incremented by 1
                            if(j != sel_proc)//If the process has not been currently assigned the CPU and has arrived its WT is incremented by 1
                                proc[j][2]++;
                        }
                        else if(j == sel_proc)//This is a special case in which the process has been assigned CPU and has completed its execution
                            proc[j][3]++;
                    }
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

            }
            System.out.println();
            System.out.println();

            //Printing the WT and TT for each Process
            System.out.println("P\t AT \t BT \t WT \t TT ");
            for(int i = 1; i <= n; i++)
            {
                System.out.printf("%d\t%2dms\t%2dms\t%2dms\t%2dms",i,atime[i],btime[i],proc[i][2],proc[i][3]);
                System.out.println();
            }

            System.out.println();

            //Printing the average WT & TT
            float WT = 0,TT = 0;
            for(int i = 1; i <= n; i++)
            {
                WT += proc[i][2];
                TT += proc[i][3];
            }
            WT /= n;
            TT /= n;
            System.out.println("The Average WT is: " + WT + "ms");
            System.out.println("The Average TT is: " + TT + "ms");
        }
    //Round-Robin
    public static void RR() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Time Quantum: ");
        int q = Integer.parseInt(br.readLine());
        System.out.println("Please enter the number of Processes: ");
        int n = Integer.parseInt(br.readLine());
        int proc[][] = new int[n + 1][4];//proc[][0] is the AT array,[][1] - RT,[][2] - WT,[][3] - TT
        for(int i = 1; i <= n; i++)
        {
            System.out.println("Please enter the Burst Time for Process " + i + ": ");
            proc[i][1] = Integer.parseInt(br.readLine());
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
        int current_q = 0;
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
                    proc[j][3]++;//If process has not completed execution its TT is incremented by 1
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

            //Updating value of sel_proc for next iteration
            current_q++;
            if(current_q == q || proc[sel_proc][1] == 0)//If Time slice has expired or the current process has completed execution
            {
                current_q = 0;
                //This will select the next valid value for sel_proc
                for(int j = 1; j <= n; j++)
                {
                    sel_proc++;
                    if(sel_proc == (n + 1))
                        sel_proc = 1;
                    if(proc[sel_proc][1] != 0)
                        break;
                }
            }
        }
        System.out.println();
        System.out.println();

        //Printing the WT and TT for each Process
        System.out.println("P\t WT  \t TT  ");
        for(int i = 1; i <= n; i++)
        {
            System.out.printf("%d\t%3dms\t%3dms",i,proc[i][2],proc[i][3]);
            System.out.println();
        }

        System.out.println();

        //Printing the average WT & TT
        float WT = 0,TT = 0;
        for(int i = 1; i <= n; i++)
        {
            WT += proc[i][2];
            TT += proc[i][3];
        }
        WT /= n;
        TT /= n;
        System.out.println("The Average WT is: " + WT + "ms");
        System.out.println("The Average TT is: " + TT + "ms");


    }
    //Priority
    public static void Priority() {
        int pos=0,temp = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int i;
        int awt = 0;    //average waiting time
        int att = 0;    //average turn-around time

        int p[]=new int[n];//process
        int at[] = new int[n];//arrival time
        int bt[]=new int[n];//burst time
        int pt[]=new int[n];//priority time
        int wt[]=new int[n];//wait time
        int tat[]=new int[n];//turnaround time
        int k = 1;
        //burst time input
        for(i=0;i<n;i++)
        {
            System.out.println("Enter the burst time for the process " + k +":");
            p[i]=i+1;
            bt[i]=sc.nextInt();
            k++;
        }
        k = 1;
        //priority time input
        for(i=0;i<n;i++)
        {
            System.out.println("Enter priority time for the process " + k +":");
            pt[i] = sc.nextInt();
            k++;
        }
        //priority checking
        for(i=0;i<n;i++){   //current process priority time
            pos=i;
            for(int j=i+1;j<n;j++) //previous process priority time
            {
                if(pt[j]<pt[pos]) //if next is lower than current
                    pos=j;
            }
            temp=pt[pos];
            pt[pos]=pt[i];
            pt[i]=temp;
            temp=p[pos];
            p[pos]=p[i];
            p[i]=temp;
            temp=bt[pos];
            bt[pos]=bt[i];
            bt[i]=temp;
        }
        wt[0]=0;
        for(i=1;i<n;i++)
        {
            wt[i]=0;
            for(int j=0;j<i;j++)
                wt[i]+=bt[j];
        }
        System.out.println("Process\tBT\tpriority\tWT\t  TAT");
        for(i=0;i<n;i++)
        {
            tat[i]=bt[i]+wt[i];
            System.out.println(p[i]+"\t \t"+bt[i]+"\t \t"+pt[i]+"\t\t"+wt[i]+"\t\t"+tat[i]);
        }
        for(i=0;i<n;i++){
            awt = awt + wt[i];
            att = att + tat[i];
        }
        awt = awt/n;
        att = att/n;

        System.out.println("Average waiting time = " + awt);
        System.out.println("Average turn-around time = " + att);
    }
}

