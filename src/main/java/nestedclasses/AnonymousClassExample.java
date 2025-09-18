package nestedclasses;

/** An example of an anonymous class. Shows a way to pass parameters to the object of the anonymous class.
 *  Anonymous classes can not have explicit constructors, so we discussed how to initialize objects. */
public class AnonymousClassExample {
    public static void main(String[] args) {
        Runnable r = () -> {
            int res = 1;
            for (int i = 1; i <= 3; i++) {
                res *= i;
            }
            System.out.println(res);
        };
        Thread th = new Thread(r);
        th.start();

        // Suppose we do not want to hardcode the number of iterations like in previous example
        // Option 1: use initializer:
        Thread thread  = new Thread() {
            private int num;
            Thread init(int num){
                this.num = num;
                return this;
            }
            @Override
            public void run() {
                // computes and outputs the factorial of num
                int res = 1;
                for (int i = 1; i <= num; i++) {
                    res *= i;
                }
                System.out.println(res);
            }
        }.init(3);

        thread.start();

        // Option 2: Use final local variable
       final int n = 3;
       Thread threadOther  = new Thread() {
            @Override
            public void run() {
                // computes and outputs the factorial of num
                int res = 1;
                for (int i = 1; i <= n; i++) {
                    res *= i;
                }
                System.out.println(res);
            }
        };
        threadOther.start();
    }


}
