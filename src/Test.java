/**
 * Created by User on 19-3-2015.
 */
public class Test {

    static public void main(String[] args) {

        int x = 3; //bytesOfDataRecieved ;
        byte[] ack = new byte[4];
        int bytecounter = 3;
        int forHexConversion = 0;
        for (double i = 32; i >= 0; i--) {
            if (x > Math.pow(2, i)) {
                System.out.println("i =" + i);
                System.out.println( x + " > " + Math.pow(2,i));
                forHexConversion += Math.pow(2, i % 8);
            }
            if ((i != 32) && i % 8 == 0) {
                System.out.println("i = " + i + "   forHexConversion =" + forHexConversion);
                ack[bytecounter] = Byte.parseByte(Integer.toHexString(forHexConversion));
                forHexConversion = 0;
                bytecounter = bytecounter-1;
            }
        }
        System.out.println(ack[3]);
        System.out.println(ack[2]);
        System.out.println(ack[1]);
        System.out.println(ack[0]);
    }
}
