/**
 * Created by User on 20-4-2015.
 */
public enum Speed {
    BACKWARD_FAST   (100),
    BACKWARD_SLOW   (50),
    STOP            (0),
    FORWARD_SLOW    (50),
    FORWARD_FAST    (100);

    private final int speed;

    Speed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }
}
