package org.launchcode.techjobs.console;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    System.out.println("Search all fields not yet implemented.");
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                } if((JobData.findByColumnAndValue(searchField, searchTerm)).isEmpty()){
                    System.out.println("No Jobs Found!");
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];
        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for(Iterator var6 = choices.keySet().iterator(); var6.hasNext(); i = i + 1) {
            String choiceKey = (String)var6.next();
            choiceKeys[i] = choiceKey;
        }

        Integer choiceIdx;
        do {
            System.out.println("\n" + menuHeader);
            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j=j+1) {
                System.out.println(j + " - " + (String)choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx >= 0 && choiceIdx < choiceKeys.length) {
                validChoice = true;
            }else{
                System.out.println("Invalid choice. Try again.");
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        //--5/04/2021 Claire Patterson--implemented printJobs method by adding
        // the following code to iterate through an Arraylist<HashMap<String,String>>
        //that holds job data from the Jobs_data.csv file and print that data.
        for (HashMap<String, String> map : someJobs) {
            System.out.println("*****");

            for (Map.Entry<String, String> entry : map.entrySet()) {

                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
