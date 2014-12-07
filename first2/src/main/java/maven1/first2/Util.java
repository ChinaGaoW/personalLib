package maven1.first2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util
{
	public static String waitPlayerInput()
	{
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			return strin.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
