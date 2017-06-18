package Storage;

public class Command {
    public enum CommandType {
        ATTACKED,
        ATTACHED_EASY_MOB,
        ATTACHED_NORM_MOB,
        ATTACHED_HARD_MOB,
        CONTINUE
    }
    private CommandType command;
    private String name;
    private long timeout;

    public CommandType getCommand() {
        return command;
    }

    public void setCommand(CommandType command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
