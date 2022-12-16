package personal;

import personal.GameEngine;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GameEngine game = new GameEngine();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.update();
            }
        });
        thread.run();
    }
}
