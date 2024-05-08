package cache;

public class Line {
    private int tag;
    private boolean isValid;
    private boolean isDirty;
    private int flag;

    public Line() {
        tag = 0;
        isValid = false;
        isDirty = false;
        flag = 0;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void addLru() {
        flag++;
    }
}
