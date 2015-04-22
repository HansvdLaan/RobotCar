/**
 * Created by User on 20-4-2015.
 */
public enum Motor {

    /**
     * This setting represents
     * the front left motor.
     */
    FRONT_LEFT  (PinSettings.MOTOR_A),

    /**
     * This setting represents
     * the front right motor.
     */
    FRONT_RIGHT (PinSettings.MOTOR_B),

    /**
     * This setting represents
     * the back left motor.
     */
    BACK_LEFT   (PinSettings.MOTOR_C),

    /**
     * This setting represents
     * the back right motor.
     */
    BACK_RIGHT  (PinSettings.MOTOR_D);

    private final PinSettings pinSettings;

    Motor (PinSettings pinSettings) {
        this.pinSettings = pinSettings;
    }

    public PinSettings getPinSettings() {
        return this.getPinSettings();
    }
}
