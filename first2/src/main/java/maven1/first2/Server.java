package maven1.first2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class Server
{
	private ComSocket[] clients = new ComSocket[3];

	public static void main(String[] args) throws IOException
	{
		Server app = new Server();
		app.begin();
	}

	private void begin() throws IOException
	{
		int port = 8899;
		ServerSocket server = new ServerSocket(port);

		int count = 0;
		while (count < 1)
		{
			System.out.println("等待其它玩家连接...");
			Socket socket = server.accept();

			clients[count++] = new ComSocket(socket);
			System.out.println("连接了一个玩家...");
		}

		System.out.println("所有玩家都已连接，开始...");

		count = 1000;
		while (count-- > 0)
		{
			System.out.println("请输入指令，格式：[0|1|2];[INPUT|OUTPUT|DISPLAY];...");
			String command = Util.waitPlayerInput();

			String[] inputs = command.split(";");

			int index = 0;
			try
			{
				index = Integer.parseInt(inputs[0]);
			} catch (Exception e)
			{
				System.out.println("用户序号输入错误，请重新输入");
				continue;
			}

			if (index < 0 || index > 2)
			{
				System.out.println("用户序号输入错误，请重新输入");
				continue;
			}

			ComSocket toUser = clients[index];

			CommandType type = CommandType.getType(inputs[1]);

			switch (type)
			{
			case INPUT:
				toUser.write("INPUT;请输入：");

				String in = toUser.read();
				System.out.println("输入了：" + in);
				break;
			case OUTPUT:
				toUser.write("OUTPUT;" + inputs[2]);
				break;
			case DISPLAY:
				toUser.write("DISPLAY");
				break;
			default:
				System.out.println("输入了错误，请重新输入");
			}
		}

		server.close();

	}
}
