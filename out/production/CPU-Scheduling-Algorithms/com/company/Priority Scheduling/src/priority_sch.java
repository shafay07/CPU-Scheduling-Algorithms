import java.util.Scanner;

class priority_sch {
        static int pos=0;
		static int temp;



public static void main(String args[])
    
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n=sc.nextInt();
        int i;
        System.out.println("Enter the burst times:");
        int p[]=new int[n];
        int bt[]=new int[n];
        int pt[]=new int[n];
        int wt[]=new int[n];
        int tat[]=new int[n];
    
        for(i=0;i<n;i++)
        	{
        		p[i]=i+1;
        		bt[i]=sc.nextInt();
        	}
        
        System.out.println("Enter priority time:");
        
        for(i=0;i< n; i++)
        pt[i]=sc.nextInt();

        for(i=0;i<n;i++){pos=i;
         for(int j=i+1;j<n;j++)
        	{
        	if(pt[j]<pt[pos])
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
        
        System.out.println("Process\tBT\tpriority\twaiting time\tTAT");
        
        for(i=0;i<n;i++)
        	{
        	 tat[i]=bt[i]+wt[i];
        	 System.out.println(p[i]+"\t"+bt[i]+"\t"+pt[i]+"\t\t"+wt[i]+"\t\t"+tat[i]);
        	}
     }
}