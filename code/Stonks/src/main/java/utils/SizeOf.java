package utils;

public class SizeOf {
	
	public static long getSizeInByte(String s) {
		long _return = s.length() * 2;
		return _return;
	}
	
	public static long getSizeInMegabyte(String s) {
		long _return = (s.length() * 2) / 1000000;
		return _return;
	}

}
