package commonUtils;

public class CommonUtils {
	public static boolean isNotNullOrEmpty(String input) {
		if(input == null || input.equals(""))
			return false;
		return true;
	}
}
