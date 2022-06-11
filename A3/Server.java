import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.math.BigInteger;

public class Server{
    public static void main(String[] args) {
        int port = 8989;
        System.out.format("listening on %d\n", port);
        System.out.println("Fast Modular Exponentiation of 10^8 % 133: "+fastModularExp(10, 8, 133));
        try(
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            out.println("hello from server");
            System.out.println(in.readLine());

            Random rand1 = new Random();
            Random rand2 = new Random();

            int randInt = rand1.nextInt(1024)+1024;

            BigInteger p = BigInteger.probablePrime(randInt, rand2);
            System.out.println(p);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int fastModularExp(int b, int e, int n) {
        if(n==1){
            return 0;
        }
        int rs = 1;
        while(e>0){
            if((e & 1) == 1){
                rs = (rs*b)%n;
            }
            e = e >> 1;
            b = (b*b)%n;
        }
        return rs;
    }

}