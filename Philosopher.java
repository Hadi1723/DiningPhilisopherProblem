
import java.util.Random;

public class Philosopher implements Runnable{

    private int id;
    private volatile boolean isFull;
    private ChopStick left;
    private ChopStick right;
    private Random random;
    private int counter;

    public Philosopher(int id, ChopStick left, ChopStick right){
        this.id = id;
        this.left = left;
        this.right = right;
        this.counter = 0;

        this.random = new Random();

    }

    @Override
    public void run() {
        
        try {
            
            while (!isFull){
                think();
                
                if (left.pickUp(this, State.LEFT)){

                    if (right.pickUp(this, State.RIGHT)){
                        eat();
                        right.putDown(this, State.RIGHT);
                    }

                    left.putDown(this, State.LEFT);

                }


            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void think() throws InterruptedException{
        System.out.println(this + " is thinking....");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException{
        System.out.println(this + " is eating....");
        counter += 1;
        Thread.sleep(random.nextInt(1000));
    }

    public boolean getFull(){
        return this.isFull;
    }


    public void setFull(boolean isFull){
        this.isFull = isFull;
    }


    @Override
    public String toString(){
        return "Philosopher " + id;
    }

    public int getCounter(){
        return counter;
    }


}