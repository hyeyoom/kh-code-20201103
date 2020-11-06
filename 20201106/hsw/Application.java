package kr.or.iei.ex20201106.hsw;

public class Application {

    public static void main(String[] args) {
        Super testA = new Sub();
        Sub testB = new Sub();
        System.out.println(((Sub)testA).data);     // super
        System.out.println(testB.data);     // sub class
        testA.print();
        testB.print();
    }
}
