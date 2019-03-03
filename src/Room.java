import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room
{
    private String description;                     // Description of the room.
    private HashMap<String, Room> exits;            // A collection of rooms linked to exits.
    private ItemCollection itemCollection;          // A collection of items found in the room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();

        this.itemCollection = new ItemCollection();
    }

    /**
     * Adds an item to the room.
     * @param item The item to be added to the room.
     */
    public void addItem(Item item)
    {
        this.itemCollection.addItemToCollection(item.getName(), item);
    }

    /**
     * Removes an item from the room.
     * @param itemName The item to be removed from the room.
     */
    public void removeItem(String itemName)
    {
        this.itemCollection.removeItemFromCollection(itemName);
    }

    /**
     * Returns the item if available, otherwise returns null.
     * @param itemName The name of the item to be returned.
     * @return Returns the item if available, else returns null.
     */
    public Item getItemFromCollection(String itemName)
    {
        return this.itemCollection.getDescription(itemName);
    }

    /**
     * Check if the item is edible.
     * Eat item if in collection and edible,
     * otherwise return error message.
     * @param itemName The item name of the item to be eaten.
     */
    public Item eatItem(String itemName)
    {

        Item item = null;

        if ( this.itemCollection.containsItem(itemName) )
        {
            if ( this.itemCollection.getItem(itemName).isEdible() )
            {
                System.out.print(this.itemCollection.getItem(itemName).getName());
                System.out.print(" is edible. ");
                System.out.println("We can now feast!");
                item = this.itemCollection.getItem(itemName);
            }

            else
            {
                System.out.println("The item is not edible.");
            }
        }

        else
        {
            System.out.println("The item you wanted to eat is not in the room.");
        }

        return item;
    }

    /**
     * Check if the item is able to be picked up.
     * Pick up item if in collection and possible to be picked up,
     * otherwise return error message.
     * @param itemName The item name of the item to be picked up.
     */
    public Item pickUpItem(String itemName)
    {
        Item item = null;
        // Checks if the room has any items. Ends method if not.
        //hasItems();

        if ( this.itemCollection.containsItem(itemName) )
        {
            if ( this.itemCollection.getItem(itemName).isCarriable() )
            {
                item = this.itemCollection.getItem(itemName);
            }

            else
            {
                System.out.println("The item is not able to be picked up.");
            }
        }

        else
        {
            System.out.println("The item you wanted to pick up is not in the room.");
        }

        return item;
    }

    /**
     * Checks if the collection has items.
     * Returns true if collection has items, false otherwise.
     * @return true if collection has items, false otherwise.
     */
    private boolean hasItems()
    {
        boolean hasItems = false;

        if (this.itemCollection.hasItems())
        {
            hasItems = true;
        }

        return hasItems;
    }

    /**
     * Sets an exit from the room. The exit is given by a
     * direction, like "east", "west", et.c, and the room
     * that is found through the exit.
     * @param exitDirection the direction or name of the exit, like "east", "west", etc.
     * @param exitRoom the room that the exit should lead to.
     */
    public void setExits(String exitDirection, Room exitRoom)
    {
        this.exits.put(exitDirection, exitRoom);
    }

    /**
     * Returns a long description of this room, of the form.
     * Format are as follows;
     *      You are in the creepy wine cellar.
     *      Exits: north east
     *      Item in room: Wine
     *
     *      -- OR --
     *      You are in the office.
     *      Exits: west
     *      There are no items in the room.
     * @return The description of the room, including exits and items.
     */
    public String getLongDescription()
    {
        String descriptionOfRoom = "You are " + this.description
                + ".\n"
                + getExitsAsString()

                + this.itemCollection.getItemsDescription();

        return descriptionOfRoom;
    }

    /**
     * Return the room found through the exit given by the parameter.
     * If no room exists in that direction, <code>null</code> is returned.
     * @param exitDirection the exit to find a room through, like "east", "est", etc.
     * @return the room found through the given exit. If no room exists through
     *          the exit, <code>null</code> is returned.
     */
    public Room getExit(String exitDirection)
    {
        return this.exits.get(exitDirection);
    }

    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitsAsString()
    {
        String returnString = "Exits:";
        Set<String> keys = this.exits.keySet();

        for ( String exit : keys )
        {
            returnString += " " + exit;
        }

        return returnString;
    }
}