package Debug;

public class Main {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            System.out.println("index is: " + index );
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
        }
        System.out.println(" ");
    }
       public void test() {
        //no code yet
        //findAbc("abcd");
        //findAbc("abcdabc");
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        findAbc("abcabcabcabca");
    }
    public static void main(String[] args) {
        Main mn = new Main();
        mn.test();
    }
}