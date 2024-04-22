package teak;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            App.getInstance().run();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}