
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Kevin
 */
public class Player {
    private LinkedList<String> currentInventory;
    private String name;
    private String lastWhisperName;
    private int currentRoom;
    private Direction currentDirection;
    private PrintWriter replyWriter = null;
    private DataOutputStream outputWriter = null;
    // add a money field to track player money
    private double money;

    public Player(String name) {
        this.currentRoom = 1;
        this.currentDirection = Direction.NORTH;
        this.name = name;
        this.currentInventory = new LinkedList<>();
        // set a default amount of money for each player
        this.money = 20.0;
    }
    
    public void turnLeft() {
        switch(this.currentDirection.toString()) {
            case "North":
                this.currentDirection = Direction.WEST;
                break;
            case "South":
                this.currentDirection = Direction.EAST;
                break;
            case "East":
                this.currentDirection = Direction.NORTH;
                break;
            case "West":
                this.currentDirection = Direction.SOUTH;
                break;                
        }
    }
    
    public void turnRight() {
        switch(this.currentDirection.toString()) {
            case "North":
                this.currentDirection = Direction.EAST;
                break;
            case "South":
                this.currentDirection = Direction.WEST;
                break;
            case "East":
                this.currentDirection = Direction.SOUTH;
                break;
            case "West":
                this.currentDirection = Direction.NORTH;
                break;                
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastWhisperName(String name) {
        this.lastWhisperName = name;
    }

    public String getLastWhisperName() {
        return this.lastWhisperName;
    }

    public LinkedList<String> getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(LinkedList<String> currentInventory) {
        this.currentInventory = currentInventory;
    }
    
    public void addObjectToInventory(String object) {
        this.currentInventory.add(object);
    }

    /**
     * Allows an an object to be taken away from player's inventory.
     * @return Message showing success.
     */  
    public String removeRandomItem()  {
        if (this.currentInventory.isEmpty()){
            return "You have no items in your inventory.";
        }
        Random randInt = new Random();
        int randItem = randInt.nextInt(this.currentInventory.size());
        String targetItem = this.currentInventory.remove(randItem);
        setCurrentInventory(this.currentInventory);
        return targetItem + " was removed from your inventory.";
    }
    
    public void setReplyWriter(PrintWriter writer) {
        this.replyWriter = writer;
    }
    
    public PrintWriter getReplyWriter() {
        return this.replyWriter;
    }
    
    public void setOutputWriter(DataOutputStream writer) {
        this.outputWriter = writer;
    }
    
    public DataOutputStream getOutputWriter() {
        return this.outputWriter;
    }
    
    public int getCurrentRoom() {
        return this.currentRoom;
    }
    
    public void setCurrentRoom(int room) {
        this.currentRoom = room;
    }
    
    public String getCurrentDirection() {
        return this.currentDirection.name();
    }
    
    public Direction getDirection() {
        return this.currentDirection;
    }

    public void setDirection(Direction direction){
	    this.currentDirection = direction;
    }
  
    public double getMoney() {
      return this.money;
    }
    
    public String viewMoney() {
      return this.name + ", you have " + this.money + " dollars.";
    }
    
    public String viewInventory() {
        String result = "";
        if(this.currentInventory.isEmpty()) {
            return "nothing.";
        }
        for(String obj : this.currentInventory) {
            result += " " + obj;
        }
        result += ".";
        return result;
    }

    @Override
    public String toString() {
        return "Player " + this.name + ": " + currentDirection.toString();
    }
}