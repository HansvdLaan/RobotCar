import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPServer extends Thread  {

    public RobotCar robotCar;
    BufferedReader inFromClient;
    DataOutputStream outToClient;

    String clientMessage;
    String confirmationMessage;

    Scanner userTextInput;
    int port;

    ServerSocket welcomeSocket;
    Socket connectionSocket;

    public TCPServer(RobotCar robotCar) {
        this.robotCar = robotCar;
    }


    private static boolean commandCheck(String command) {
        command = command.toUpperCase();
        String[] commandsArray = command.split(" ");
        try {
            switch (commandsArray[0]) {
                case "DRIVE": {
                    if ((commandsArray[1].equals("FORWARD") || commandsArray[1].equals("BACKWARD")) &&
                            (commandsArray[2].matches("[+-]?(\\d*\\.?\\d+)|(\\d+\\.?\\d*)"))) {
                        return true;
                    } else {
                        return false;
                    }
                }
                case "TURN": {
                    if ((commandsArray[1].equals("FORWARD") || commandsArray[1].equals("BACKWARD")) &&
                            commandsArray[2].equals("LEFT") || commandsArray[1].equals("RIGHT") &&
                            commandsArray[3].matches("[+-]?(\\d*\\.?\\d+)|(\\d+\\.?\\d*)")) {
                        return true;
                    } else {
                        return false;
                    }
                }
                case "STOP": {
                    return true;
                }
                case "PING": {
                    return true;
                }
                default: {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void setUpServer() {
        try {
            System.out.println("----Connection Setup----");
            System.out.println("Port: ");

            userTextInput = new Scanner(System.in);
            port = Integer.parseInt(userTextInput.next());

            welcomeSocket = new ServerSocket(port);
            connectionSocket = welcomeSocket.accept();

            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); //TODO: make a temporary blacklist (after x tries with password/username).
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            System.out.println("CLIENT connected"); //TODO: make a username/password system

        }
        catch (Exception e){
            System.out.println("Connection Setup Failed");
            e.printStackTrace();
            setUpServer();
        }
    }

    public void dataTransfer() {
        try {
            while (true) { //TODO: add a way to neatly close the server
                clientMessage = inFromClient.readLine();
                System.out.println("TCPSERVER Received: " + clientMessage);
                clientMessage.toUpperCase();

                if(clientMessage.equals("PING")) {
                    confirmationMessage = "PONG" + '\n';
                }
                else if(commandCheck(clientMessage)) {
                    robotCar.giveCommand(clientMessage);
                    confirmationMessage = clientMessage + " OK" + '\n';
                }
                else {
                    confirmationMessage = clientMessage + " ERROR" + '\n'; //TODO, Error codes.
                }
                outToClient.writeBytes(confirmationMessage);
            }
        }
        catch (Exception e) {
            System.out.println("Connection with CLIENT broken");

            welcomeSocket = null;
            connectionSocket = null;

            setUpServer();  //TODO: NOT RESTARTING CORRECTLY AFTER A DISCONNECTION OF THE CLIENT!!!
            dataTransfer();
        }
    }


    @Override
    public void run() {
        setUpServer();
        dataTransfer();
    }


}
