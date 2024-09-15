package game.tankwar.ui;

import game.tankwar.entity.Tank;
import game.tankwar.entity.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * 继承JPanel 作为画板
 * 实现KeyListener 监听键盘事件
 */
public class GamePanel extends JPanel implements KeyListener, MouseListener {
    private Graphics g;
    private final Hero hero;

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
        this.hero = new Hero(100,100);
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
        g.fillRect(0,0,1280,960);

        drawTank(hero,hero.getDirection(),Tank.Type.SELF);



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
     * 这里处理坦克移动
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0;i <hero.getMoveFactor();i++) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> {
                    hero.setDirection(Tank.Direction.UP);
                    hero.moveUp();
                }
                case KeyEvent.VK_A -> {
                    hero.setDirection(Tank.Direction.LEFT);
                    hero.moveLeft();
                }
                case KeyEvent.VK_S -> {
                    hero.setDirection(Tank.Direction.DOWN);
                    hero.moveDown();
                }
                case KeyEvent.VK_D -> {
                    hero.setDirection(Tank.Direction.RIGHT);
                    hero.moveRight();
                }
            }
            this.repaint();
        }
    }

    /**
     * 某个键被松开时
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

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
    public void drawTank(Tank tank, Tank.Direction direction, Tank.Type type){

        //根据坦克类型设置颜色
        if (Objects.requireNonNull(type) == Tank.Type.SELF) {
            g.setColor(Color.ORANGE);
        }else {
            g.setColor(Color.lightGray);
        }
        //根据方向绘制坦克
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
}
