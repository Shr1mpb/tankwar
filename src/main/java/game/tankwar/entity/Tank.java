package game.tankwar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 坦克基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tank {
    private int x_location;
    private int y_location;
    private Tank.Direction direction = Direction.UP;//方向，默认为向上
    private int moveFactor = 3;//移动因子 在键盘监听时一次执行的移动次数


    public Tank(int x,int y){
        this.x_location = x;
        this.y_location = y;
    }

    public Tank(int x, int y, Tank.Direction direction) {
        this.x_location = x;
        this.y_location = y;
        this.direction = direction;
    }
    public Tank(int x, int y, int moveFactor) {
        this.x_location = x;
        this.y_location = y;
        this.moveFactor = moveFactor;
    }

    /**
     * 坦克类型
     */
    public static enum Type {
        ENEMY,SELF
    }

    /**
     * 坦克方向
     */
    public static enum Direction {
        UP,DOWN,LEFT,RIGHT
    }

    /**
     * 修改坦克坐标
     * @param x x坐标
     * @param y y坐标
     */
    public void modifyLocation(int x, int y) {
        this.x_location = x;
        this.y_location = y;
    }
    /**
     * 坦克移动
     * 四个方位 四个方法
     */
    public void moveUp() {
        this.y_location -= 1;
    }
    public void moveDown() {
        this.y_location += 1;
    }
    public void moveLeft() {
        this.x_location -= 1;
    }
    public void moveRight() {
        this.x_location += 1;
    }
}
