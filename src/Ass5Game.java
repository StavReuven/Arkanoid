// 322613720 Stav Reuven

import arkanoid.Game;

/**
 * The Ass5Game class is the main class that starts the Arkanoid game.
 * It initializes the game and runs the game loop.
 */
public class Ass5Game {
    /**
     * The main method creates a new Game object, initializes it, and starts the game loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
