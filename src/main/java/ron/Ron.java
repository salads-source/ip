package ron;

import ron.command.Parser;
import ron.task.TaskList;
import ron.storage.Storage;
import ron.ui.Ui;
public class Ron {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public void run() {
        Ui.greetUser();

        while (this.ui.hasNextInput()) {
            Ui.printSeparator();
            String command = this.ui.readNextCommand();
            Parser.parseCommand(command, this.tasks, this.storage, this.ui);
            Ui.printSeparator();
        }
    }

    public static void main(String[] args) {
        new Ron("./data/ron.txt").run();
    }


}
