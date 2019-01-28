public class TodoList{
    private String desc;
    private int priority;
    private boolean complete;
    private ArrayList<String> list = new ArrayList<string>();


    public TodoList(String desc, int priority){
        this.desc = desc;
        this.priority = priority;
    }

    public static String toString(){

    }

    public static void add(String item){
        list.add(item);
        System.out.println("added " + item + " to list");
    }

    public static void remove(String item){
        list.remove(item);
        System.out.println("removed " + item + " from list");
    }

}