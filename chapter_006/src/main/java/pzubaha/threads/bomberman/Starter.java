package pzubaha.threads.bomberman;

/**
 * Chapter_006. Multithreading.
 * 6. Control task. 2. Bomberman.
 * Contains solution of task 1108.
 * Starter class for the Bomberman game.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Starter {
    /**
     * Initiation of Game.
     * @param unitAmount - amount of units(Threads)
     */
    public void initGame(int unitAmount, int width, int heigth) {
        ThreadGroup group = new ThreadGroup("group");
        Field field = new Field(width, heigth);
//        Hero unit = new Hero(group, field, 0, 0, "H");
//        unit.start();
        for (int i = 0; i < unitAmount; i++) {
            new Unit(group, field, i, i, 500, "M" + i).start();
        }
        int counter = 0;
        while (counter < 10) {
            counter++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(field.toString());
        }
        group.interrupt();
    }

    /**
     * Main.
     * Start game 5x5 field with 5 units.
     * @param args args
     */
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.initGame(5, 5, 5);
    }
}
