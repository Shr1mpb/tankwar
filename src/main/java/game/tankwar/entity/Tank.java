package game.tankwar.entity;

import game.tankwar.setting.GameSetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Vector;

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
    private int moveFactor = 2;//移动因子 在键盘监听时一次执行的移动次数
    private Vector<Bullet> bullets;

    {
        //代码块 用于初始化子弹 分敌人和自己
        bullets = new Vector<>((this.getClass() == Hero.class)? GameSetting.getInstance().getMaxBulletCount() : GameSetting.getInstance().getMaxEnemyBulletCount());
        for (int i = 0; i < bullets.capacity(); i++) {
            bullets.add(new Bullet());
        }

    }
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

    public Tank(int x, int y, Direction direction, int moveFactor) {
        this.x_location = x;
        this.y_location = y;
        this.direction = direction;
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

    public void move(Direction direction) {
            this.setDirection(direction);
            switch (direction) {
                case UP -> {
                    for (int i = 0; i < moveFactor; i++) moveUp();
                }
                case DOWN -> {
                    for (int i = 0; i < moveFactor; i++) moveDown();
                }
                case LEFT -> {
                    for (int i = 0; i < moveFactor; i++) moveLeft();
                }
                case RIGHT -> {
                    for (int i = 0; i < moveFactor; i++) moveRight();
                }
            }
        }

    /**
     * 坦克射击
     */
    public synchronized void shotOppositeTank() {
        //射击子弹 如果该子弹非存活并且子弹的射击在间隔允许范围内 则射击一次
        for (Bullet bullet : bullets) {
            if (!(bullet.isLive())) {//找出死掉的子弹 如果该子弹可以射击 则射击

                //子弹射击间隔合法性
                for (Bullet bullet1 : bullets) {
                    if (bullet1.isLive()) {//只看场上已有的子弹 如果射击延迟有一个不满足 则直接结束射击
                        if (
                                !(LocalDateTime.now().     isAfter      (bullet1.getShotTime().plusNanos(
                                this.getClass() == Hero.class? (GameSetting.getInstance().getShotLag()):(GameSetting.getInstance().getEnemyShotLag())
                                    ))
                                )
                        ) {
                            return;
                        }
                    }
                }

                //激活子弹并开始运作
                switch (direction) {
                    case UP -> {
                        bullet.setAttribute(getX_location() + 10, getY_location(), direction);
                    }
                    case RIGHT -> {
                        bullet.setAttribute(getX_location() + 30, getY_location() + 10, direction);
                    }
                    case DOWN -> {
                        bullet.setAttribute(getX_location() + 10, getY_location() + 30, direction);
                    }
                    case LEFT -> {
                        bullet.setAttribute(getX_location(), getY_location() + 20, direction);
                    }
                }
                new Thread(bullet).start();
                return;//射击成功一次就结束
            }
        }

    }
}
