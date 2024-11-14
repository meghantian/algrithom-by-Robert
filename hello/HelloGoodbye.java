public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length < 2)
            return;
        String s1 = args[0], s2 = args[1];
        System.out.println("Hello" + " " + s1 + " and " + s2 + ".");
        System.out.println("Goodbye" + " " + s2 + " and " + s1 + ".");
    }
}
