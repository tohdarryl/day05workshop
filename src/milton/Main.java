package milton;


import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;






public class Main {

    public static final String HEADER = "Word,Count\n";
    public static void main(String[] args) throws Exception{ // dont need throws exception if using try - catch
        
        
        
        if(args.length != 1) {
            System.out.println("No. of arguments is incorrect");
            System.out.println("Please reenter");
        }

        String fileName = args[0];
        System.out.println("Processing: " + fileName);

        String filePath = String.format("%s", fileName);

        try{
        FileReader fr = new FileReader(filePath);
        BufferedReader bfr = new BufferedReader(fr);

        String line;
        Integer count = 0;
        Integer totalWords = 0;
        Map <String, Integer> wordMap = new HashMap<>();

        while (null != (line = bfr.readLine())){ //null means end of file
            System.out.print(count + " -> ");
            System.out.println(line);
            count++;

            if (count > 100) {
                break;
            } 
            String [] words = line.trim().split(" "); //have to be in loop to split all lines
            totalWords += words.length;

            /*
             * for (String eachWord: words){
             * Integer v = wordMap.getOrDefault(eachWord,0); //if the eachWord exists, get current value; if doesnt set to 0
             * v++; 0 + 1 = 1
             * wordMap.put(eachWord,v);
             * }
             */

            for (String eachWord : words) { //for word in words[]
                 String clearWord = eachWord.replaceAll(",", "");//replaces eachWord and assigned to clearWord
                if (wordMap.containsKey(clearWord)) {
                    Integer v = wordMap.get(clearWord) + 1;//plus 1 to add for duplicates;
                    wordMap.put(clearWord, v); 
                } else {
                    wordMap.put(clearWord, 1);
                }

            }
            }
            Set <String> uniqueWords = wordMap.keySet(); //returns set view of all userKey
            
            System.out.println("Total words in first 100 lines: " + totalWords);
            System.out.println("No. of unique words: " + uniqueWords.size());
            
            for (String eachWord : uniqueWords){
                System.out.println(eachWord + wordMap.get(eachWord)); //return eachWord and no.of occurrence
            }

            //Create CSV file
            FileOutputStream fos = new FileOutputStream(args[1]);//args[1] = word_count.csv
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            //FileWriter fw = new FileWriter(args[1],false); can use this too
            //BufferedWriter bfw = new BufferedWriter(fw); can use this too

            osw.write(HEADER);
            for (String eachWord : wordMap.keySet()) {//to get all keys and assign to a String
                String l = String.format("%s,%d\n", eachWord, wordMap.get(eachWord));//string.format is used to combine Strings
                osw.write(l);
            }
            osw.flush();
            osw.close();
            fos.close();
            //Main



            
 
        /* Integer i = 1
            while (i <=100) 
         * line = bfr.readline();
         * if (null == line)
         * break;
         * System.out.pringln(line)
         * i++
        */
        bfr.close(); 
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // String filePath2 = "src/milton/words.csv";
        // try{
        //     FileWriter fw = new FileWriter(filePath2,false);
        //     BufferedWriter bfw = new BufferedWriter(fw);

        //     
        //     bfw.write("word,count\n");
        

        //     bfw.flush();
        //     bfw.close();

        // } catch (IOException e){
        //     e.printStackTrace();
        // }




    }
}






