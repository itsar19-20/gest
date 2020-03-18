package utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class God {

	public static void seesEverythings(HttpServletRequest request) {
		
		Date date = new Date();
		pl(request.getDateHeader(null));
		
	}
	
	private static void pl(long dateHeader) {
		// TODO Auto-generated method stub
		
	}

	private void pl(String string) { System.out.println(string); }

}
