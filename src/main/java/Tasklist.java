import java.util.ArrayList;
import java.util.List;

/**
 * This is the Tasklist class.
 * It stores Tasks as a list.
 */
public class Tasklist {
    private final List<Task> list;
    public enum TaskTypes {
        T, D, E
    }

    public Tasklist() {
        this.list = new ArrayList<>();
    }

    public Tasklist(List<Task> currList) {
        this.list = currList;
    }

    public List<Task> getListData() {
        // May not be good OOP practice.
        return this.list;
    }

    private Task getTaskAt(int n) throws ArrayIndexOutOfBoundsException {
        if (n <= 0 || n > this.list.size()) {
            throw new ArrayIndexOutOfBoundsException("That is an invalid task number!");
        }
        return this.list.get(n-1);
    }

    public void addTask(String line, TaskTypes type) throws AisuException { // adds new task to taskList
        Task newTask;
        switch(type) {
            case T:
                newTask = new Todo(line);
                break;
            case D:
                try {
                    String[] temp = line.split(" /by ");
                    newTask = new Deadline(temp[0], temp[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new AisuException("Your formatting is wrong! Write as: deadline (task) /by (date)");
                }
                break;
            case E:
                try {
                    String[] temp = line.split(" /at ");
                    newTask = new Event(temp[0], temp[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new AisuException("Your formatting is wrong! Write as: event (task) /at (date range)");
                }
                break;
            default:
                throw new AisuException("That's an invalid task format...");
        }

        this.list.add(newTask);

        System.out.println(" Got it! I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void deleteTask(int n) throws ArrayIndexOutOfBoundsException {
        if (n <= 0 || n > this.list.size()) {
            throw new ArrayIndexOutOfBoundsException("That is an invalid task number!");
        }
        Task deletedTask = this.list.get(n - 1);
        this.list.remove(n - 1);

        System.out.println(" Noted~ I've removed this task:");
        System.out.println("   " + deletedTask);
        System.out.println(" Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void markDone(int n) throws ArrayIndexOutOfBoundsException {
        if (n <= 0 || n > this.list.size()) {
            throw new ArrayIndexOutOfBoundsException("That is an invalid task number!");
        }
        this.list.get(n - 1).markAsDone();
        System.out.println(" Nice! I've marked this task as completed:");
        System.out.println(this.getTaskAt(n));
    }

    @Override
    public String toString() { // displays the list
        if (this.list.isEmpty()) {
            return "Oops, the list is empty! :O";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            result.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return result.toString();
    }

}
