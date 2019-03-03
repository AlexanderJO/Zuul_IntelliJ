import java.util.Stack;

/**
 * Represents the Player in the Game.
 *
 * @author Alexander J. Overvåg
 * @version v0.1 2019.03.03
 */

public class Player
{
    // The player's name
    private String playerName;

    // The room the player is in
    private Room currentRoom;
    private Stack<Room> roomStack;

    // The item's the player is holding.
    private ItemCollection playerInventory;

    // The current, min and maxiumum weight the player can hold.
    private float currentInventorymWeight;
    private float minInventorymWeight;
    private float maxInventoryWeight;

    // The current, min and max health of the player in a whole number (integer).
    private int currentHealth;
    private int minHealth;
    private int maxHealth;

    /**
     * Constructor for object of class Player
     * @param
     */
    public Player(String playerName, Room startRoom, int playerHealth, float maxItemWeight)
    {
        // Player bio and intial information.
        this.playerName = playerName;
        this.currentRoom = startRoom;
        this.roomStack = new Stack<>();

        // The item carrying capability of the player.
        this.currentInventorymWeight = 0.0F;
        this.minInventorymWeight = 0.0F;
        this.maxInventoryWeight = maxItemWeight;

        // The health options of the player.
        this.currentHealth = playerHealth;
        this.minHealth = 0;
        this.maxHealth = 100;

        // The backpack of the player.
        playerInventory = new ItemCollection();
    }

    // ------------ Method calls -----------
    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command The command word.
     */
    public void goRoom(Command command)
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        }

        else {
            roomStack.push(currentRoom);
            enterRoom(nextRoom);
        }
    }

    /**
     * Enter the new room.
     * @param nextRoom The room to be entered.
     */
    public void enterRoom(Room nextRoom)
    {
        currentRoom = nextRoom;
        printLocationInfo();
    }

    /**
     * Try to go back to the previous room.
     * If no previous room, print an error message.
     */
    public void goBack(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Only 'back' command to be used.");
            return;
        }

        if(roomStack.empty())
        {
            System.out.println("No room to return to!");
        }

        else
        {
            Room previousRoom = roomStack.pop();
            enterRoom(previousRoom);
        }
    }

    /**
     * Try to eat to restore health. If there item is edible, eat it,
     * otherwise print an error message.
     * @param command The name of the item to be eaten.
     */
    public void eatItem(Command command)
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know what to eat ....
            System.out.println("What do you want eat?");
            return;
        }

        String itemName = command.getSecondWord();

        Item itemEaten = this.currentRoom.eatItem(itemName);

        if (itemEaten != null)
        {
            if ( itemEaten.getName() == "Cookie")
            {
                System.out.println("You have eaten the magic cookie!");
                maxInventoryWeight = maxInventoryWeight * 2;
                System.out.println("Inventory increased to " + maxInventoryWeight + " kg");
                this.currentRoom.removeItem(itemEaten.getName());
            }

            else
            {
                this.currentRoom.removeItem(itemEaten.getName());
            }
        }
    }

    /**
     * Checks if the item can be picked up.
     * Pick up item if in collection and can be picked up.
     * @param command The name of the item to be picked up.
     */
    public void pickUpItem(Command command)
    {
        // Checks if the command string contains a second word.
        if(!command.hasSecondWord())
        {
            // No second command word. Do not what to pick up.
            System.out.println("What do you want to pick up?");
            return;
        }

        String itemName = command.getSecondWord();
        Item itemPicked = this.currentRoom.pickUpItem(itemName);

        if ( itemPicked != null )
        {
            this.currentRoom.removeItem(itemPicked.getName());
            addItemToInventory(itemPicked);
        }
    }

    /**
     * Try to drop the item.
     * If the item is not carriable, give error message.
     * @param command The name of the item to be dropped.
     */
    public void dropItem(Command command)
    {
        // Checks if the command string contains a second word.
        if(!command.hasSecondWord())
        {
            // No second command word. Do not what to drop.
            System.out.println("What do you want to drop?");
            return;
        }

        String itemName = command.getSecondWord();
    }

    /**
     * Adds an item to the room.
     * @param item The item to be added to the room.
     */
    public void addItemToInventory(Item item)
    {
        if ( ( getCurrentInventorymWeight() + item.getWeight() ) >= maxInventoryWeight)
        {
            System.out.println("Item weighs to much.");
        }
        else
        {
            this.playerInventory.addItemToCollection(item.getName(), item);
            System.out.print(item.getName());
            System.out.print(" is carriable and has been added to your inventory.");
        }
    }

    // ------------ Getter Methods ---------

    /**
     * Returns the player name.
     * @return the player name.
     */
    public String getPlayerName()
    {
        return this.playerName;
    }

    /**
     * Returns the current total item weight on the player.
     * @return the current total item weight on the player.
     */
    private float getCurrentInventorymWeight()
    {
        return this.currentInventorymWeight = this.playerInventory.getTotalWeight();
    }

    /**
     * Returns the current health of the player.
     * @return the current health of the player.
     */
    public int getPlayerHealth()
    {
        return this.currentHealth;
    }

    /**
     * Returns the current player room.
     * @return the current player room.
     */
    public Room getCurrentRoom()
    {
        return this.currentRoom;
    }

    // ----------- Print methods ----------

    /**
     * Prints the information of your current location.
     */
    public void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Looks around the room and lists exits for current room.
     */
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Prints the current inventory weight on player.
     */
    public void getInventoryWeight()
    {
        System.out.println(getCurrentInventorymWeight());
    }

    /**
     * Prints items carried by the player.
     */
    public void printPlayerInventory()
    {
        System.out.println(this.playerInventory.getCollectionWeight());
    }
}
