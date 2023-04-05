package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        User user1 = new User();
        Candidate astra = new Candidate("Astra");
        Candidate rosie = new Candidate("Rosie");
        ArrayList<Candidate> listCandidate = new ArrayList<>();
        listCandidate.add(astra);
        listCandidate.add(rosie);

        System.out.println("Hello, dear user");
        while (true){
            System.out.println("Please, enter \n(1) - to REGISTRATION \n(2) - to VOTE \n(3) - to SEE A RESULT \n(4) - to LOG OUT");

            int ans1 = sc.nextInt();
            String a = sc.nextLine();
            if (ans1==1){
                user1.register();
            }
            else if(ans1==2){
                System.out.println("Enter your login");
                String login = sc.nextLine();
                System.out.println("Enter your password");
                String pass = sc.nextLine();
                user1.voting(login,pass,listCandidate);
            }
            else if(ans1==3){
                Candidate winner = mostPowerful(listCandidate);
                System.out.println(winner.getName()+" is a winner of VOTING, with "+winner.getVoices()+" voices");
            }
            else if(ans1==4){
                break;
            }
            else System.out.println("Invalid input");
        }
    }

    public static Candidate mostPowerful(ArrayList<Candidate> listCandidate) {
        double maxValue = -1;
        int indexOfMaxValue = -1;
        for(int i = 0; i < listCandidate.size(); i++) {
            if(listCandidate.get(i).getVoices() > maxValue) {
                indexOfMaxValue = i;
            }
        }
        return listCandidate.get(indexOfMaxValue);
    }

}
