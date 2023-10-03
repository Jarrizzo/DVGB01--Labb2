import java.io.*;
import java.util.*;

public class labb2 {

    public static void main(String[] args) throws FileNotFoundException  {
        
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
        
        String InputsVal = Inputs();

        if(InputsVal.equals("FCFS")){
            FirstComeFirstServe(SplitFile);
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
        int TurnAroudTime = 0;
        int WaitingTime = 0;


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
        }

        for(String i:FinalArray)
            System.out.print("  "+i);

    return 0;
    }

}




