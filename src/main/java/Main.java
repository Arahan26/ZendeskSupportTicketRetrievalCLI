import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import exception.APIFailureException;
import model.Ticket;
import model.TicketResponse;
import model.ZendeskPojo;

public class Main {

    private int maxPageSize = 25;
    private Scanner scanner;

    public static void main(String[] args) {
        new Main().driver();
        // write your code here

    }

    public void driver() {
        displayWelcomeMessage(); // displays a welcome message on the home page of the app.

        String optionChosen = "";

        while (!optionChosen.equals("3")) {
            optionChosen = displayMainMenuAndGetResponse();
            mainMenuDriver(optionChosen);
        }
    }

    private void mainMenuDriver(String option) {
        switch (option) {
            case "1":
                ticketsViewer();
                break;
            case "2":
                showTicketbyID();
                break;
            case "3":
                break;
            default:
                System.out.println("Please select from the options.");
                break;
        }
    }

    private void showTicketbyID() {
        System.out.print("Enter the ticket ID: ");
        String id = getInput();
        Ticket response = viewTicket(id);

        displayTicketDetails(response);

        /*System.out.println("Press Q to return");
        System.out.println("Enter Quit to exit the program");*/
        System.out.println("Press any key and enter to go back");
        String backString = getInput();

        /*if(backString == "Q") {
            return;
        }else if (backString == "Quit") {
            System.exit(1);
        }
        else
        {
            System.out.println("Please select one of the above options");
        }*/
       /* switch (backString) {
            case "Q":
                return;
            case "Quit":
                System.exit(0);
                break;
            default:
                System.out.println("Please select one of the options");

        }*/

    }

    private void displayTicketDetails(Ticket response) {
        System.out.println("ID: " + response.getId() + "\nSubject\t " + response.getSubject() + "\n\nDescription:\t" + response.getDescription()
                + "\n\nAssigned To:\t" + response.getAssigneeId() + "\n\nStatus:\t\t\t" + response.getStatus());
    }

	/*private void viewTicket(String id) {

	}*/

    private void ticketsViewer() {
        int pageNumber = 1;
        String chosenResponse;

        while (true) {
            ZendeskPojo response = getTickets(pageNumber);
            response.getTickets().forEach(ticket -> displayTicket(ticket));
            System.out.println("\nPage Number: " + pageNumber + "/"
                    + (response.getCount() / maxPageSize + (response.getCount() % maxPageSize == 0 ? 0 : 1)));

            boolean paginationNeeded = response.getCount() > maxPageSize;
            displayTicketsMenuAndGetResponse(paginationNeeded, response, pageNumber);
            while (true) {
                System.out.print("ENTER THE CHOICE: ");
                chosenResponse = getInput();
                List<String> validResponses = getTicketsMenuValidResponses(paginationNeeded, response, pageNumber);
                if (validResponses.contains(chosenResponse))
                    break;
                else
                    System.out.println("Invalid Input. Please Try again.");
            }

            switch (chosenResponse) {
                case "N":
                    pageNumber++;
                    break;
                case "P":
                    pageNumber--;
                    break;
                case "Q":
                    return;
                case "V":
                    showTicketbyID();
                    break;
                default:
                    System.out.println("Please select from the above options.");
                    break;
            }
        }
    }

    private void displayTicket(Ticket ticket) {
        System.out.println("ID: " + ticket.getId() + " : " + ticket.getCreatedAt() + " | Subject: "
                + ticket.getSubject() + " | Due At:" + ticket.getDueAt() + " | Status:" + ticket.getStatus()
                + " | Priority:" + ticket.getPriority());
    }

    private void displayTicketsMenuAndGetResponse(boolean paginationNeeded, ZendeskPojo response, int pageNumber) {
        if (paginationNeeded) {
            if (null != response.getPreviousPage())
                System.out.println("Press P for previous Page");
            if (null != response.getNextPage())
                System.out.println("Press N for next Page");
        }
        System.out.println("Press V to view a specific ticket");
        System.out.println("Press Q to return to the previous menu");

        return;
    }

    private List<String> getTicketsMenuValidResponses(boolean paginationNeeded, ZendeskPojo response, int pageNumber) {
        List<String> validResponses = new ArrayList<>();
        if (paginationNeeded) {
            if (null != response.getPreviousPage())
                validResponses.add("P");
            if (null != response.getNextPage())
                validResponses.add("N");
        }
        validResponses.add("Q");
        validResponses.add("V");
        return validResponses;
    }

    private String getInput() {
        scanner = new Scanner(System.in);
        return scanner.next();
    }

    private void displayWelcomeMessage() {
        System.out.println("Hello User, \nWelcome to the Ticket Viewer System");
    }

    private String displayMainMenuAndGetResponse() {
        System.out.println("1: View Tickets");
        System.out.println("2: View a Ticket");
        System.out.println("3: quit");
        System.out.print("ENTER THE CHOICE: ");
        return getInput();
    }

    private ZendeskPojo getTickets(int page) {
        try {
            HttpGet request = new HttpGet("https://codingtestrishabh.zendesk.com/api/v2/tickets.json?page=" + page
                    + "&per_page=" + maxPageSize);
            String auth = "rishiorveth@gmail.com" + "/token:RzDVCdSBtia3WbK4KqPFYkeyl1cZhA32ergCFKUN"; // "/token:RzDVCdSBtia3WbK4KqPFYkeyl1cZhA32ergCFKUN";
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));
            String authHeader = "Basic " + new String(encodedAuth);
            request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
                        .fromJson(EntityUtils.toString(response.getEntity()), ZendeskPojo.class);
            else
                System.out.println("Oops! Couldn't connect. Please try again. Status Code: " + statusCode);
            displayMainMenuAndGetResponse();
        } catch (Exception e) {
            throw new APIFailureException();
        }
        return null;
    }

    private Ticket viewTicket(String id) {
        try {
            HttpGet request = new HttpGet("https://codingtestrishabh.zendesk.com/api/v2/tickets/" + id + ".json");
//					
            String auth = "rishiorveth@gmail.com" + "/token:RzDVCdSBtia3WbK4KqPFYkeyl1cZhA32ergCFKUN"; // "/token:RzDVCdSBtia3WbK4KqPFYkeyl1cZhA32ergCFKUN";
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));
            String authHeader = "Basic " + new String(encodedAuth);
            request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
                        .fromJson(EntityUtils.toString(response.getEntity()), TicketResponse.class).getTicket();
            else
                System.out.println("Oops! Couldn't connect. Please try again. Status Code: " + statusCode);
            displayMainMenuAndGetResponse();
        } catch (Exception e) {
            throw new APIFailureException();
        }
        return null;
    }

}
