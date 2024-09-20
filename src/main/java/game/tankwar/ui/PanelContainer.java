package game.tankwar.ui;

import game.tankwar.constant.GameInfo;
import game.tankwar.entity.Hero;
import game.tankwar.entity.Tank;
import game.tankwar.ui.input.KeyStatus;

import javax.swing.*;

@SuppressWarnings({"all"})
public class PanelContainer extends JFrame implements Runnable {
    private GamePanel mp;

    public PanelContainer() {
        mp = new GamePanel();
        //添加画板
        this.add(mp);
        //添加监听器(通过画板实现) 这里用于设置按键状态
        this.addKeyListener(mp);
        this.addMouseListener(mp);
        //分辨率
        this.setSize(1280, 960);
        //可见
        this.setVisible(true);
        //关闭后释放资源 让JVM会结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口名字
        this.setTitle(GameInfo.GAME_NAME + "\tVersion: " + GameInfo.VERSION);
        //不停地让Panel重绘(run方法) 以提高帧率并显示子弹轨迹
        new Thread(mp).start();
    }

    /**
     * 监听按键情况 进行对Hero坦克的操作
     */
    @Override
    public void run() {
        KeyStatus keyStatus = KeyStatus.getInstance();
        Hero hero = mp.getHero();
        while (true) {
            if (keyStatus.isW()) {
                hero.move(Tank.Direction.UP);
            }
            if (keyStatus.isA()) {
                hero.move(Tank.Direction.LEFT);
            }
            if (keyStatus.isS()) {
                hero.move(Tank.Direction.DOWN);
            }
            if (keyStatus.isD()) {
                hero.move(Tank.Direction.RIGHT);
            }
            if (keyStatus.isSpace()) {
                hero.shotOppositeTank();
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

