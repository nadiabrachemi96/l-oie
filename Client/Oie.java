package Client;


import game.Game;
import view.Gui;





public class Oie {

  
    
    //main
    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Game.finish();
            Gui client = new Gui(serverAddress);
            client.play();
            if (!client.wantsToPlayAgain()) {
                break;
            }
        }
    }
}