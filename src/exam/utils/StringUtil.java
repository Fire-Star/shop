package exam.utils;

public class StringUtil {
	public static String toString(String str){
		
		return str.replace("\"", "\\\\\"");
	}
}
