/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreservices;

import java.util.Scanner;
import registrationservices.*;
import loginservices.*;
/**
 *
 * @author Christina Terradista
 */
public class CoreServices {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int decision = 0;
        int rateDateInput = 3282018;
        Scanner input = new Scanner(System.in);
        LoginService loginInput = new LoginService();
        MemoryUserRepository repositoryOfUsers = new MemoryUserRepository();
        MemoryRatingRepository repositoryOfRatings = new MemoryRatingRepository();
        User newUser = new User();
        Doctor newDoctor = new Doctor();
        
        while(decision != 6){
            System.out.print("Welcome to the Doctor Colosseum! Please determine what you would like to do. "
                    + "\n1: Register as a patient."
                    + "\n2: Register as a doctor."
                    + "\n3: Log in as a patient."
                    + "\n4: Log in as a doctor."
                    + "\n5: Doctor rating."
                    + "\n6: Quit the program.");
            decision = input.nextInt();

            if(decision == 1){
                String emailInput = null;
                String passwordInput = null;
                //register as patient
                System.out.print("\nWhat is your email address? ");
                emailInput = input.next();
                System.out.print("\nWhat is your password? ");
                passwordInput = input.next();
                newUser = new User(emailInput, passwordInput);
                System.out.print("\nYou have successfully registered! You will now "
                        + "return to the main prompt.");
                repositoryOfUsers.saveUser(newUser);
            }
            if(decision == 2){
                String firstNameInput = null;
                String lastNameInput = null;
                String emailInput = null;
                String passwordInput = null;
                String hospitalInput = null;
                String specialtyInput = null;
                //register as doctor
                System.out.print("What is your first name? ");
                firstNameInput = input.next();
                System.out.print("\nWhat is your last name? ");
                lastNameInput = input.next();
                System.out.print("\nWhat is your email address? ");
                emailInput = input.next();
                System.out.print("\nWhat is your password? ");
                passwordInput = input.next();
                System.out.print("\nWhat is your hospital? ");
                hospitalInput = input.next();
                System.out.print("\nWhat is your practice speciality? ");
                specialtyInput = input.next();
                newDoctor = new Doctor(firstNameInput, lastNameInput, emailInput,
                    passwordInput, hospitalInput, specialtyInput);
                System.out.print("\nYou have successfully registered! You will now "
                        + "return to the main prompt.");
                repositoryOfUsers.saveDoctor(newDoctor);
            }
            if(decision == 3){
                //log in as a patient
                String emailInput = null;
                String passwordInput = null;
                System.out.print("\nWhat is your email address? ");
                emailInput = input.next();
                System.out.print("\nWhat is your password? ");
                passwordInput = input.next();
                //User returningUser = new User(emailInput, passwordInput);
                if(loginInput.UserLoginValidator(newUser, emailInput, passwordInput))
                    System.out.print("\nYou have successfully logged in!"
                            + " However, our rating service is under construction. Sorry!\n");
                else System.out.print("\nYou failed to log in successfully. Please try again.");
            }
            if(decision == 4){
                //log in as a doctor
                //log in as a patient
                String emailInput = null;
                String passwordInput = null;
                System.out.print("\nWhat is your email address? ");
                emailInput = input.next();
                System.out.print("\nWhat is your password? ");
                passwordInput = input.next();
                //User returningUser = new User(emailInput, passwordInput);
                if(loginInput.DoctorLoginValidator(newDoctor, emailInput, passwordInput))
                    System.out.print("\nYou have successfully logged in!"
                            + " However, our rating service is under construction. Sorry!\n");
                else System.out.print("\nYou failed to log in successfully. Please try again.");
            }
             if(decision == 5){
                //doctor rating
                String firstNameInput = null;
                String lastNameInput = null;
                DoctorRating rating = new DoctorRating();
                int ratingInput;
                System.out.print("\nWhat is the first name of the doctor?");
                firstNameInput = input.next();
                System.out.print("\nWhat is the last name of the doctor?");
                lastNameInput = input.next();
                System.out.print("\nWhat rating (out of ten) would you give the doctor?");
                ratingInput = input.nextInt();
                if(newDoctor.getFirstName() == firstNameInput && newDoctor.getLastName() == lastNameInput){
                    rating = new DoctorRating(newUser.getEmail(), newDoctor.getEmail(), rateDateInput, ratingInput, newUser, newDoctor);
                    repositoryOfRatings.saveRating(rating);
                }
            }
        }
    }
    
}
