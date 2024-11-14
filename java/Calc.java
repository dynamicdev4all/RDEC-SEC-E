public class Calc {
    public static void main(String[] args){
        int z= 0;
        String op = args[1];
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[2]);
        
        if(op.equals("+"))
            z = x+y;
        else if(op.equals("-"))
            z = x-y;
        else if(op.equals("*"))
            z = x*y;
        else if(op.equals("/"))
            z = x/y;
        System.out.println(z);
    }
}
