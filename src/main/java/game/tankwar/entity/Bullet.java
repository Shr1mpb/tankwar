package game.tankwar.entity;

import game.tankwar.setting.GameSetting;
import lombok.Data;

/**
 * 子弹类
 * 属性：横纵坐标
 * 方向（与坦克发射时保持一致）
 * 速度
 * 是否存活
 */
@Data
public class Bullet implements Runnable {
    private int x;
    private int y;
    private Tank.Direction direction;
    private int moveFactor = 15;
    private boolean isLive = true;
    private GameSetting setting = GameSetting.getInstance();

    public Bullet(int x,int y,Tank.Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * 射击 开新的线程
     */
    @Override
    public void run() {

        while (true) {
            //子弹休眠 否则会导致瞬移
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //子弹移动 （GamePanel负责不停地重绘）
            switch (direction) {
                case UP -> {
                    for(int i = 0;i <moveFactor;i++) moveUp();
                }
                case DOWN -> {
                    for(int i = 0;i <moveFactor;i++) moveDown();
                }
                case LEFT -> {
                    for(int i = 0;i <moveFactor;i++) moveLeft();
                }
                case RIGHT -> {
                    for(int i = 0;i <moveFactor;i++) moveRight();
                }
            }

            //子弹销毁
            if (x <= 0 || x >= setting.getWidth() || y <= 0 || y >= setting.getHeight()) {
                isLive = false;
                break;
            }

        }

    }

    public void moveUp(){
        y--;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }
}
