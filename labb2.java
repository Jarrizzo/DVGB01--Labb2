import java.io.*;
import java.util.*;

public class labb2 {

    public static void main(String[] args) throws FileNotFoundException  {
        
        String pifPath = "pif_1.txt";
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
        
        String InputsVal = Inputs();

        if(InputsVal.equals("FCFS")){
            FirstComeFirstServe(SplitFile);
        }
        else if(InputsVal.equals("SJF")){
            ShortestJobFirst(SplitFile);
        }
    }

    public static ArrayList<String> ArrayListConverter (ArrayList <String> SplitArray,String Input){
        ArrayList <String> ResultArray = new ArrayList<String>();
        String [] Split = Input.split(",");
        for(int i = 0; i < Split.length;i++){
            SplitArray.add(Split[i]);
        }
   
    return ResultArray;

    }
    public static String Inputs(){
        System.out.println("Vilken Algoritm vill du köra?\n------------------------------\nFirst Come First Serve: FCFS\nShortest Job First: SJF\nRound Robin: RR\n------------------------------\nInput: ");
        Scanner scan = new Scanner(System.in);
        String AlgoritmVal = scan.nextLine();
        scan.close();

    return AlgoritmVal;
    }

    public static  int FirstComeFirstServe(ArrayList <String> InputArray){

        int length = InputArray.size();
        ArrayList <Integer> tmpArray = new ArrayList<Integer>();
        ArrayList <String> FinalArray = new ArrayList<String>();
        FinalArray.add("PID ID");
        FinalArray.add("Wait Time");
        FinalArray.add("Turnaround Time");
        int TurnAroudTime = 0;
        int WaitingTime = 0;
        float AvrageTT = 0;
        float AvrageWT = 0;
        int indx = 0;

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
        for(String i: InputArray){
            tmpArray.add(Integer.parseInt(i));
        }
        for(int i = 0; i < length; i+=3){
            if(i == 0){
                WaitingTime = 0;
                TurnAroudTime = WaitingTime + tmpArray.get(i+2);
            }
            else if(tmpArray.get(i+1) >= tmpArray.get(i-1)+tmpArray.get(i-2)){
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

  for(int j = 3; j < FinalArray.size();j+=3){
            for(int i = 3;i < FinalArray.size()-j;i+=3){
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
        for(String i: FinalArray){
            System.out.print(i + "\t");
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

    public static int ShortestJobFirst(ArrayList <String> InputArray){

        int CurrentTime = 0;
        ArrayList <String> QueArray = new ArrayList<String>();
        ArrayList <String> FinalArray = new ArrayList<String>();
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

        for(int i = 0; i < CalculatableArray.size(); i+=3){
            System.out.println(CalculatableArray.get(i));
        }

        return 0;
    }

}




