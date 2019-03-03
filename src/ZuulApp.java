/**
 * The starting point of the game.
 */

public class ZuulApp
{
    // Parameteret "arg" kan også være "args", "jens", "osv".
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}