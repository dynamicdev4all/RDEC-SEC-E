import java.util.ArrayList;

public class Access {
    public static void main(String[] args) {
        // // Gym g = new Gym();

        // // g._memberName = "ABC";
        // // g.membershipType = "GOLD";

        // // g.set_memberName("ABC");

        // Gym g1 = new Gym("ABC", "GOLD");
        // Gym g2 = new Gym("DEF", "SILVER");
        // System.out.println(g1);
        // System.out.println(g2);

        ArrayList<String> l = new ArrayList<>();
        System.out.println(l);
        l.add("A");
        l.add("B");
        l.add("C");
        System.out.println(l);
        l.add(0, "D");
        System.out.println(l);
        System.out.println(l.get(1));

    }
}
