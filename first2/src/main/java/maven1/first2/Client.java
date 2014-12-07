package maven1.first2;

import java.io.IOException;
import java.net.Socket;

public class Client
{
	ComSocket client;

	public static void main(String args[]) throws Exception
	{
		Client app = new Client();
		app.begin();

	}

	/**
	 * @throws Exception
	 */
	private void begin() throws Exception
	{
		String host = "127.0.0.1";
		int port = 8899;

		Socket socket = new Socket(host, port);

		client = new ComSocket(socket);

		String[] inputs;

		int count = 1000;
		while (count-- > 0)
		{
			inputs = waitingServerCommand();
			CommandType type = CommandType.getType(inputs[0]);

			switch (type)
			{
			case INPUT:
				System.out.println(inputs[1]);
				String userInput = Util.waitPlayerInput();

				client.write(userInput);
				break;
			case OUTPUT:
				System.out.println(inputs[1]);
				break;
			case DISPLAY:
				System.out.println("need display...");
				break;
			default:
				break;
			}
		}
	}

	private String[] waitingServerCommand() throws IOException
	{
		String inputStr = client.read();
		String[] inputs = inputStr.split(";");
		return inputs;
	}

}