package pbl3_gradle.models;

public class Project {
    private String projectID;
    private String projectName;
    private String projectOwner;
    private final List<Member> projectMember;
    private final List<Item> productBacklog;
    private final List<Sprint> projectSprint;
    private DateTime projectUpdatedDate;

    public Project() {
        projectMember = new ArrayList<>();
        productBacklog = new ArrayList<>();
        projectSprint = new ArrayList<>();
        DateTime projectCreatedDate = new DateTime();
    }

    public void addMember(Member member) {
        projectMember.add(member);
    }

    public void removeMember(Member member) {
        projectMember.remove(member);
    }

    public Sprint createSprint(DateTime start, DateTime end) {
        Sprint sprint = new Sprint(start, end);
        projectSprint.add(sprint);
        return sprint;
    }

    public List<Item> getProductBacklogList() {
        return productBacklog;
    }

    public List<Item> getSprintBacklogList(Sprint sprint) {
        return sprint.getTaskList(); // or separate sprint backlog object
    }
}
