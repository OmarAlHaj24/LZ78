/**
 * @author: Omar Khaled Al Haj   20190351
 * @author: Alaa Mahmoud Ebrahim 20190105
 */

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void compress(String s) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        Scanner sc = new Scanner(br);
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.out"));
        String s1 = sc.nextLine();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        int counter = 1;
        for(int i = 0; i < s1.length(); i++){
            String searchingFor = "";
            int idx = 0;
            for(int j = i; j < s1.length(); j++){
                searchingFor += s1.charAt(j);
                if(dict.containsKey(searchingFor)){
                    idx = dict.get(searchingFor);
                    if(j + 1 == s1.length()){
                        writer.write("<" + idx + "," + "NULL" + ">\n");
                        i = j + 1;
                    }
                }else{
                    dict.put(searchingFor, counter++);
                    writer.write("<" + idx + "," + s1.charAt(j) + ">\n");
                    i = j;
                    break;
                }
            }
        }
        writer.write(" ");
        writer.close();
    }

    public static void decompress(String s) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        Scanner sc = new Scanner(br);
        HashMap <Integer, String> dict = new HashMap<Integer, String>();
        dict.put(0, "");
        StringBuilder result = new StringBuilder();
        int counter = 1;
        while(true){
            int idx = -1;
            String next = "";
            String s1 = sc.nextLine();
            if(s1.equals(" ")) break;
            for(int i = 1; i < s1.length();){
                StringBuilder x = new StringBuilder();
                while(s1.charAt(i) != ',' && s1.charAt(i) != '>'){
                    x.append(s1.charAt(i));
                    i++;
                }
                i++;
                if(idx == -1){
                    idx = Integer.parseInt(x.toString());
                }else{
                    next = x.toString();
                }
            }
            String temp = dict.get(idx);
            if(!next.equals("NULL")) temp += next;
            dict.put(counter, temp);
            counter++;
            result.append(temp);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        compress("input.in");
        decompress("output.out");
    }
}