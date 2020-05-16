package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import game.Game;

public class ChatInput {
	ChatProtocol handler;
	InputStream in;
	Game.Player p;

	public ChatInput(InputStream in, ChatProtocol handler) throws IOException {
		this.in = in;
		this.handler = handler;

	}

	public void doRun() throws IOException, ChatProtocolException {

		String strMsg, strName, strFrom, strTo;
		ArrayList<String> userList;
		try (BufferedReader is = new BufferedReader(new InputStreamReader(in))) {
			handler.sendWelcome();
			System.out.print("seconde partie");
			while (true) {// !stop à la place
				String line = is.readLine();
				switch (line) {
				case "NAME":
					strName = is.readLine();
					handler.sendName(strName);
					break;
				case "MOVE":
					strName = is.readLine();
					int result = Integer.parseInt(strName);
					System.out.println("position CHAT INPUT MOVE " + strName);
					if (Game.legalMove(result, Game.getCurrentPlayer())) {
						handler.sendPosition(strName);
						System.out.println("position" + strName);
						Game.getCurrentPlayer().setScore(result);
					}
					break;
				// serveur ou client
				case "MESSAGE":
					strName = is.readLine();
					strMsg = is.readLine();
					handler.sendMessage(strName, strMsg);
					break;
				// serveur ou client
				case "PRIVATE MESSAGE":
					strFrom = is.readLine();
					strTo = is.readLine();
					strMsg = is.readLine();
					handler.sendPrivateMessage(strFrom, strTo, strMsg);
					break;

				case "ULIST":
					userList = new ArrayList<>();
					String x;
					while (!(x = is.readLine()).equals(".")) {
						userList.add(x);
					}
					handler.sendUserList(userList);
					break;
				case "AULIST":
					handler.sendAskUserList();
					// cas serveur donc pas dans input
				case "NAME OK":
					handler.sendNameOK();
					break;
				case "NAME BAD":
					handler.sendNameBad();
					break;

				case "QUIT":
					handler.sendQuit();
					break;

				default:
					System.out.println(line);

				}
			}
		} catch (IOException e) {
			System.out.println("Player died: " + e);
		}
	}
}
