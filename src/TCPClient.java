import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient extends Thread {

    private String host;
    private int port;

    private String sentence;
    private String modifiedSentence;

    private Socket clientSocket;
    private BufferedReader inFromUser;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    boolean connected = true;

    public static void main(String argv[])
    {

        TCPClient client = new TCPClient();
        client.run();
    }

    public TCPClient() {
    }

    public void setUpConnection() {
        try {
            System.out.println("----Connection Setup----");
            System.out.println("IP: ");
            Scanner userTextInput = new Scanner(System.in);
            host = userTextInput.next();
            System.out.println("Port: ");
            port = Integer.parseInt(userTextInput.next());

            clientSocket = new Socket(host, port);
            inFromUser = new BufferedReader(new InputStreamReader(System.in));
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("CONNECTION SUCCESFULL");

        }

        catch (Exception e) {
            System.out.println("CONNECTION FAILED");
            e.printStackTrace();
            setUpConnection();
        }
    }

    public void dataTransfer() {
        try{
            while (connected) {
                System.out.println("Please enter a command: ");
                sentence = inFromUser.readLine();
                if (sentence.equals("HELP")) {
                    System.out.println("    DRIVE FORWARD/BACKWARD speed ");
                    System.out.println("    TURN FORWARD/BACKWARD LEFT/RIGHT speed");
                    System.out.println("    STOP");
                    System.out.println("    PING");
                    System.out.println("    DISCONNECT");
                }
                else if(sentence.equals("DISCONNECT")){
                    connected = false;
                    clientSocket.close();

                } else {
                    outToServer.writeBytes(sentence + '\n'); //TODO: Encrypt message.
                    modifiedSentence = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + modifiedSentence);
                }
            }
        }
        catch (Exception e) {
            System.out.println("CONNECTION LOST");
            setUpConnection();
            dataTransfer();
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        setUpConnection();
        dataTransfer();
    }
}