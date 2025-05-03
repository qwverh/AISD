package TaskParentheses;

import java.util.Stack;

public class Main {
    public static boolean correctString(String str) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < str.length(); ++i) {
            char x = str.charAt(i);
            switch(x) {
                case '(':
                case '{':
                case '[':
                    stack.push(x);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                    break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                    break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "()()()()()()())";
        System.out.println(correctString(str));
    }
}
