
/*
 * 
 * in circle, for last phil, we need modulo
 * 
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        Philosopher[] phils = null;
        ChopStick[] sticks = null;

        try {
            phils = new Philosopher[Constants.getInstance().getPhils()];
            sticks = new ChopStick[Constants.getInstance().getChops()];

            for (int i = 0; i < sticks.length; i++) {
                sticks[i] = new ChopStick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.getInstance().getPhils());

            for (int i = 0; i < Constants.getInstance().getPhils(); i++) {
                phils[i] = new Philosopher(i, sticks[i], sticks[(i+1)%Constants.getInstance().getPhils()]);
                executorService.execute(phils[i]);
            }

            Thread.sleep(Constants.getInstance().getTime());

            executorService.shutdown();

            for (int i = 0; i < phils.length; i++) {
                phils[i].setFull(true);
            }

        } catch (Exception e) {
            executorService.shutdownNow();

            while (!executorService.isTerminated()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            for (Philosopher phil : phils){
                System.out.println(phil + " eat #" + phil.getCounter() + " times");
            }
        }
    }
}