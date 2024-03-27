import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String[] str = {"asd","ihf",".","....",".."};
        for (String s:str){
            System.out.println(s.matches("\\.+"));

        }
        String com = "echo 123 > a/b";
        System.out.println(Arrays.toString(com.split(" ")[3].split("/")));
    }
}
