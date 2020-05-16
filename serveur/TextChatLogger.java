package serveur;

public class TextChatLogger implements IChatLogger {

	@Override
	public void PositionChanger(String position) {
		System.out.println("vous etes � la position : " + position);

	}

	@Override
	public void clientConnected(String ip) {
		System.out.println(ip + " est connecté");
	}

	@Override
	public void clientDisconnected(String ip, String name) {
		System.out.println(ip + " s'est déconnecté");
	}

	@Override
	public void clientGotName(String ip, String name) {
		System.out.println("IP :" + ip + " name : " + name);
	}

	@Override
	public void clientGotCommand(String name, int command) {
		System.out.println("name : " + name + ", commande " + command);
	}

	@Override
	public void publicChat(String from, String msg) {
		System.out.println(from + " : " + msg);
	}

	@Override
	public void privateChat(String from, String to, String msg) {
		System.out.println("PRIVATE ( " + to + ") " + from + " : " + msg);
	}

	@Override
	public void systemMessage(String msg) {
		System.out.println("SYSTEME : " + msg);
	}

	@Override
	public void salonAjoute(String salon) {
		// TODO Auto-generated method stub
		System.out.println("Salon : " + salon);
	}

	@Override
	public void GameStarted() {
		System.out.println("le jeux commence");
	}

}
