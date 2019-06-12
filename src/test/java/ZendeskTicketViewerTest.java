import exception.APIFailureException;
import junit.framework.Assert;
import model.Ticket;
import model.ZendeskPojo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ZendeskTicketViewerTest {

    ZendeskTicketViewer testZendeskTicketViewer = new ZendeskTicketViewer();

    @Test
    public void ticketViewerReturnsTicketsOrNull()
    {
        Assert.assertNotNull("If the api is called",testZendeskTicketViewer.getTickets(1));
    }

    @Test
    public void viewTicketReturnsTicket()
    {
        String id = "1"; //using 1 because first ticket will always be the one that was made first.
        String expectedID = "381581354133";
        Assert.assertEquals("Using Known ID", testZendeskTicketViewer.viewTicket("1").getAssigneeId(), expectedID);
    }

    @Test
    public void retrieveUsernameReturnsUser()
    {
        String id = "381581354133"; //using my own ID
        String expectedName = "Rishabh Sharma";

        Assert.assertEquals("Using Rishabh Sharma's User ID", expectedName, testZendeskTicketViewer.retrieveUsername(id).getName());

    }

    @Test
    public void getTicketsMenuValidResponsesReturnsValidOptions()
    {
        int pageOne = 1;
        int pageTwo = 2;
        int pageFive = 5;
        ZendeskPojo ticketsPageOne = testZendeskTicketViewer.getTickets(pageOne);
        ZendeskPojo ticketsPageTwo = testZendeskTicketViewer.getTickets(pageTwo);
        ZendeskPojo ticketsPageFive = testZendeskTicketViewer.getTickets(pageFive);

        List<String> validResponses = new ArrayList<>();
        validResponses.add("P");
        validResponses.add("N");
        validResponses.add("V");
        validResponses.add("Q");

        List<String> validResponsesIfFirstPage = new ArrayList<>();
        validResponsesIfFirstPage.add("N");
        validResponsesIfFirstPage.add("V");
        validResponsesIfFirstPage.add("Q");

        List<String> validResponsesIfLastPage = new ArrayList<>();
        validResponsesIfLastPage.add("P");
        validResponsesIfLastPage.add("V");
        validResponsesIfLastPage.add("Q");

        Assert.assertEquals(testZendeskTicketViewer.getTicketsMenuValidResponses(true,ticketsPageTwo,2),validResponses);
        Assert.assertEquals(testZendeskTicketViewer.getTicketsMenuValidResponses(true,ticketsPageOne,1),validResponsesIfFirstPage);
        Assert.assertEquals(validResponsesIfLastPage, testZendeskTicketViewer.getTicketsMenuValidResponses(true,ticketsPageFive,5));

    }


    @Test (expected = NullPointerException.class)
    public void getTicketsThrowsApiFailureException () //throws APIFailureException
    {
        Throwable ex = null;
        //APIFailureException exception = assertThrows(APIFailureException.class,)
        try {
            ZendeskPojo response  = testZendeskTicketViewer.getTickets(1);
            //response.getTickets().forEach(ticket -> testZendeskTicketViewer.displayTicket(ticket));
        }
        catch (Throwable e) {
            ex = e;
        }
        //assertTrue(ex instanceof NullPointerException);

        assertEquals(java.lang.NullPointerException.class, ex.getClass());
    }


    /*@Test
    public void displayTicketReturnsValidTicket()
    {
        Ticket ticket;
        ticket = testZendeskTicketViewer.viewTicket("1");
        String substringDisplayTicket = testZendeskTicketViewer.displayTicket(ticket);

        assertEquals("ID: 381581354133", testZendeskTicketViewer.displayTicket(ticket));


    }*/



}
