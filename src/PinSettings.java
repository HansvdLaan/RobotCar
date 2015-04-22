import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;


/**
 * Enum with the pin-settings in their respective fields.
 * Left argument is the pin controlling the speed with a PWM signal connecting to IA.
 * Right argument is the pin controlling the direction with a boolean signal connecting to IB.
 */
public enum PinSettings {

    MOTOR_A     (RaspiPin.GPIO_01, RaspiPin.GPIO_02),
    MOTOR_B     (RaspiPin.GPIO_03, RaspiPin.GPIO_04),
    MOTOR_C     (RaspiPin.GPIO_05, RaspiPin.GPIO_06),
    MOTOR_D     (RaspiPin.GPIO_07, RaspiPin.GPIO_08);

    /**
     * GPIO pin connection to the PWM signal pin on the HG7881 Board (pin IA)
     */
    private final Pin speedPin;

    /**
     * GPIO pin connection to the direction pin on the HG7881 Board (pin IB)
     */
    private final Pin directionPin;

    PinSettings(Pin speedPin, Pin directionPin) {
        this.speedPin = speedPin;
        this.directionPin = directionPin;
    }

    public Pin getSpeedPin() {
        return this.speedPin;
    }

    public Pin getDirectionPin() {
        return this.directionPin;
    }
}
