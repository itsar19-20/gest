package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static <T> T deserializesString(final TypeReference<T> type, final String jsonPacket) {
		T data = null;
		try {
			data = new ObjectMapper().readValue(jsonPacket, type);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
	
}
