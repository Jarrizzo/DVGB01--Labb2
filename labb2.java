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
        System.out.println(length);

    return 0;
    }
}




