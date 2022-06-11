import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client{
    public static void main(String[] args) {
        try(
            Socket socket = new Socket("localhost", 8989);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println(in.readLine());
            out.println("Hello from the client");
        }
        catch(Exception e){
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