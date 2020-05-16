package serveur;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

public class ChatModel {
	private static final TreeMap<String, ChatModelEvents> clientList = new TreeMap<>();
	
	

	public static synchronized boolean registerUser(String name, HandleClient client) {
		if (!existUserName(name) && !name.equals("")) {
			clientList.put(name, client);
			notifyNewName();
			return true;
		}
		return false;
	}

	public static synchronized void unregisterUser(String name) {
		if (existUserName(name)) {
			clientList.remove(name);
			notifyNewName();
		}
	}

	public static synchronized boolean renameUser(String oldname, String newname, HandleClient client) {
		if (existUserName(oldname)) {
			registerUser(newname, client);
			unregisterUser(oldname);
			return true;
		}
		return false;
	}

	public static synchronized boolean existUserName(String name) {
		return clientList.containsKey(name);
	}

	public static synchronized Set<String> getUserNames() {
		return clientList.keySet();
	}

	public static void sendChatMessage(String from, String msg) {
		clientList.values().forEach(c -> c.chatMessageSent(from, msg));
	}

	public static void sendPrivateChatMessage(String from, String to, String msg) {
		if (existUserName(from) && existUserName(to)) {
			clientList.get(to).privateChatMessageSent(from, to, msg);
		}
	}

	private static void notifyNewName() {
		clientList.values().forEach(ChatModelEvents::userListChanged);
	}

	
	
	/*
	public static synchronized Collection<String> getRooms(){
		
	}
	public static synchronized Collection<String> roomGetUserList(String room){
		
	}*/
	
	public static synchronized boolean roomHasUser(String room, String user) {
		
	return true;	
	}
}

