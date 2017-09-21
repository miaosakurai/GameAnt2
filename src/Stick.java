import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joy12 on 2017/9/19.
 */
public class Stick {
    private Integer length;

    public Stick(Integer length) {
        this.length = length;
    }

    public boolean isOnStick(Ant ant) {
        return (ant.getPosition() < length && ant.getPosition() > 0);
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
