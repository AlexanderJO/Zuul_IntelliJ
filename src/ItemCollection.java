import java.util.HashMap;

/**
 * A list of items.
 *
 * @author Alexander J. Overvåg
 * @version v0.1 2019.02.27
 */
public class ItemCollection
{
    // A collection of items picked up by the player.
    private HashMap<String, Item> itemCollection;

    /**
     * Create a new item list.
     */
    public ItemCollection()
    {
        this.itemCollection = new HashMap<>();
    }

    /**
     * Adds an item to the collection.
     * @param itemName the name of the item to be added to the collection.
     * @param item the object Item to be added to the collection.
     */
    public void addItemToCollection(String itemName, Item item)
    {
        this.itemCollection.put(itemName, item);
    }

    /**
     * Remove an item of name.
     * @oaram itemName The name of the item to be removed.
     */
    public Item removeItemFromCollection(String itemName)
    {
        return itemCollection.remove(itemName);
    }

    /**
     * Returns true if the collection has items, false otherwise.
     * @return true if the collection has items, false otherwise.
     */
    public boolean hasItems()
    {
        boolean hasItems = false;

        if (!this.itemCollection.isEmpty())
        {
            hasItems = true;
        }
        else
        {
            hasItems = false;
        }

        return hasItems;
    }

    /**
     * Returns true if the collection contains item with itemName,
     * otherwise returns false.
     * @param itemName The item to be checked.
     * @return true if the collection contains item with itemName,
     * otherwise returns false.
     */
    public boolean containsItem(String itemName)
    {
        return this.itemCollection.containsKey(itemName);
    }

    /**
     * Returns the item if available, otherwise returns null.
     * @param itemName The name of the item to be returned.
     * @return Returns the item if available, else returns null.
     */
    public Item getDescription(String itemName)
    {
        return this.itemCollection.get(itemName);
    }

    /**
     * Returns a long description of this room, of the form.
     * Format are as follows;
     *      Item in room: Wine
     *      -- OR --
     *      Item in room: Wine Tree Branch
     *      -- OR --
     *      There are no items in the room.
     * @return Returns the items within the collection.
     */
    public String getItemsDescription()
    {
        String returnString = "";
        if ( this.itemCollection.size() == 1 )
        {
            for ( Item item : this.itemCollection.values() )
            {
                returnString += ".\n Item in room: " + item.getDescription();
            }
        }

        else if ( this.itemCollection.size() > 1 )
        {
            returnString = ".\n Items in room:";

            for ( Item item : this.itemCollection.values() )
            {
                returnString +=  " " + item.getDescription() + ",";
            }
            returnString = returnString.substring (0, returnString.length() - 1 );
        }

        else
        {
            returnString +=  "\n There are no items in the room.";
        }

        return returnString;
    }

    /**
     * Prints each item with item weight in the collection.
     */
    public String getCollectionWeight()
    {
        String returnString = "";

        if ( this.itemCollection.size() > 0 )
        {
            for ( Item item : this.itemCollection.values() )
            {
                returnString +=  item.getDescription() + " weighing " + item.getWeight() + " kg\n";
            }
        }

        else
        {
            returnString +=  "\n There are no items to display.";
        }

        return returnString;
    }

    /**
     * Returns a long description of this room, of the form.
     * Format are as follows;
     *      Item in room: Wine
     *      -- OR --
     *      Item in room: Wine Tree Branch
     *      -- OR --
     *      There are no items in the room.
     * @return Returns the items within the collection.
     */
    public String getItemsName()
    {
        String returnString = "";

        if ( this.itemCollection.size() > 0 )
        {
            for ( Item item : this.itemCollection.values() )
            {
                returnString += "" + item.getName();
            }
        }
        return returnString;
    }

    /**
     * Returns name of all items in the collection, if any.
     * @return name of all items in the collection, if any.
     */
    public void getItemNames()
    {
        if ( this.itemCollection.isEmpty())
        {
            System.out.println("No items!");
        }

        else
        {
            for (Item item : this.itemCollection.values())
            {
                String itemName = item.getName();
                System.out.println(itemName);
            }
        }
    }

    /**
     * Returns the object Item if in the collection
     * @param itemName the name of the Item to be returned.
     * @return true if the the object is in the collection.
     *         false otherwise.
     */
    public Item getItem(String itemName)
    {
        Item item = null;

        if ( containsItem(itemName) )
        {
            item = this.itemCollection.get(itemName);
        }

        return item;
    }

    /**
     * Returns the total weight of the items in the collection in kilogram.
     * @return The total weight of the items in the collection in kilogram.
     */
    public float getTotalWeight()
    {
        float weight = 0;
        for ( Item item : this.itemCollection.values() )
        {
            weight = weight + item.getWeight();
        }
        return weight;
    }

    /**
     * Returns true if the item of the collection is edible, false otherwise.
     * @return true if the item of the collection is edible, false otherwise.
     */
    public boolean isEdible(String itemName)
    {
        return itemCollection.get(itemName).isEdible();
    }
}
