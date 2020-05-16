package serveur;

import java.util.Collection;

public interface ChatProtocol {
	default void sendName(String name){}
	default void sendPosition(String position){}
	default void sendNameOK(){}
	default void sendMoveOK(){}
	default void sendNameBad() {}
	default void sendMessage(String user, String msg){}
	default void sendAskUserList() {}
	default void sendUserList(Collection<String> ulist) {}
	default void sendPrivateMessage(String from, String to, String msg){}
	default void sendQuit(){}
	default void sendWelcome() {}
	default void setVlisePosition(String string) {};
}
