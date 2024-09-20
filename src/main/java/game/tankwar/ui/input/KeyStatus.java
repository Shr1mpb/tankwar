package game.tankwar.ui.input;

import game.tankwar.entity.Hero;
import game.tankwar.ui.GamePanel;
import lombok.Data;

@Data
public class KeyStatus {
    private static KeyStatus keyStatus = new KeyStatus();
    private KeyStatus(){}

    public static KeyStatus getInstance() {
        return keyStatus;
    }


    private boolean w;
    private boolean a;
    private boolean s;
    private boolean d;
    private boolean space;


}
