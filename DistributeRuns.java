import java.io.*;
import java.util.ArrayList;

public class DistributeRuns{

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));; 

    //list of temporary files
    public static File[] fileList;
    public static int pointer = 0;
    public static PrintWriter[] writerList;



    /**
     * Constructor class
     * Reads standard input from create runs and distributes them 
     * according to the number of files givem
     * 
     * @param files    Number of files
    */
    public DistributeRuns(int files){
        //check if valid number of files and set it to 2 if not
        if(files<=1){ 
            System.err.println("Number of files must be greater than 1. Defaulted to 2.");
            files = 2;
        }

        //creates the files and adds them to the file list
        fileList = new File[files];
        writerList = new PrintWriter[files];
        createFiles(files);

        // accept runs
        accept();
    }

    /**
     * Accepts standart input
    */
    public void accept(){
        // read from standard input
        
        try{
            String run = reader.readLine();
            
            // System.out.println(s);
            while(run!=null){
                // need to distribute the inputs evenly among the files
                distribute(run);
                run = reader.readLine();
            }
            
        } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Distributes the input run into text files 
     * @param run   The run to be distributed
    */
    public void distribute(String run){
        
        // check if reached the end of the file list and reset pointer
        if(pointer == fileList.length){
            pointer = 0;
        }

        try{
            // print to the correct file and point to the next writer
            PrintWriter fileWriter = writerList[pointer];
            fileWriter.println(run);  
            pointer++;     
            
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }        
    }
        
    /**
     * Creates temporary text files and lists all the printwriters
     * @param files Number of files to create
    */
    public void createFiles(int files){
        for(int i=0; i<files; i++){
            try{
                String filename = "temp"+ (i+1);
                fileList[i] = new File(filename);
                writerList[i] = new PrintWriter(new BufferedWriter(new FileWriter(filename)), true);
            }catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }
    }
}