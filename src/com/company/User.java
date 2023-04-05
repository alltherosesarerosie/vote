package com.company;

import java.io.*;
import java.util.*;

public class User implements Registration{
    Scanner sc = new Scanner(System.in);
    private String login;
    private String pass;
    private String status = "_";

    public void setStatus (){
        this.status = "done";
    }

    public String getStatus(){
        return status;
    }

    @Override
    public void register() {
        System.out.println("--- Registration ---");
        System.out.println("Input your login");
        login = sc.nextLine();
        System.out.println("Input your password");
        pass = sc.nextLine();
        registerToDB(login, pass, status);
    }

    private void registerToDB(String log_, String pass_, String str_)  {
        try (FileWriter f = new FileWriter("users.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
             p.println(log_+" "+pass_+" "+str_);
             p.close();
             System.out.println("Successfully added to the Database.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void voting(String login, String pass, ArrayList<Candidate> list) throws IOException{
        int ind = 0;
        ArrayList<List<String>> users = usersList();
        System.out.println(users);

        for (List<String> user: users) {
            if(((user.get(0) + user.get(1)).equals(login + pass) && Objects.equals(user.get(2), "_"))){
                ind = users.indexOf(user);
                for (int i = 0; i < user.size()-1; i++) {
                    System.out.println(i+" "+list.get(i).getName());
                }
                System.out.println("Input a number of CANDIDATE");
                int canInd = sc.nextInt();
                setStatus();
                list.get(canInd).addVoice();
                System.out.println(
                        list.get(canInd).getVoices());
            }
            else {
                System.out.println("invalid input");
            }
        }
        users.get(ind).set(2,getStatus());
    }

    public static ArrayList<List<String>> usersList() throws IOException {
        ArrayList<List<String>> users = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        String st;
        while((st = br.readLine()) != null) {
            List<String> l = Arrays.asList(st.split(" "));
            users.add(l);
        }
        return users;
    }
}
