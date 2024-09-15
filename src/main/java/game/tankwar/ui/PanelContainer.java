package game.tankwar.ui;

import javax.swing.*;

@SuppressWarnings({"all"})
public class PanelContainer extends JFrame {
    private GamePanel mp;
    public PanelContainer() {
        mp = new GamePanel();
        //添加画板
        this.add(mp);
        //添加监听器(通过画板实现)
        this.addKeyListener(mp);
        this.addMouseListener(mp);
        //分辨率
        this.setSize(1280, 960);
        //可见
        this.setVisible(true);
        //关闭后释放资源 让JVM会结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

