# ZendeskSupportTicketRetrievalCLI

Made for Zendesk Intern Coding Challenge 2019

Project to display tickets from an organisation's Zendesk account.


<b>Requirements</b>

Java

<b>Running the program</b>

Method 1:

1. Install IntelliJ IDEA from https://www.jetbrains.com/idea/ or Any other Java IDE
2. Clone or Download this project from the repository.
3. Open the project using IntelliJ IDEA.
4. Navigate to class `ZendeskTicketViewer`
5. Open or Run the main method.

Method 2:

1. Install java jdk/jre.
2. Download the project from this repository.
3. On Terminal change directory to `%ZendeskSupportTicketRetrievalCLI/out/artifacts/TicketViewerZendesk_jar`
4. enter the command `java -jar TicketViewerZendesk.jar`



<b>Assumptions and Decisions</b>

1. In displayTicket() username isn't shown as it adds another API call and slows down the process of loading ticket. This can be changed by replacing the code with the code given in comments of that method.
2. Used http://www.jsonschema2pojo.org/ to make model of JSON data.
3. Data displayed in list:
	1. ID
	2. Created At
	3. Subject
	4. Due At
	5. Status
	6. Assigned to (ID)
	7. Priority
4. Data displayed for single ticket
	1. Ticket ID
	2. Assigned To (name)
	3. Subject
	4. Description
	5. Status
	6. Due At
5. Pagination is achieved by using the parameters to set maximum tickets per page. The program checks if the count of the tickets and decides how many pages are based on that.
To move to the next or previous page the parameters "next_page" and "previous_page" are checked. It appropriately removes or adds the options to go forward or backward.

