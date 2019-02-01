import java.util.Scanner;
import java.util.*;

public class Commandline{
    public static void main(String[] args){
        System.out.println("Start Program:");

        int selection;
        Scanner scan = new Scanner(System.in);

        //Create arrayList
        ArrayList<ListItem> todoList = new ArrayList<ListItem>();
        todoList.add(new ListItem("hi", 5));
        todoList.add(new ListItem("bye", 2));
        todoList.add(new ListItem("gda", 1));
        todoList.add(new ListItem("gdg", 0));
        todoList.add(new ListItem("gda", 1));
        todoList.add(new ListItem("bxv", 3));
        todoList.add(new ListItem("dsad", 2));
        todoList.add(new ListItem("nag", 4));
        todoList.add(new ListItem("fD", 2));
        todoList.add(new ListItem("F", 1));
        todoList.add(new ListItem("gfd", 4));
        todoList.add(new ListItem("trw", 5));
        todoList.add(new ListItem("fda", 2));
        todoList.add(new ListItem("dzg", 7));

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
        System.out.println("4: print list");
        System.out.println("5: check if exists");
        System.out.println("6: check if pair exists" + "\n");
    }

    public static void selector(int selection, Scanner scan, ArrayList<ListItem> todoList){
        if (selection == 1){
            todoList.add(addItem(scan));
        } else if (selection == 2){
            removeItem(scan, todoList);
        } else if (selection == 3){
            setItemStatus(scan, todoList);
        } else if (selection == 4){
            selectionSort(todoList);
            printList(todoList);
            System.out.println(binarySearch(todoList, 7) + "" + '\n');
        } else if(selection == 5) {
            ListItem check = new ListItem("fda", 2);
            System.out.println(exists(todoList, check) + "" + '\n');
        } else if(selection == 6) {
            ListItem check1 = new ListItem("fda", 2);
            ListItem check2 = new ListItem("fD", 2);
            System.out.println(existsPair(todoList, check1, check2) + "" + '\n');
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

    public static void selectionSort(ArrayList<ListItem> todoList){
        //printList(todoList);
        for (int i = 0; i < todoList.size(); i++){
            ListItem temp = todoList.get(i);
            int index = i;
            for (int j = i+1; j < todoList.size(); j++){
                if(todoList.get(j).getPriority() < temp.getPriority() ){
                    temp = todoList.get(j);
                    index = j;
                } 
            }
            todoList.set(index,todoList.get(i));
            todoList.set(i,temp);
            //printList(todoList);
        }

    }

    public static void insertionSort(ArrayList<ListItem> todoList){
        if(todoList.size() > 1){
            printList(todoList);
            for (int i = 0; i < todoList.size(); i++){
                ListItem temp = todoList.get(i);
                todoList.remove(i);
                int index = i;
                for (int j = i-1; j >= 0; j--){
                    if (todoList.get(j).getPriority() < temp.getPriority()){
                        index = j;
                    }
                }
                printList(todoList);
                todoList.add(index, temp);
            }
            printList(todoList);
        }
    }

    public static void bubbleSort(ArrayList<ListItem> todoList){
        if(todoList.size() > 1){
            for (int i = 0; i < todoList.size()-1; i++){
                for (int j = todoList.size()-1; j > i; j--){
                    if (todoList.get(j-1).getPriority() < todoList.get(j).getPriority()){
                        ListItem temp = todoList.get(j-1);
                        todoList.set(j-1, todoList.get(j));
                        todoList.set(j, temp);
                        printList(todoList);
                    }
                }
            }
        }
    }

    public static boolean exists(ArrayList<ListItem> todoList, ListItem check){
        for(int i = 0; i < todoList.size(); i++){
            if(todoList.get(i).getDesc().equals(check.getDesc()) && todoList.get(i).getPriority() == check.getPriority()){
                return true;
            }
        }
        return false;
    }

    public static boolean existsPair(ArrayList<ListItem> todoList, ListItem check1, ListItem check2){
        if(exists(todoList, check1) && exists(todoList, check2)) return true;
        return false;
    }

    public static void printList(ArrayList<ListItem> todoList){
        String complete = "";
        for(int i = 0; i < todoList.size(); i++){
            complete = complete + todoList.get(i).getPriority() + " | ";
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

    public static boolean binarySearch ( ArrayList<ListItem> todoList, int num){
        int check = (todoList.size())/2;
        int previousCheck = -10;
        System.out.println("Searching for: " + num + ", check(" + check + "): " + todoList.get(check).getPriority() );
        do {
            if (num == todoList.get(check).getPriority()){
                System.out.println("found " + num + " at check(" + check + "): " + todoList.get(check).getPriority());
                return true;
            }
            if (num > todoList.get(check).getPriority()){
                System.out.println("num: " + num + " > check(" + check + "): " + todoList.get(check).getPriority());
                previousCheck = check;
                check += (todoList.size()-check) / 2;
                System.out.println("previousCheck: " + previousCheck);
                System.out.println("new check: " + check);
            }
            if (num < todoList.get(check).getPriority()){
                System.out.println("num: " + num + " < check(" + check + "): " + todoList.get(check).getPriority());
                previousCheck = check;
                check = (check) / 2;
                System.out.println("previousCheck: " + previousCheck);
                System.out.println("new check: " + check);
            }
        System.out.println("LOOP: Math.abs: " + Math.abs(previousCheck-check) + "" + '\n');
        } while( !(Math.abs(previousCheck-check) == 0)); 
        return false;
    }
}