public class FnDemo {


    static void sum0(){
        System.out.println("this is fun0");
        sum1(0); //vid without parameter

    }
    static void sum1(int a){ 
        System.out.println("this is fun1");
        sum2();//void with paramter

    }
    static int sum2(){
        System.out.println("this is fun2");
        sum3(0); //non-void without parameter
          return 0;
    }
    static int sum3(int a){
        System.out.println("this is fun3"); //non-void with parameter
        return 0;
    }
    public static void main(String[] args) {
        sum0();
    }
    
}
