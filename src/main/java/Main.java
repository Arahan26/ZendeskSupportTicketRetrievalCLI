import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import exception.APIFailureException;
import model.Ticket;
import model.ZendeskPojo;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private int maxTicketsPerRequest = 100;
    private int maxPageSize = 25;

    public static void main(String[] args) {
        new Main().driver();
        // write your code here

    }

    public void driver() {
        displayWelcomeMessage(); //displays a welcome message on the home page of the app.

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
                break;
            case "3":
                break;
            default:
                break;
        }
    }

    private void ticketsViewer() {
        int apiPageNumber = 1;
        int pageNumber = 1;
        int startPage = 1 , endPage;
        int start = 0;
        int end;
        ZendeskPojo response = getTickets(apiPageNumber);
        boolean paginationNeeded = response.getCount() > maxPageSize;
        end = paginationNeeded ? maxPageSize - 1 : response.getCount() - 1;
        endPage = startPage +  (paginationNeeded?response.getCount()/(apiPageNumber*maxPageSize):1);
        IntStream.rangeClosed(start, end).mapToObj(index -> response.getTickets().get(index)).forEach(this::displayTicket);
        String chosenResponse = displayTicketsMenuAndGetResponse(paginationNeeded, response.getCount(), pageNumber);
        if (paginationNeeded) {
            switch (chosenResponse) {
                case "N":
                    break;
                case "P":
                    break;
                case "Q":
                    break;
                default:
                    //Show error message
                    break;
            }
        } else {
            if ("Q".equals(chosenResponse)) {
            }else{
                //Show error message
            }
        }
    }

    private void displayTicket(Ticket ticket) {
        System.out.println(ticket.getId()+ " : "+ ticket.getCreatedAt());
    }

    private String displayTicketsMenuAndGetResponse(boolean paginationNeeded, int totalNumberOfTickets, int pageNumber) {
        if (paginationNeeded) {
            System.out.println("Page Number: " + pageNumber + "/" + (totalNumberOfTickets / maxPageSize + (totalNumberOfTickets % maxPageSize == 0 ? 0 : 1)));
            System.out.println("Press P for previous Page");
            System.out.println("Press N for next Page");
        }
        System.out.println("Press Q to return to the previous menu");
        return getInput();
    }

    private String getInput() {
        System.out.print("ENTER THE CHOICE: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private void displayWelcomeMessage() {
        System.out.println("Hello User, \nWelcome to the Ticket Viewer System");
    }

    private String displayMainMenuAndGetResponse() {
        System.out.println("1: View Tickets");
        System.out.println("2: View a Ticket");
        System.out.println("3: quit");

        return getInput();
    }

    private ZendeskPojo getTickets(int page) {
        try {
            HttpGet request = new HttpGet("https://codingtestrishabh.zendesk.com/api/v2/tickets.json?page=" + page);
            String auth = "rishiorveth@gmail.com" + ":" + "Risharahan26";
            byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(StandardCharsets.ISO_8859_1));
            String authHeader = "Basic " + new String(encodedAuth);
            request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                return new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create().fromJson(EntityUtils.toString(response.getEntity()), ZendeskPojo.class);
            else
                throw new APIFailureException();
        } catch (Exception e) {
            throw new APIFailureException();
        }
    }
}


