package serveur;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import game.Game;

public class ChatOutput implements ChatProtocol {
	// valider la position

	PrintWriter os;

	public ChatOutput(OutputStream out) throws IOException {
		this.os = new PrintWriter(out, true);
	}

	@Override
	public void setVlisePosition(String string) {
		os.println(string);
		os.println(Game.hasWinner() ? "VICTORY" : Game.boardFilledUp() ? "TIE" : "");
	}

	@Override
	public synchronized void sendName(String name) {
		os.println("NAME");
		os.println(name);
	}

	@Override
	public void sendPosition(String position) {
		os.println("MOVE");
		os.println(position);

	}

	@Override
	public void sendWelcome() {
		os.println("WELCOME FOR THE PLAYERS GAME CAN START");
	}

	@Override
	public synchronized void sendMessage(String user, String msg) {
		os.println("MESSAGE");
		os.println(user);
		os.println(msg);
	}

	@Override
	public synchronized void sendPrivateMessage(String from, String to, String msg) {
		os.println("PRIVATE MESSAGE");
		os.println(from);
		os.println(to);
		os.println(msg);
	}

	@Override
	public synchronized void sendUserList(Collection<String> ulist) {
		os.println("ULIST");
		ulist.forEach(os::println);
		os.println(".");
	}

	@Override
	public synchronized void sendAskUserList() {
		os.println("AULIST");
	}

	@Override
	public synchronized void sendQuit() {
		os.println("QUIT");
	}

	@Override
	public synchronized void sendNameOK() {
		os.println("NAME OK");
	}

	@Override
	public synchronized void sendNameBad() {
		os.println("NAME BAD");

	}

	@Override
	public void sendMoveOK() {
		os.println("MOVE OK");
		os.println(Game.hasWinner() ? "VICTORY" : Game.boardFilledUp() ? "TIE" : "");

	}

}
