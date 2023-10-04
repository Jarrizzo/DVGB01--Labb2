import java.io.*;
import java.util.*;

public class labb2 {

    public static void main(String[] args) throws FileNotFoundException  {

        Scanner scan = new Scanner(System.in);
        String pifPath = "pif_2.txt";
        ArrayList <String> SplitFile = new ArrayList<String>();
        BufferedReader read = null;
        try {
            String line;
            read = new BufferedReader(new FileReader(pifPath));

            while((line = read.readLine()) != null){
                ArrayListConverter(SplitFile,line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        String InputsVal = Inputs(scan);

        if(InputsVal.equals("FCFS")){
            FirstComeFirstServe(SplitFile);
        }
        else if(InputsVal.equals("SJF")){
            ShortestJobFirst(SplitFile);
        }
        else if(InputsVal.equals("RR")){
            System.out.print("\nHur lång Timeslice vill du ha?\nInput:");
            int TimeSlice = scan.nextInt();
            System.out.println("");
            RoundRobin(SplitFile,TimeSlice);  
        } 
        scan.close();          
       
    }

    public static ArrayList<String> ArrayListConverter (ArrayList <String> SplitArray,String Input){
        ArrayList <String> ResultArray = new ArrayList<String>();
        String [] Split = Input.split(",");
        for(int i = 0; i < Split.length;i++){
            SplitArray.add(Split[i]);
        }
   
    return ResultArray;

    }
    public static String Inputs(Scanner scan){
        System.out.print("Vilken Algoritm vill du köra?\n------------------------------\nFirst Come First Serve: FCFS\nShortest Job First: SJF\nRound Robin: RR\n------------------------------\nInput: ");
        String AlgoritmVal = scan.nextLine();

    return AlgoritmVal;
    }
    public static  int FirstComeFirstServe(ArrayList <String> InputArray){

        int length = InputArray.size();

         for(int j = 0; j < length-3;j+=3){
            for(int i = 0;i < length-j-3;i+=3){
                if(Integer.parseInt(InputArray.get(i+1)) > Integer.parseInt(InputArray.get(i+4))){
                    String tmp0 = InputArray.get(i);
                    String tmp1 = InputArray.get(i+1);
                    String tmp2 = InputArray.get(i+2);
                    InputArray.set(i,InputArray.get(i+3));
                    InputArray.set(i+1,InputArray.get(i+4));
                    InputArray.set(i+2,InputArray.get(i+5));
                    InputArray.set(i+3,tmp0);
                    InputArray.set(i+4,tmp1);
                    InputArray.set(i+5,tmp2);
                }
            }
        }
        Calculator(InputArray);

    return 0;
    }
    public static int ShortestJobFirst(ArrayList <String> InputArray){

        int CurrentTime = 0;
        ArrayList <String> QueArray = new ArrayList<String>();
        ArrayList <String> CalculatableArray = new ArrayList<String>();
        int loop = InputArray.size();

        for(int p = 0; p < loop/3; p++){
            if(QueArray.size() > 0){
                QueArray.clear();
            }
            for(int i = 0; i < InputArray.size(); i +=3){
                if(Integer.parseInt(InputArray.get(i+1)) <= CurrentTime ){
                    QueArray.add(InputArray.get(i));
                    QueArray.add(InputArray.get(i+1));
                    QueArray.add(InputArray.get(i+2));
                }
            }
            if(QueArray.size() == 0){
                int newCurrentTime = 0;
                for(int i = 0; i < InputArray.size()-3;i+=3){
                    if(Integer.parseInt(InputArray.get(i+1)) < Integer.parseInt(InputArray.get(i+4))){
                        newCurrentTime = Integer.parseInt(InputArray.get(i+1));
                    }
                }
                CurrentTime = newCurrentTime;
                for(int i = 0; i < InputArray.size(); i +=3){
                    if(Integer.parseInt(InputArray.get(i+1)) <= CurrentTime ){
                        QueArray.add(InputArray.get(i));
                        QueArray.add(InputArray.get(i+1));
                        QueArray.add(InputArray.get(i+2));
                    } 
                }               
            }

            for(int j = 0; j < QueArray.size()-3;j+=3){
                for(int i = 0;i < QueArray.size()-3-j;i+=3){
                    if(Integer.parseInt(QueArray.get(i+2)) > Integer.parseInt(QueArray.get(i+5))){
                        String tmp0 = QueArray.get(i);
                        String tmp1 = QueArray.get(i+1);
                        String tmp2 = QueArray.get(i+2);
                        QueArray.set(i,QueArray.get(i+3));
                        QueArray.set(i+1,QueArray.get(i+4));
                        QueArray.set(i+2,QueArray.get(i+5));
                        QueArray.set(i+3,tmp0);
                        QueArray.set(i+4,tmp1);
                        QueArray.set(i+5,tmp2);
                    }
                }
            }
            CalculatableArray.add(QueArray.get(0));
            CalculatableArray.add(QueArray.get(1));
            CalculatableArray.add(QueArray.get(2));
            CurrentTime = CurrentTime + Integer.parseInt(QueArray.get(2));

            for(int i = 0; i < InputArray.size();i+=3){
                if(InputArray.get(i) == (QueArray.get(0))){
                    InputArray.remove(i);
                    InputArray.remove(i);
                    InputArray.remove(i);
                }
            }
        }

        Calculator(CalculatableArray);

        return 0;
    }
   
    private static int RoundRobin(ArrayList<String> InputArray, int TimeSlice) {
        ArrayList <Integer> intArray = new ArrayList<Integer>();
        ArrayList <Integer> CalculatableArray = new ArrayList<Integer>();
        int CurrentBurstTime = 0;
 
        for(int i = 0; i < InputArray.size();i+=3){
            intArray.add(Integer.parseInt(InputArray.get(i)));
            intArray.add(0);
            intArray.add(Integer.parseInt(InputArray.get(i+2)));  
        }

        while(intArray.size() != 0){

            for(int i = 0;i <= intArray.size()-3;i += 3){

                CurrentBurstTime = intArray.get(i+2);

                if(intArray.get(i+2) >= TimeSlice){
                intArray.set(i+2,intArray.get(i+2)-TimeSlice);
                }
                else if(CurrentBurstTime < TimeSlice){    
                intArray.set(i+2,intArray.get(i+2) - CurrentBurstTime);
                }
                for(int j = 0;j <= intArray.size()-3;j+=3){
                    if(intArray.get(i) != intArray.get(j)){
                        if(TimeSlice > CurrentBurstTime){
                            intArray.set(j+1,intArray.get(j+1) + CurrentBurstTime);
                        }
                        else if(TimeSlice <= CurrentBurstTime){
                            intArray.set(j+1,intArray.get(j+1)+TimeSlice);
                        }
                    }
                }
                if(intArray.get(i+2) <= 0){
                    CalculatableArray.add(intArray.get(i));
                    CalculatableArray.add(intArray.get(i+1));
                    CalculatableArray.add(intArray.get(i+2));

                    intArray.remove(i);
                    intArray.remove(i);
                    intArray.remove(i);
                    i-=3;
                }
            }
        }
        for(int j = 0; j < CalculatableArray.size()-3;j+=3){
            for(int i = 0;i < CalculatableArray.size()-j-3;i+=3){
                if(CalculatableArray.get(i) > CalculatableArray.get(i+3)){
                    int tmp0 = CalculatableArray.get(i);
                    int tmp1 = CalculatableArray.get(i+1);
                    int tmp2 = CalculatableArray.get(i+2);
                    CalculatableArray.set(i,CalculatableArray.get(i+3));
                    CalculatableArray.set(i+1,CalculatableArray.get(i+4));
                    CalculatableArray.set(i+2,CalculatableArray.get(i+5));
                    CalculatableArray.set(i+3,tmp0);
                    CalculatableArray.set(i+4,tmp1);
                    CalculatableArray.set(i+5,tmp2);
                }
            }
        }
        System.out.println("PID ID\tWaiting time\tTurnaround time");
        for(int i = 0; i < CalculatableArray.size();i+=3){
            System.out.print(CalculatableArray.get(i) + "\t");
            System.out.print(CalculatableArray.get(i+1) + "\t\t");
            System.out.print((CalculatableArray.get(i+1)+Integer.parseInt(InputArray.get(i+2)))+"\n");
        }

        return 0;
    }
    public static int Calculator(ArrayList <String> InputArray){
        int WaitingTime = 0;
        int TurnAroudTime = 0;
        int indx = 0;
        float AvrageTT = 0;
        float AvrageWT = 0;
        ArrayList <String> FinalArray = new ArrayList<String>();
        ArrayList <Integer> tmpArray = new ArrayList<Integer>();

        for(String p: InputArray){
            System.out.print(p + "\t\t");
            indx++;
            if(indx == 3){
                System.out.println();
                indx = 0;
            }

        }

         for(String i: InputArray){
            tmpArray.add(Integer.parseInt(i));
        }
        for(int i = 0; i < tmpArray.size(); i+=3){
            if(i == 0){
                WaitingTime = 0;
                TurnAroudTime = WaitingTime + tmpArray.get(i+2);
            }
            else if(tmpArray.get(i+1) >= tmpArray.get(i-1)+tmpArray.get(i-2)+WaitingTime){
                WaitingTime = 0;
                TurnAroudTime = WaitingTime + tmpArray.get(i+2); 
            }
            else{
                WaitingTime = (tmpArray.get(i-2) + Integer.parseInt(FinalArray.get(i-1)) - tmpArray.get(i+1));
                TurnAroudTime =WaitingTime + tmpArray.get(i+2);
            }
            
            FinalArray.add(InputArray.get(i));
            FinalArray.add(Integer.toString(WaitingTime));
            TurnAroudTime = WaitingTime + Integer.parseInt(InputArray.get(i+2));
            FinalArray.add(Integer.toString(TurnAroudTime));
            AvrageTT = AvrageTT + TurnAroudTime;
            AvrageWT = AvrageWT + WaitingTime; 
        }
        for(int j = 0; j < FinalArray.size()-3;j+=3){
            for(int i = 0;i < FinalArray.size()-j-3;i+=3){
                if(Integer.parseInt(FinalArray.get(i)) > Integer.parseInt(FinalArray.get(i+3))){
                    String tmp0 = FinalArray.get(i);
                    String tmp1 = FinalArray.get(i+1);
                    String tmp2 = FinalArray.get(i+2);
                    FinalArray.set(i,FinalArray.get(i+3));
                    FinalArray.set(i+1,FinalArray.get(i+4));
                    FinalArray.set(i+2,FinalArray.get(i+5));
                    FinalArray.set(i+3,tmp0);
                    FinalArray.set(i+4,tmp1);
                    FinalArray.set(i+5,tmp2);
                }
            }
        }
        System.out.println("PID ID\t\tWaiting Time\tTurnaroundTime");
        for(String i: FinalArray){
            System.out.print(i + "\t\t");
            indx++;
            if(indx == 3){
                System.out.println();
                indx = 0;
            }
        }
            System.out.println("Avrage Turnaround time: " + AvrageTT/(FinalArray.size()/3));
            System.out.println("Avrage Wait time: " + AvrageWT/(FinalArray.size()/3));

        return 0;
    }
}





