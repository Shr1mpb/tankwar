package game.tankwar.ui;

import game.tankwar.entity.Bullet;
import game.tankwar.entity.EnemyTank;
import game.tankwar.entity.Tank;
import game.tankwar.entity.Hero;
import game.tankwar.setting.GameSetting;
import game.tankwar.ui.input.KeyStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Vector;

/**
 * 继承JPanel 作为画板
 * 实现KeyListener 监听键盘事件
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GamePanel extends JPanel implements KeyListener, MouseListener,Runnable {
    private Graphics g;
    private final Hero hero;
    private final GameSetting setting = GameSetting.getInstance();
    //敌人坦克相关 放入Vector集合中(线程安全)
    private final Vector<EnemyTank> enemyTanks = new Vector<>();
    private final KeyStatus keyStatus = KeyStatus.getInstance();

    /*
        继承JFrame的类是画框
        Panel为面板、画板
        Graphics(图画、画图)是画笔

        画框JFrame 构造器中调用this.add方法添加画板 new会创建窗口
           ->面板 本类 ， 重写paint方法
                ->画笔    本类中的paint方法的形参， paint方法会绘制图形
     */

    /**
     * 构造器用于初始化面板上的坦克
     * 直接把现有的坦克都初始化
     */
    public GamePanel(){
        this.hero = new Hero(100,500);
        for (int i = 0; i < setting.getEnemyTankSize(); i++) {
            enemyTanks.add(new EnemyTank(100*(i+2),50));
        }
    }


    /**
     * 此方法(paint)用于在画板上绘制图形
     * 使用 g 提供的方法可以进行绘制 调用父类方法完成初始化 super.paint(g)
     *   调用：
     *      1.在第一次加载时会被自动调用
     *      2.在窗口最小化后恢复时被调用
     *      3.窗口大小发生变化时被调用
     *      4.repaint方法时被调用
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);//初始化
        this.g = g;//传入画笔 供其他方法使用
        //填充游戏区域为黑色
        g.fillRect(0,0,setting.getWidth(),setting.getHeight());
        //画出自己的坦克
        drawTank(hero,Tank.Type.SELF);
        //画出敌人的坦克
        for (EnemyTank enemyTank : enemyTanks) {
            drawTank(enemyTank,Tank.Type.ENEMY);
        }
        //画出自己坦克的子弹
        Vector<Bullet> bullets = hero.getBullets();
        for (Bullet bullet : bullets) {
            if (bullet.isLive()) {
                g.fill3DRect(bullet.getX(), bullet.getY(), 3, 3, false);
            }
        }
        //画出敌人坦克的子弹
        //自己坦克的子弹通过空格可以发射 然后isAlive设置为true
        //敌人坦克的子弹需要手动触发射击
        for (EnemyTank enemyTank : enemyTanks) {
            Vector<Bullet> enemyTankBullets = enemyTank.getBullets();
            for (Bullet enemyBullet : enemyTankBullets) {
                if (enemyBullet.isLive()) {
                    g.fill3DRect(enemyBullet.getX(), enemyBullet.getY(), 3, 3, false);
                }
            }
        }
        for (EnemyTank enemyTank : enemyTanks) {//射击
            enemyTank.shotOppositeTank();
        }



    }


    /**
     * 有字符输出时被监听
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 某个键被按下时
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                keyStatus.setW(true);
            }
            case KeyEvent.VK_A -> {
                keyStatus.setA(true);
            }
            case KeyEvent.VK_S -> {
                keyStatus.setS(true);
            }
            case KeyEvent.VK_D -> {
                keyStatus.setD(true);
            }
            case KeyEvent.VK_SPACE -> {
                keyStatus.setSpace(true);
            }
        }

    }

    /**
     * 某个键被松开时
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                keyStatus.setW(false);
            }
            case KeyEvent.VK_A -> {
                keyStatus.setA(false);
            }
            case KeyEvent.VK_S -> {
                keyStatus.setS(false);
            }
            case KeyEvent.VK_D -> {
                keyStatus.setD(false);
            }
            case KeyEvent.VK_SPACE -> {
                keyStatus.setSpace(false);
            }
        }
    }



    /*
        以下是鼠标监听
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * 绘制坦克的方法
     * tank 用于获取x,y 代表坦克左上角，为坦克的坐标
     * g为画笔     direction为方向  上下左右
     * type为坦克的类型(Tank$TankType)
     */
    public void drawTank(Tank tank, Tank.Type type){
        //根据坦克类型设置颜色
        if (Objects.requireNonNull(type) == Tank.Type.SELF) {
            g.setColor(Color.ORANGE);
        }else {
            g.setColor(Color.lightGray);
        }
        //根据方向绘制坦克
        Tank.Direction direction = tank.getDirection();
        switch (direction){
            case UP -> {
                //左右履带
                g.fill3DRect(tank.getX_location(),tank.getY_location(),5,30,false);
                g.fill3DRect(tank.getX_location() + 15, tank.getY_location(), 5,30,false);
                //中间部分
                g.fill3DRect(tank.getX_location()+5,tank.getY_location()+5,10,20,false);
                g.fillOval(tank.getX_location()+5,tank.getY_location()+10,10,10);
                g.drawLine(tank.getX_location() + 10, tank.getY_location() + 15, tank.getX_location() + 10, tank.getY_location());

            }
            case DOWN -> {
                //左右履带
                g.fill3DRect(tank.getX_location(),tank.getY_location(),5,30,false);
                g.fill3DRect(tank.getX_location() + 15, tank.getY_location(), 5,30,false);
                //中间部分
                g.fill3DRect(tank.getX_location()+5,tank.getY_location()+5,10,20,false);
                g.fillOval(tank.getX_location()+5,tank.getY_location()+10,10,10);
                g.drawLine(tank.getX_location() + 10, tank.getY_location() + 15, tank.getX_location() + 10, tank.getY_location()+30);
            }
            case LEFT -> {
                //左右履带
                g.fill3DRect(tank.getX_location(),tank.getY_location(),30,5,false);
                g.fill3DRect(tank.getX_location(), tank.getY_location()+15, 30,5,false);
                //中间部分
                g.fill3DRect(tank.getX_location()+5,tank.getY_location()+5,20,10,false);
                g.fillOval(tank.getX_location()+10,tank.getY_location()+5,10,10);
                g.drawLine(tank.getX_location() + 15, tank.getY_location() + 10, tank.getX_location() , tank.getY_location()+10);
            }
            case RIGHT -> {
                //左右履带
                g.fill3DRect(tank.getX_location(),tank.getY_location(),30,5,false);
                g.fill3DRect(tank.getX_location(), tank.getY_location()+15, 30,5,false);
                //中间部分
                g.fill3DRect(tank.getX_location()+5,tank.getY_location()+5,20,10,false);
                g.fillOval(tank.getX_location()+10,tank.getY_location()+5,10,10);
                g.drawLine(tank.getX_location() + 15, tank.getY_location() + 10, tank.getX_location() + 30, tank.getY_location()+10);
            }

        }

    }

    /**
     * 该方法用于不停地重绘 以显示子弹 并提高坦克帧率
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}
