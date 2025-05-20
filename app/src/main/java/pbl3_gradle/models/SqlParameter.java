package pbl3_gradle.models;

public class SqlParameter {
    public int index;
    public Object value;

    public SqlParameter(int index, Object value) {
        this.index = index;
        this.value = value;
    }
}
