package utils;

public class JsonUtil {

	public static String getJsonFromAnObjectOfObjects(String input) {
		String output = null;
		boolean apertaQuadra = false;
		for (int i = 0; i < input.length() - 1; i++) {
			String quadra = "[";
			Character questoCarattere = input.charAt(i);
			if (!apertaQuadra) {
				if (quadra.contentEquals(questoCarattere.toString())) {
					apertaQuadra = true;
					output = questoCarattere.toString();
				}
			} else {
				output += questoCarattere.toString();
			}
		}
		return output;
	}
	
	public static String getJsonFromAnObject(String input) {
		String output = null;
		boolean apertaGraffa = false;
		for (int i = 1; i < input.length() - 1; i++) {
			String graffa = "{";
			Character questoCarattere = input.charAt(i);
			if (!apertaGraffa) {
				if (graffa.contentEquals(questoCarattere.toString())) {
					apertaGraffa = true;
					output = questoCarattere.toString();
				}
			} else {
				output += questoCarattere.toString();
			}
		}
		return output;
	}
	
}
