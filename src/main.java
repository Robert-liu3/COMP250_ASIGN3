public class main {
    public static void main(String[] args) {
        TST<Character> tree = new TST<>();

        tree.insert('5');
        tree.insert('0');
        tree.insert('4');
        tree.insert('2');
        System.out.printf(tree.toString());
    }

}
