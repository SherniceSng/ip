package chacha.command;

import chacha.ChaCha;
import chacha.ChaChaException;
import chacha.Storage;
import chacha.Ui;
import chacha.task.Task;
import chacha.task.TaskList;

import java.time.DateTimeException;

public class EventCommand extends Command {

    public EventCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to adding Event task.
     *
     * @param userInput
     * @param storage
     * @param ui
     * @param tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        try {
            Task taskAdded = tasks.addEvent(userInput, ui, storage);

            return ui.printAdd(taskAdded, tasks);

        } catch (ChaChaException e) {
            return e.toString();
        } catch (DateTimeException e) {
            String[] arr = {"Please input the date in the format YYYY-MM-DD. "};
            return ui.printStrings(arr);
        }
    }
}
