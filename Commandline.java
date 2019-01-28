import java.util.Scanner;
import java.util.*;

public class Commandline{
    public static void main(String[] args){
        System.out.println("Start Program:");

        int selection;
        Scanner scan = new Scanner(System.in);

        //Create arrayList
        ArrayList<ListItem> todoList = new ArrayList<ListItem>();

        //Display menu options
        do {
            //Show menu options
            menuGUI();
            selection = scan.nextInt();
            System.out.println("");
            selector(selection, scan, todoList);
        } while (selection != 0);
    }

    public static void menuGUI(){
        System.out.println("0: exit");
        System.out.println("1: add item");
        System.out.println("2: remove item");
        System.out.println("3: set item status");
        System.out.println("4: print list" + "\n");
    }

    public static void selector(int selection, Scanner scan, ArrayList<ListItem> todoList){
        if (selection == 1){
            todoList.add(addItem(scan));
        } else if (selection == 2){
            removeItem(scan, todoList);
        } else if (selection == 3){
            setItemStatus(scan, todoList);
        } else if (selection == 4){
            newSortList(todoList);
            printList(todoList);
        } else {
            if(selection != 0) System.out.println("Invalid input" + '\n');
        }
    }

    public static ListItem addItem(Scanner scan){
        System.out.println("CREATING TODO");
        String desc = "";
        desc = scan.nextLine();
        int priority = 0;
        do{
            System.out.println("Enter description (can't be empty):");
            desc = scan.nextLine();
        } while (desc.trim().equals(""));
        do{
            System.out.println("Enter priority (1-5):");
            priority = scan.nextInt();
        } while (priority < 1 || priority > 5);
        System.out.println("TODO MADE" + '\n');

        ListItem item = new ListItem(desc, priority);
        return item; 
    }
    
    public static void removeItem(Scanner scan, ArrayList<ListItem> todoList){
        System.out.println("Enter description for item to remove:");
        String desc = scan.nextLine();
        desc = scan.nextLine();
        int index = -1;

        for(int i = 0; i < todoList.size(); i++){
            if(todoList.get(i).getDesc().equals(desc)){
                index = i;
            }
        }

        if(index == -1){
            System.out.println("item doesn't exist" + '\n');
        } else {
            System.out.println("Removing: " + todoList.get(index).getDesc() + '\n');
            todoList.remove(index);
        }
    }

    public static void setItemStatus(Scanner scan, ArrayList<ListItem> todoList){
        System.out.println("Item to change (enter desc):");
        String desc = scan.nextLine();
        desc = scan.nextLine();
        int index = -1;

        for(int i = 0; i < todoList.size(); i++){
            if(todoList.get(i).getDesc().equals(desc)){
                index = i;
            }
        }

        if(index == -1){
            System.out.println("item doesn't exist");
        } else {
            System.out.println("Is Complete?: " + todoList.get(index).getComplete());
            System.out.println("Enter new status (0: not complete, 1: complete)");
            int status = scan.nextInt();
            if(status == 0) todoList.get(index).setComplete(false);
            else if(status == 1) todoList.get(index).setComplete(true);
            else System.out.println("invalid int"); 
        }
    }

    public static ArrayList<ListItem> sortList(ArrayList<ListItem> todoList){

        ArrayList<ListItem> sortedList = new ArrayList<ListItem>();
        
        for(int j = 5; j >= 1; j--){
            for(int i = 0; i < todoList.size(); i++){
                if(todoList.get(i).getPriority() == j){
                    sortedList.add(todoList.get(i));
                } 
            }
        }
       return sortedList;
    }

    public static void newSortList (ArrayList<ListItem> todoList){
        for (int i = 0; i<todoList.size();i++){
            for (int j = todoList.size()-1; j>i; j--){
                if (todoList.get(j-1).getPriority()<todoList.get(j).getPriority()){
                    ListItem temp =todoList.get(j-1);
                    todoList.get(j-1) = todoList.get(j);
                    todoList.get(j) = temp;

                }

            }
        }


    }

    public static void printList(ArrayList<ListItem> todoList){
        String complete = "";
        for(int i = 0; i < todoList.size(); i++){
            complete = complete + todoList.get(i).toString() + " | ";
        }
        System.out.println("List size: " + todoList.size());
        System.out.println(complete + '\n');
    }

    public static void printListSorted(ArrayList<ListItem> todoList){
        String complete = "";
        for(int j = 5; j >= 1; j--){
            for(int i = 0; i < todoList.size(); i++){
                if(todoList.get(i).getPriority() == j) complete = complete + todoList.get(i).toString() + " | ";
            }
        }
        System.out.println("List size: " + todoList.size());
        System.out.println(complete + '\n');
    }
}