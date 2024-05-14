import java.net.*;
import java.io.*;

public class ChatBotServer {
    public static void main(String[] args)throws Exception {
        ServerSocket ss=new ServerSocket(3333);
        Socket s=ss.accept();
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());

        String msg="", reply;
        while(!msg.equals("stop")){
            msg=din.readUTF();
            System.out.println("client says: "+msg);

            if (msg.equalsIgnoreCase("Hello")) {
                reply = "Hola Amigo!";
            } else if (msg.equalsIgnoreCase("how are you")) {
                reply = "I am fine, thank you and you.";
            } else if (msg.equalsIgnoreCase("bye")) {
                reply = "Have a great day!";
            } else if (msg.equalsIgnoreCase("stop")) {
                reply = "stop";
            } else {
                reply = "Out of Syllabus";
            }

            dout.writeUTF(reply);
            dout.flush();
        }

        din.close();
        s.close();
        ss.close();
    }
}  