
/*
 * lock needed becasue phils may use same chopstick
 * 
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick{

    private int id;
    private Lock lock;

    public ChopStick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher phil, State state) throws InterruptedException{

        // trying to acquire the chopstick (getting the lock)
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)){
            System.out.println(phil+ "picked up "+ state.toString()+ " " + this);
            return true;
        }
        
        return false;
    }

    public void putDown(Philosopher phil, State state){
        lock.unlock();
        System.out.println(phil +" put down " + state.toString() + this);

    }


    @Override
    public String toString(){
        return "ChopStick " + id;
    }


}