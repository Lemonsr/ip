package aisu.task;

import aisu.AisuException;

/**
 * An Event task.
 * Consists of a description and an event period.
 *
 * @author Liaw Xin Yan
 */
public class Event extends Task {
    private final String eventPeriod;

    public Event(String description, String eventPeriod) throws AisuException {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    /**
     * Parses data in readable format to be stored in storage.
     *
     * @return Parsed data.
     */
    @Override
    public String ParseData() {
        return "E;;" + (this.isDone ? "1" : "0") + ";;" + this.description + ";;" + this.eventPeriod;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return The Task in readable format.
     */
    @Override
    public String toString() {
        return String.format("[Event] %s %s (at: %s)", this.getStatusIcon(), this.description, this.eventPeriod);
    }
}
