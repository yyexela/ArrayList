public class ListItem{
    private String desc;
    private int priority;
    private boolean complete;

    public ListItem(String desc, int priority){
        this.desc = desc;
        this.priority = priority;
        this.complete = false;
    }

    public String getDesc(){
        return desc;
    }

    public int getPriority(){
        return priority;
    }

    public boolean getComplete(){
        return complete;
    }

    public void setComplete(boolean status){
        complete = status;
    }

    public String toString(){
        return ("Desc: " + desc + ", Priority: " + priority + ", Is complete?: " + complete);
    }
}