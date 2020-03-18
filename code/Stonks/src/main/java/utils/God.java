package utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.Nullable;

import models.Jesu;

public class God {

	public static void seesEverythings(HttpServletRequest request, HttpServletResponse response, @Nullable String responceString) {

		try {
			Date date = new Date();
			String requestClass = String.valueOf(request.getClass());
			String requestCookies = String.valueOf(request.getCookies());
			String attributeName = String.valueOf(request.getAttributeNames());
			Integer number = null;
			if (responceString != null) number = responceString.length();
			
			Jesu jesu = new Jesu(
					date, 
					request.getAuthType(), 
					request.getCharacterEncoding(), 
					request.getContentType(), 
					request.getContextPath(), 
					request.getLocalAddr(), 
					request.getLocalName(), 
					request.getMethod(), 
					request.getPathInfo(), 
					request.getPathTranslated(), 
					request.getProtocol(), 
					request.getQueryString(), 
					request.getRemoteAddr(), 
					request.getRemoteHost(), 
					request.getRemoteUser(), 
					request.getRequestedSessionId(), 
					request.getRequestURI(), 
					request.getScheme(), 
					request.getServerName(), 
					request.getServletPath(), 
					request.toString(), 
					Integer.valueOf(request.getContentLength()), 
					Integer.valueOf(request.getLocalPort()), 
					Integer.valueOf(request.getRemotePort()), 
					Integer.valueOf(request.getServerPort()), 
					Integer.valueOf(request.hashCode()), 
					attributeName, 
					requestClass, 
					requestCookies, 
					Integer.valueOf(response.getBufferSize()), 
					response.getCharacterEncoding(), 
					response.getContentType(), 
					Integer.valueOf(response.getStatus()), 
					Integer.valueOf(response.hashCode()), 
					response.toString(), 
					number);
			
			// System.out.println(jesu.toString());
			System.out.println("# " + jesu.getRequestToString() + "\n# Response [ " + jesu.getResponseToString() + " ]");
			DataBase.create(jesu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
