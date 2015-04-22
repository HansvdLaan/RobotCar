import com.pi4j.io.gpio.*;

import static com.pi4j.wiringpi.SoftPwm.softPwmCreate;
import static com.pi4j.wiringpi.SoftPwm.softPwmWrite;

/**
 * Created by User on 20-4-2015.
 */
public class MotorControl {
    final GpioController gpio;
    final GpioPinDigitalOutput DirectionPinMotorA;
    final GpioPinDigitalOutput DirectionPinMotorB;
    final GpioPinDigitalOutput DirectionPinMotorC;
    final GpioPinDigitalOutput DirectionPinMotorD;

    public void setMotor(Motor motor, Speed speed,Direction direction) {
        if(direction == Direction.FORWARDS) {
            softPwmWrite(motor.getPinSettings().getSpeedPin().getAddress(), speed.getSpeed());
            DirectionPinMotorA.high();
        }
        else {
            softPwmWrite(motor.getPinSettings().getSpeedPin().getAddress(), speed.getSpeed());
            DirectionPinMotorA.low();
        }
    }

    public MotorControl() {
        this.gpio = GpioFactory.getInstance();

        /*
            Creating new instances of PWM output pins on the motor control speed pins.
            The pins that connect to the motors are defined in PinSettings
         */
        softPwmCreate(PinSettings.MOTOR_A.getSpeedPin().getAddress(),0,100);
        softPwmCreate(PinSettings.MOTOR_B.getSpeedPin().getAddress(),0,100);
        softPwmCreate(PinSettings.MOTOR_C.getSpeedPin().getAddress(),0,100);
        softPwmCreate(PinSettings.MOTOR_D.getSpeedPin().getAddress(),0,100);

        /*
            Creating new instances of Digital output pins on the motor control direction pins.
            The pins that connect to the motors are defined in PinSettings
         */
        DirectionPinMotorA =  gpio.provisionDigitalOutputPin(PinSettings.MOTOR_A.getDirectionPin(),
                "Motor A Direction pin", PinState.HIGH);
        DirectionPinMotorB =  gpio.provisionDigitalOutputPin(PinSettings.MOTOR_A.getDirectionPin(),
                "Motor B Direction pin", PinState.HIGH);
        DirectionPinMotorC =  gpio.provisionDigitalOutputPin(PinSettings.MOTOR_A.getDirectionPin(),
                "Motor C Direction pin", PinState.HIGH);
        DirectionPinMotorD =  gpio.provisionDigitalOutputPin(PinSettings.MOTOR_A.getDirectionPin(),
                "Motor D Direction pin", PinState.HIGH);
    }
}
