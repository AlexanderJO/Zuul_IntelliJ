import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRoomsAndPlayer();
        this.parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRoomsAndPlayer()
    {
        Room outside;
        Room theater;
        Room pub;
        Room lab;
        Room office;
        Room cellar;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the creepy wine cellar");

        // initialise room exits
        // Creates the outside area with exits and possible items.
        outside.setExits( "east", theater);
        outside.setExits( "south", lab);
        outside.setExits( "west", pub);
        outside.addItem(new Item("Poo","Dog poo", 0.1F, false, false));
        outside.addItem(new Item("Branch","Tree Branch of stuff", 5.3F, false, true));
        outside.addItem(new Item("Flower","Yellow flower", 0.1F, true, true));

        // Creates the theater area with exits and possible items.
        theater.setExits( "west", outside);
        theater.addItem(new Item("Dress","Dress of unique quality", 1.0F, false, true));
        theater.addItem(new Item("Donut","Donut", 0.1F, true, true));

        // Creates the pub area with exits and possible items.
        pub.setExits("south", outside);
        pub.addItem(new Item("Beer","Beer", 0.5F, true, false));        // Creates an anonymous object.
        pub.addItem(new Item("Chili","Chili is hot", 0.2F, true, true));

        // Creates the lab area with exits and possible items.
        lab.setExits("north", outside);
        lab.setExits("east", office);
        lab.addItem(new Item("Poison","Poison of dangerous stuff", 0.1F, false, true));
        lab.addItem(new Item("Cookie","Magic cookie", 0.1F, true, true));

        // Creates the office area with exits and possible items.
        office.setExits("west", lab);
        office.setExits("down", cellar);
        office.addItem(new Item("Book","Book of boring stuff", 0.5F, false, true));

        // Creates the cellar area with exits and possible items.
        cellar.setExits("up", office);
        cellar.addItem(new Item("Wine","Wine of old vintage", 0.75F, false, true));

        // Add the player.
        player = new Player("Alexander", cellar,100, 5.0F);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcomeInfo();             // Prints a welcome message.
        player.printLocationInfo();            // Prints current location.

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        printGoodbyeInfo();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcomeInfo()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
    }

    /**
     * Print out the message when exiting/quitting the game.
     */
    private void printGoodbyeInfo()
    {
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown())
        {
            errorMessage();
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
            commandList();
        }
        else if (commandWord.equals("go"))
        {
            player.goRoom(command);
        }
        else if (commandWord.equals("commands"))
        {
            commandList();
        }
        else if (commandWord.equals("look"))
        {
            player.look();
        }
        else if (commandWord.equals("eat"))
        {
            player.eatItem(command);
        }
        else if (commandWord.equals("quit"))
        {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back"))
        {
            player.goBack(command);
        }
        else if (commandWord.equals("pick"))
        {
            player.pickUpItem(command);
        }
        else if (commandWord.equals("drop"))
        {
            player.dropItem(command);
        }
        else if (commandWord.equals("weight"))
        {
            player.getInventoryWeight();
        }
        else if (commandWord.equals("inventory"))
        {
            player.printPlayerInventory();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
    }

    /**
     * Print out the commands.
     */
    private void commandList()
    {
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * The error message to be printed if incorrect command is used.
     */
    private void errorMessage()
    {
        System.out.println("I don't know what you mean...");
    }
}