import static java.lang.Thread.sleep;

public class RobotCar {

    public MotorControl controls = new MotorControl();

    public RobotCar() {
    }

    public void drive(Direction direction, Speed speed) {
        controls.setMotor(Motor.BACK_LEFT, speed, direction);
        controls.setMotor(Motor.BACK_RIGHT, speed, direction);
        controls.setMotor(Motor.FRONT_LEFT, speed, direction);
        controls.setMotor(Motor.FRONT_RIGHT, speed, direction);
    }

    public void turn(Direction directionFB, Direction directionLF, int speed) {
        controls.setMotor(Motor.BACK_LEFT, Speed.STOP, Direction.FORWARDS);
        controls.setMotor(Motor.BACK_RIGHT, Speed.STOP, Direction.FORWARDS);
        controls.setMotor(Motor.FRONT_LEFT, Speed.STOP, Direction.FORWARDS);
        controls.setMotor(Motor.FRONT_RIGHT, Speed.STOP, Direction.FORWARDS);
}

    public void stop(){
        controls.setMotor(Motor.BACK_LEFT, Speed.STOP, Direction.FORWARDS);
        controls.setMotor(Motor.BACK_RIGHT, Speed.STOP, Direction.FORWARDS);
        controls.setMotor(Motor.FRONT_LEFT, Speed.STOP, Direction.FORWARDS);
        controls.setMotor(Motor.FRONT_RIGHT, Speed.STOP, Direction.FORWARDS);
    }

    public void wait(int miliseconds) {
        try {
            sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void giveCommand(String command){
        String[] commandsArray = command.split(" ");
        System.out.println("RobotCar: " + command);
        switch (commandsArray[0]){
            case "DRIVE": {
                this.drive(Direction.FORWARDS, Speed.FORWARD_FAST);
                break;
            }
            case "TURN": {
                this.turn(null,null,0);
                break;
            }
            case "STOP" : {
                this.stop();
                break;
            }
        }
    }

    public static void main(String[] args) {
        RobotCar robotCar = new RobotCar();
        robotCar.drive(Direction.FORWARDS,Speed.FORWARD_FAST);
        System.out.println("MOTOR SET");
        //TCPServer server = new TCPServer(robotCar); //Since I want multiple devices to be able to connect to one drone, I chose
        //server.start();                             //the car to be the server.

        /*
        while (true) {
            robotCar.drive(100, Direction.FORWARD);
            robotCar.wait(2000);
            robotCar.drive(100, Direction.BACKWARD);
            robotCar.wait(2000);
            robotCar.stop();
            robotCar.wait(1000);
            robotCar.turn(100, Direction.FORWARD, Direction.LEFT);
            robotCar.wait(3000);
            robotCar.turn(100, Direction.BACKWARD, Direction.RIGHT);
            robotCar.wait(3000);
        }
        */
    }

}
