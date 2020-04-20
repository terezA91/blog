package blog;

import blog.User;
import blog.UserStorage;

import java.util.Scanner;
import java.util.Date;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UserStorage userStorage = new UserStorage();
    static PostStorage postStorage = new PostStorage();
    static User us;

    private static final int REGISTER = 1;
    private static final int LOGIN = 2;
    private static final int SEARCH_POST = 3;
    private static final int POSTS_BY_CATEGORY = 4;
    private static final int ALL_POSTS = 5;
    private static final int EXIT = 0;

    private static final int ADD_POST = 1;
    private static final int LOGOUT = 2;

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands1();
            String commandStr = scanner.nextLine();
            int command = Integer.parseInt(commandStr);
            switch (command) {
                case REGISTER:
                    register();
                    break;
                case LOGIN:
                    login();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;
                case ALL_POSTS:
                    postStorage.printAlPosts();
                    break;
                case EXIT:
                    isRun = false;
                    System.out.println("Հաջողություն");
                    break;
                default:
                    System.out.println("Նման հրաման չունենք!");
                    break;
            }
        }
    }

    private static void register() {
        System.out.println("Գրանցվելու համար մուտքագրել տվյալներ(name,surname,email,password)");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        User userOb = new User();
        userOb.setName(userData[0]);
        userOb.setSurname(userData[1]);
        userOb.setEmail(userData[2]);
        userOb.setPassword(userData[3]);
        userStorage.add(userOb);
        System.out.println("Գրանցումը հաջողությամբ կատարվեց");
    }

    private static void login() {
        System.out.println("Մուտք գործելու համար մուտքագրել տվյալները");
        System.out.println("email");
        String email = scanner.nextLine();
        System.out.println("password");
        String password = scanner.nextLine();
        us = userStorage.getUserByEmailAndPassword(email, password);
        boolean b = true;
        while (b && us!=null) {
            printCommands2();
            String commandS = scanner.nextLine();
            int commands = Integer.parseInt(commandS);
            switch (commands) {
                case ADD_POST:
                    addPost();
                    break;
                case LOGOUT:
                    b = false;
                    break;
                default:
                    System.out.println("Նման հրաման չունենք!");
            }
        }
    }

    private static void addPost() {
        System.out.println("Ավելացնել գրառում(title,text,category,createdDate)");
        String postDataStr = scanner.nextLine();
        String[] postData = postDataStr.split(",");
        Post postOb = new Post();
        postOb.setTitle(postData[0]);
        postOb.setText(postData[1]);
        postOb.setCategory(postData[2]);
        postOb.setCreatedDate(new Date());
        postOb.setUser(us);
        postStorage.add(postOb);
        System.out.println("Գրառումը ավելացված է");
    }

    private static void printCommands1() {
        System.out.println("Ներմուծել " + REGISTER + " համակարգում գրանցվելու համար");
        System.out.println("Ներմուծել " + LOGIN + " համակարգ մուտք գործելու համար");
        System.out.println("Ներմուծել " + SEARCH_POST + " գրառում փնտրելու համար");
        System.out.println("Ներմուծել " + POSTS_BY_CATEGORY + " գրառումն ըստ կատեգորիայի փնտրելու համար");
        System.out.println("Ներմուծել " + ALL_POSTS + " գրառումները տեսնելու համար");
        System.out.println("Ներմուծել " + EXIT + " դուրս գալու համար");
    }

    private static void printCommands2() {
        System.out.println("Ներմուծել " + ADD_POST + " գրառում ավելացնելու համար");
        System.out.println("Ներմուծել " + LOGOUT + " հետ գնալու համար");
    }

    private static void postsByCategory() {
        System.out.println("Ներմուծել կատեգորիան՝ գրառումը տեսնելու համար");
        String category = scanner.nextLine();
        postStorage.printPostsByCategory(category);
    }

    private static void searchPost() {
        System.out.println("Ներմուծել բառ՝ գրառում փնտրելու համար");
        String keyword = scanner.nextLine();
        postStorage.searchPostsByKeyword(keyword);
    }

}
