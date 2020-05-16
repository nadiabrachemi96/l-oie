package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.logging.*;

import game.Game;

public class ServerCore extends Thread {
	private int port;
	ServerSocket ss;
	private IChatLogger logger = null;

	public ServerCore(int port) throws IOException {
		this.port = port;
		logger = new TextChatLogger();
		logger.systemMessage("Server started...");
		this.start();
	}

	public void run() {
		try (ServerSocket ss = new ServerSocket(port)) {

			try {
				while (true) {
					try {
						Game g = new Game();
						Game.Player playerX = g.new Player(ss.accept(), "player 1");
						Game.Player playerO = g.new Player(ss.accept(), "player 2");
						Game.setCurrentPlayer(playerX);
						playerX.setOpponent(playerO);
						playerO.setOpponent(playerX);
						Game.currentPlayer = playerX;

						// Afficher sur la sortie standard que le client est connecté
						logger.clientConnected(playerX.getSocket().toString());
						logger.clientConnected(playerO.getSocket().toString());

						new Thread(new HandleClient(playerX.getSocket(), logger, playerX)).start();
						new Thread(new HandleClient(playerO.getSocket(), logger, playerO)).start();

					} catch (SocketTimeoutException ex) {

						System.out.println("nouveaux joueur:" + ex.toString());
					}
				}
			} finally {
				ss.close();
			}
		} catch (IOException e) {
			System.out.println("Could not bind port " + port);
			Logger.getLogger(ServerCore.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
