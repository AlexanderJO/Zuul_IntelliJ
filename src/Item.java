/**
 * This class is for an item in a game.
 *
 *
 *
 * @author Alexander J. Overvåg
 * @version v0.1 2019.02.27
 */
public class Item
{
    // -------------- Fields -------------------
    private String name;                        // The name of the item.
    private String description;                 // The description of the item.
    private float weight;                       // Stores the item weight in kilogram.
    private boolean edible;                    // Stores whether the item is edible or not.
    private boolean carriable;                 // Stores whether the item is possible to be carried.

    // -------------- Constructor ---------------
    public Item(String name, String description, float weight, boolean eatable, boolean carriable)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.edible = eatable;
        this.carriable = carriable;
    }

    // -------------- Methods -------------------

    /**
     * Returns the description of the item.
     * @return the description of the item.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Returns the description of the item.
     * @return the description of the item.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the weight of the item in kilogram.
     * @return the weight of the item in kilogram.
     */
    public float getWeight()
    {
        return this.weight;
    }

    /**
     * Returns true if the item is edible, else returns false.
     * @return true if the item is edible, else returns false.
     */
    public boolean isEdible()
    {
        return this.edible;
    }

    /**
     * Returns true if the item is carriable, else returns false.
     * @return true if the item is carriable, else returns false.
     */
    public boolean isCarriable()
    {
        return this.carriable;
    }
}
