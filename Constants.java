
public class Constants {

    private static Constants instance = null;
    private int NUMBER_OF_PHILSOPHERS, NUMBER_OF_CHOPSTICKS, SIMULATION_TIME;
    
    private Constants(){
        this.NUMBER_OF_PHILSOPHERS = 5;
        this.NUMBER_OF_CHOPSTICKS = 5;
        this.SIMULATION_TIME = 1000*5;
    }

    public static Constants getInstance(){
        synchronized(Constants.class){
            if (instance == null){
                instance = new Constants();
            }

            return instance;
        }
    }

    public int getPhils(){
        return NUMBER_OF_PHILSOPHERS;
    }
    
    public int getChops(){
        return NUMBER_OF_CHOPSTICKS;
    }

    public int getTime(){
        return SIMULATION_TIME;
    }

    
}