package kr.or.iei.ex20201106.singleton;

public class Application {
    public static void main(String[] args) {
        final String name = Singleton.getInstance().getName();
        System.out.println(name);
        Singleton.getInstance().addToList("1");
        Singleton.getInstance().addToList("2");
        Singleton.getInstance().addToList("3");
        Singleton.getInstance().showList();
    }
}
