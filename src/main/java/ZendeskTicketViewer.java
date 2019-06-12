import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import model.*;
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

public class ZendeskTicketViewer {

    private int maxPageSize = 25;
    private Scanner scanner;
    String testString = "";

    public static void main(String[] args) {
        new ZendeskTicketViewer().driver();

    }

    public void driver() {
        displayWelcomeMessage(); // displays a welcome message on the home page of the app.

        String optionChosen = ""; //user input string

        while (!optionChosen.equals("3")) {     //keeps the program running till user inputs 3
            optionChosen = displayMainMenuAndGetResponse();
            mainMenuDriver(optionChosen);
        }
    }

    private void mainMenuDriver(String option) {
        switch (option) {
            case "1":
                ticketsViewer();    //calls the method to load and display tickets
                break;
            case "2":
                showTicketByID();       //calls the method to display details of a single ticket
                break;
            case "3":       //ends the program
                break;
            default:
                System.out.println("Please select from the options.");
                break;
        }
    }

    /**
     * creates a ticket viewer with pages if there are more than 25 tickets returned
     * Depending on how many pages (tickets in multiples of 25) are there, it shows the option to go the next or previous page.
     * Can also call the method to show a specific ticket's details.
     */
    public void ticketsViewer() {
        int pageNumber = 1; //expects that at least 1 with 1 ticket is returned
        String chosenResponse;

        while (true) {
            ZendeskPojo response = getTickets(pageNumber);
            if (response != null) {


                response.getTickets().forEach(ticket -> displayTicket(ticket));
                System.out.println("\nPage Number: " + pageNumber + "/"
                        + (response.getCount() / maxPageSize + (response.getCount() % maxPageSize == 0 ? 0 : 1))); //displays current page and the total number of pages.

                boolean paginationNeeded = response.getCount() > maxPageSize; //if the count is greater than 25 pages, it's set to true.
                displayTicketsMenuAndGetResponse(paginationNeeded, response, pageNumber);
                while (true) {
                    System.out.print("ENTER THE CHOICE: ");
                    chosenResponse = getInput();
                    List<String> validResponses = getTicketsMenuValidResponses(paginationNeeded, response, pageNumber); //valid response for this will change according to the presence of more pages or not.
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
                        showTicketByID();
                        break;
                    default:
                        System.out.println("Please select from the above options.");
                        break;
                }
            } else
                return;
        }
    }

    /**
     * display's a single ticket's details.
     * (Optional) Retrieve's the name of the person the ticket is assigned to by retrieveUsername() method which in turn uses Zendesk's Users API
     *
     * @param ticket
     */
    public void displayTicket(Ticket ticket) {
        testString = ticket.getAssigneeId();
        System.out.println("ID: " + ticket.getId()
                + " | Created At " + ticket.getCreatedAt()
                + " | Subject: " + ticket.getSubject()
                + " | Due At:" + ticket.getDueAt()
                + " | Status:" + ticket.getStatus()
                + " | Assigned to:" + ticket.getAssigneeId()// Replace with the following code if Agent Name is needed. It will slow down the loading of data though as an additional call to Users API will also be made
                // {+ " | Assignee" + retrieveUsername(ticket.getAssigneeId()).getName()}
                + " | Priority:" + ticket.getPriority());
    }


    /**
     * Asks for ticket ID whose details are to be displayed. If a valid ticket ID is given it displays the ticket details
     */
    private void showTicketByID() {
        System.out.print("Enter the ticket ID: ");
        String id = getInput();
        Ticket response = viewTicket(id);

        if (response != null) {
            displayTicketDetails(response);
            System.out.println("Press any key and enter to go back");
            String backString = getInput();
        } else
            return;

    }

    /**
     * called by showTicketByID()  and displays details of a single ticket
     *
     * @param ticket the ticket id whose details are shown.
     */
    private void displayTicketDetails(Ticket ticket) {
        System.out.println("Ticket ID: " + ticket.getId()
                + "\tAssigned to: " + retrieveUsername(ticket.getAssigneeId()).getName()
                + "\nSubject\t " + ticket.getSubject()
                + "\n\nDescription:\t" + ticket.getDescription()
                + "\n\nStatus:\t\t\t" + ticket.getStatus()
                + "\nDue At:" + ticket.getDueAt());
    }


    /**
     * displays the ticket menu. depending on the number of tickets, if more pages are there it adds or removes the option to navigate to them.
     *
     * @param paginationNeeded boolean. checks if there are more than 25 tickets. if yes, adds pages to display them.
     * @param response         user input
     * @param pageNumber       the page you're on.
     */
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

    /**
     * dynamically decides if Next or Previous pages are available. If yes, shows the menu option for it.
     *
     * @param paginationNeeded
     * @param response         has a property called next page. gives the link to that. I'm not using the link. Just changing the page number through code itself.
     * @param pageNumber
     * @return
     */
    public List<String> getTicketsMenuValidResponses(boolean paginationNeeded, ZendeskPojo response, int pageNumber) {
        List<String> validResponses = new ArrayList<>();
        if (paginationNeeded) {
            if (null != response.getPreviousPage())
                validResponses.add("P");
            if (null != response.getNextPage())
                validResponses.add("N");
        }
        validResponses.add("V");
        validResponses.add("Q");

        return validResponses;
    }

    /**
     * scans and returns user input
     *
     * @return
     */
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

    /**
     * accesses the tickets API and checks if the connection is made and valid. Returns the data in gson format.
     *
     * @param page page number from which tickets are retrieved from.
     * @return
     */
    public ZendeskPojo getTickets(int page) {
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
            //throw new APIFailureException();

        } catch (Exception e) {
            throw new APIFailureException();
        }
        return null;
    }

    /**
     * accesses the ticket API and returns a single ticket's details in gson format.
     *
     * @param id id of the ticket to be shown.
     * @return
     */
    public Ticket viewTicket(String id) {
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
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND)
                    System.out.println("Oops! Invalid ID. Please try again.");
                else
                System.out.println("Oops! Couldn't connect. Please try again. Status Code: " + statusCode);
        } catch (Exception e) {
            throw new APIFailureException();
        }
        return null;
    }

    /**
     * accesses the Users API to retrieve details of a specific user.
     *
     * @param id ID of the user whose details are needed.
     * @return
     */
    public User retrieveUsername(String id) {
        try {
            HttpGet request = new HttpGet("https://codingtestrishabh.zendesk.com/api/v2/users/" + id + ".json");
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
                        .fromJson(EntityUtils.toString(response.getEntity()), Users.class).getUser();
            else
                System.out.println("Oops! Couldn't connect. Please try again. Status Code: " + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIFailureException();
        }
        return null;
    }


}
