package bwt.tools.ticketgenerator;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestMain {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		BWTTicketGenerator tt = BWTTicketGenerator.getInstance();
		tt.createTicket("39763620316","119-13-16-Etkinlik Adı|119-263-30-27/02/2016|119-274-248-22:30|119-174-169-A|119-381-166-A5");
	}

}
