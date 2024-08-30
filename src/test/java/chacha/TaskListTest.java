package chacha;

import chacha.task.Task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    TaskList tasks = new TaskList();
    private ChaCha chacha = new ChaCha("./src/main/java/chacha/data/chacha.txt");
    Ui ui = chacha.ui;
    Storage storage = chacha.storage;

    @Test
    public void addToDo_missingComponent_exceptionMessage() {
        try {
            Task task = tasks.addToDo("todo", ui, storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("What task are you intending to add as a \'todo\'?", actual);
        }
    }

    @Test
    public void deleteTask_missingIndex_exceptionMessage() {
        try {
            Task task = tasks.deleteTask("delete", ui, storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the index of task you want to delete. ", actual);
        }
    }

    @Test
    public void markDone_missingIndex_exceptionMessage() {
        try {
            Task task = tasks.markDone("mark", ui, storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the index of task you want to mark. ", actual);
        }
    }

    @Test
    public void markUndone_missingIndex_exceptionMessage() {
        try {
            Task task = tasks.markUndone("unmark", ui, storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the index of task you want to unmark. ", actual);
        }
    }

    @Test
    public void find_missingText_exceptionMessage() {
        try {
            ArrayList<Task> list = tasks.find("find", ui);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the text you want to find. ", actual);
        }
    }
}
