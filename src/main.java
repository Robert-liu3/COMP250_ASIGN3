public class main {
    public static void main(String[] args) {
        TST<Character> tree = new TST<>();

        tree.insert('5');
        tree.insert('2');
        tree.insert('4');
        tree.insert('3');
        tree.insert('4');
        System.out.println(tree.toString());
        tree.remove('5');
        System.out.println(tree.toString());
    }

}
