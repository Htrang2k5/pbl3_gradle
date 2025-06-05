package pbl3_gradle.models;
import pbl3_gradle.models.*;
import pbl3_gradle.controllers.*;

import java.util.ArrayList;
import java.util.List;

public class SprintList {
    public List<Sprint> sprints = new ArrayList<>();

    public List<Sprint> getSprintList() {
        return sprints;
    }

    public void setSprintList(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }
    public void removeSprint(Sprint sprint) {
        sprints.remove(sprint);
    }
    public void updateSprint(Sprint sprint) {
        for (int i = 0; i < sprints.size(); i++) {
            if (sprints.get(i).getIdSprint() == sprint.getIdSprint()) {
                sprints.set(i, sprint);
                break;
            }
        }
    }
}
