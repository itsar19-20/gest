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
		boolean isFloat = false;
		String importoString = "x";
		boolean isImoprto = false;
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
				/*
				if (!isImoprto) {
					switch (importoString) {
					case "x":
						if (questoCarattere.toString().contentEquals("i"))
							importoString = questoCarattere.toString();
						break;
					case "i":
						if (questoCarattere.toString().contentEquals("m"))
							importoString += questoCarattere.toString();
						break;
					case "im":
						if (questoCarattere.toString().contentEquals("p"))
							importoString += questoCarattere.toString();
						break;
					case "imp":
						if (questoCarattere.toString().contentEquals("o"))
							importoString += questoCarattere.toString();
						break;
					case "impo":
						if (questoCarattere.toString().contentEquals("r"))
							importoString += questoCarattere.toString();
						break;
					case "impor":
						if (questoCarattere.toString().contentEquals("t"))
							importoString += questoCarattere.toString();
						break;
					case "import":
						if (questoCarattere.toString().contentEquals("o"))
							importoString += questoCarattere.toString();
						break;
					case "importo":
						isImoprto = true;
						break;
					}
					output = questoCarattere.toString();
				} else {
					if (questoCarattere.toString().contentEquals(".")) {
						output += ",";
						isFloat = true;
					} else {
						if (isFloat) {
							if (questoCarattere.toString().contentEquals("\"")) {
								output += "F" + questoCarattere;
								isFloat = false;
							}
						} else {
							output += questoCarattere.toString();
						}
					}
				}
*/
			}
			
		}
		return output;
	}

}
