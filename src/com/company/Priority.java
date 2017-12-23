package com.company;

/**
 * Created by Shafay Haseeb on 12/17/2017.
 */
import java.util.Scanner;

public class Priority {

    public void Priority() throws Exception
        {
            int pos=0,temp = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number of processes:");
            int n = sc.nextInt();
            int i;
            int awt = 0;
            int att = 0;

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

