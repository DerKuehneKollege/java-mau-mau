package atomkraftzwerg.javamaumau;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        game.addHumanPlayer("Heinrich");
        game.addPlayer("Eugen");
        game.addPlayer("Günther");

        game.start();
    }

}
