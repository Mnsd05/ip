package duke.keycommands;

import duke.common.Common;

import java.io.IOException;

/**
 * Represents the command to change the status of a task in the task list.
 */
public class ChangeTaskStatusCommand {

    private static final String FINISH_UNMARKING_MESSAGE = "Ok! I've marked this task as not done yet:";

    private static final String FINISH_MARKING_MESSAGE = "Nice! I've marked this task as done:";

    public static final String UNMARK = "unmark";

    private int taskNumber;

    private String keyword;

    /**
     * Constructs a new ChangeTaskStatusCommand object with the given task number locating the task
     * and changes the status of this task in the task list.
     * @param taskNumber The position of the task to be changed.
     * @param keyword The keyword to determine whether the task is marked or unmarked.
     */
    public ChangeTaskStatusCommand(int taskNumber, String keyword) {
        this.taskNumber = taskNumber;
        this.keyword = keyword;
        changeTaskStatus();
    }

    private void changeTaskStatus() {
        boolean doesCommandContainsUnmark = keyword.equals(UNMARK);
        if (doesCommandContainsUnmark) {
            Common.tasks.get(taskNumber - 1).unMark();
            System.out.println(FINISH_UNMARKING_MESSAGE);
        } else {
            Common.tasks.get(taskNumber - 1).mark();
            System.out.println(FINISH_MARKING_MESSAGE);
        }
        System.out.println(taskNumber + ". " + Common.tasks.get(taskNumber - 1).getMarkingStatus()
                + " " + Common.tasks.get(taskNumber - 1).getContent());
        try {
            Common.dataFile.updateTask();
        } catch (IOException error) {
            System.out.println(Common.WRITEFILE_EXCEPTION_MESSAGE);
        }
    }

}
