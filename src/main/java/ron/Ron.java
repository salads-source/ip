package ron;

import ron.command.Parser;
import ron.task.TaskList;
import ron.storage.Storage;
import ron.ui.Ui;

/**
 * The main class for the Ron task manager.
 * <p>
 * This class initializes the task management system, loads stored tasks,
 * and continuously processes user commands via the command-line interface.
 * </p>
 */
public class Ron {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the Ron task manager with a specified storage file path.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Ron(String filePath) {
        TaskList taskList;
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (RonException e) {
            Ui.echoMessage("Error loading tasks.");
            taskList = new TaskList();
        }
        this.tasks = taskList;
    }

    /**
     * Starts the Ron task manager.
     * <p>
     * Greets the user and enters a loop that continuously reads and processes commands
     * until the program is terminated.
     * </p>
     */
    public void run() {
        Ui.greetUser();

        while (this.ui.hasNextInput()) {
            Ui.printSeparator();
            String command = this.ui.readNextCommand();
            Parser.parseCommand(command, this.tasks, this.storage, this.ui);
            Ui.printSeparator();
        }
    }

    /**
     * The entry point for the Ron task manager.
     * <p>
     * Initializes and starts the Ron application using the default file path for task storage.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Ron("./data/ron.txt").run();
    }


}
