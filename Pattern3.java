public class Pattern3 {
    public static void floyd_traingle(int n) {
        int c=1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(c+" ");
                c++;
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        floyd_traingle(5);
    }
}
