public class Main {

    public static void main(String[] args)
    {
        int[] result = new int[25];
        for(int i = 0; i<result.length;i++)
        {
            result[i] = getFreq((i - 10));
            System.out.print(result[i] + ", ");
        }
        double b  = Math.pow(2,3);
        System.out.println(getFreq(0));
    }

    private static int getFreq(int i)
    {
        double f;

        double exp = (float)i/12;
        f = 1000000/(880*Math.pow(2,exp));
       // System.out.println(f);
        return (int)f;
    }

}
