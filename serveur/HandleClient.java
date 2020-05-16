package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import game.Game;

public class HandleClient implements Runnable, ChatProtocol, ChatModelEvents {

	private Socket s;
	BufferedReader input;
	PrintWriter output;
	Game.Player opponent;

	private ChatOutput cho;
	private ChatInput chi;
	private Game.Player player;
	private String name = "";
	private IChatLogger logger = null;

	private enum ClientState {
		ST_INIT, ST_NORMAL
	};

	private ClientState state = ClientState.ST_INIT;
	private boolean stop = false;

	public HandleClient(Socket s, IChatLogger logger, Game.Player p) throws IOException {
		Game.finish();
		System.out.print("je suis dans le hundle client.............");
		this.s = s;
		this.logger = logger;
		this.player = p;
		input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		output = new PrintWriter(s.getOutputStream(), true);
	}

	public void run() {
		try {
			System.out.print("je suis dans le run du hundle client.............");
			cho = new ChatOutput(s.getOutputStream());
			chi = new ChatInput(s.getInputStream(), this);
			chi.doRun();

		} catch (IOException e) {
			Game.finish();
			System.out.println("Player died:" + e);
		}
	}

	@Override
	public void userListChanged() {
		this.askUList();
	}

	@Override
	public void sendPosition(String position) {
		cho.sendMoveOK();

	}

	@Override
	public void chatMessageSent(String from, String message) {
		if (from != name) {
			cho.sendMessage(from, message);
		}
	}

	@Override
	public void privateChatMessageSent(String from, String to, String message) {
		if (from != name) {
			cho.sendPrivateMessage(from, to, message);
		}
	}

	@Override
	public void shutdownRequested() {

	}

	@Override
	public void sendName(String name) {
		String newName = name;
		if (ChatModel.existUserName(newName)) {
			cho.sendNameBad();
		} else {
			if (state == ClientState.ST_INIT) {
				ChatModel.registerUser(newName, this);
				state = ClientState.ST_NORMAL;
			} else {
				ChatModel.renameUser(name, newName, this);
			}
			this.name = newName;
			cho.sendNameOK();
			logger.clientGotName(s.toString(), name);
		}
	}

	@Override
	public void sendWelcome() {
		logger.GameStarted();
	}

	public void askUList() {
		if (state == ClientState.ST_INIT)
			return;
		cho.sendUserList(ChatModel.getUserNames());
	}

	public void sendAskUserList() {
		askUList();
	}

	public void sendMessage(String user, String msg) {
		if (state == ClientState.ST_INIT)
			return;
		ChatModel.sendChatMessage(name, msg);
		logger.publicChat(name, msg);
	}

	public void sendPrivateMessage(String from, String to, String msg) {
		if (state == ClientState.ST_INIT)
			return;
		ChatModel.sendPrivateChatMessage(from, to, msg);
		logger.privateChat(from, to, msg);
	}

	@Override
	public void sendQuit() {
		// TODO Auto-generated method stub
		ChatModel.unregisterUser(name);
	}

	@Override
	public void roomUserListChanged() {
		// TODO Auto-generated method stub

	}

}
