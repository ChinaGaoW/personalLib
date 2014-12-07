package maven1.first2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ComSocket
{

	private static final String EOF = "eeooff";
	private Socket socket;
	private OutputStreamWriter writer;
	private InputStreamReader reader;

	public ComSocket(Socket socket) throws IOException
	{
		this.socket = socket;

		this.reader = new InputStreamReader(socket.getInputStream());

		this.writer = new OutputStreamWriter(socket.getOutputStream());

	}

	public void write(String str) throws IOException
	{
		writer.write(str + EOF);
		writer.flush();

		if (!CommandType.ANSWER.name().equals(str))
		{
			read();
		}
	}

	public String read() throws IOException
	{
		char chars[] = new char[64];
		int len;
		StringBuilder sb = new StringBuilder();
		String temp;
		int index;
		while ((len = reader.read(chars)) != -1)
		{
			temp = new String(chars, 0, len);
			if ((index = temp.indexOf(EOF)) != -1)
			{
				sb.append(temp.substring(0, index));
				break;
			}
			sb.append(new String(chars, 0, len));
		}

		temp = sb.toString();
		if (!CommandType.ANSWER.name().equals(temp))
		{
			write(CommandType.ANSWER.name());
			writer.flush();
		}

		return temp;
	}

	public void close() throws IOException
	{
		writer.close();
		reader.close();
		socket.close();
	}

}
