package milton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class BookSort {
    public static void main(String[] args) throws Exception{
        
        if(args.length != 1) {
            System.out.println("No. of arguments is incorrect");
            System.out.println("Please reenter");
        }

        String fileName = args[0];
        System.out.println("Processing: " + fileName);

        String filePath = String.format("%s",fileName);

        FileReader fr = new FileReader(filePath);
        BufferedReader bfr = new BufferedReader(fr);

        String line;
        Map <String, ArrayList<String>> pubMap = new HashMap<>();
       

        while ((line = bfr.readLine()) != null) {
            String [] allDetail = line.trim().split(","); //split them into an array
            String pubKey = allDetail[11]; //setting pub [11] as pubKey
            ArrayList <String> bookList = new ArrayList<>();//declare the bookList ArrayList within the while loop
            if(!pubMap.containsKey(pubKey)){ //if pubMap does not have pubKey, add it
                
                bookList.add(String.format("%s",allDetail[1])); 
                pubMap.put(pubKey, bookList); 

            } else {pubMap.get(pubKey).add(String.format("%s",allDetail[1]));
            }
       
            }
            
            bfr.close();  //close after while loop or before try statement ends
            
            Set <String> uniquePub = pubMap.keySet();
            Integer count = 0;
           
        
                for (String publishers : uniquePub){//for each pubKey in pubMap
                    System.out.printf("-> Publisher: %s\n", publishers);
                for (String books : pubMap.get(publishers)){ //for each book in ArrayList of books for a publisher
                    count++;
                    if (count == pubMap.get(publishers).size()){
                        count = 0; 
                        
                    } 
                    
                    System.out.printf("%d. %s\n", count, books);
                    
                    
                    }
                    //filewriter wihin first for loop for publisher  
                    String fp = String.format("src/books/%s.csv", publishers);
                    FileWriter fw = new FileWriter(fp,false);
                    BufferedWriter bfw = new BufferedWriter((fw));

                    for (String books : pubMap.get(publishers)){
                        count++;
                        if (count == pubMap.get(publishers).size()){
                            count = 0;   
                        } 
                        String l = String.format("%d. %s\n", count, books);
                        bfw.write(l);
                        

                    }
                    bfw.close();

                }
    }
}
    

