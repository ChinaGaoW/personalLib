package maven1.first2;

public enum CommandType
{
	OUTPUT, INPUT, DISPLAY, ANSWER;

	public static CommandType getType(String name)
	{
		for (CommandType curType : CommandType.values())
		{
			if (curType.name().equalsIgnoreCase(name))
			{
				return curType;
			}
		}
		return null;
	}

	public static void main(String[] args)
	{
		System.out.println(getType("display"));
	}
}
