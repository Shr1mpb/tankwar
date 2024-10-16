package game.tankwar.ui;

import game.tankwar.constant.ExceptionInfo;
import game.tankwar.constant.GameInfo;
import game.tankwar.constant.GameMessage;
import game.tankwar.exception.ScreenException;
import game.tankwar.setting.GameSetting;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TankGame {
    /**
     * 创建并启动游戏面板
     * 启动并输出游戏名、版本、作者的信息
     *
     * 线程记录：
     *      GamePanel       不断重绘
     *      PanelContainer  检测KeyStatus并做出操作
     *      Bullet          创建子弹线程 子弹移动
     *
     */
    public void startGame() {
        //设置游戏
        GameSetting gameSetting = GameSetting.getInstance();
        gameSetting.setEnemyTankSize(2);
        gameSetting.setLevel(1);
        gameSetting.setWidth(1280);
        gameSetting.setHeight(960);
        gameSetting.setMaxBulletCount(3);
        gameSetting.setMaxEnemyBulletCount(1);
        gameSetting.setShotLag(1000 * 1000000);//此处前方数字单位为微秒
        gameSetting.setEnemyShotLag(2000 * 1000000 );
        if (gameSetting.getWidth() < 800 || gameSetting.getHeight() < 600) {
            throw new ScreenException(ExceptionInfo.SCREEN_EXCEPTION);
        }
        //创建游戏面板
        PanelContainer pc = new PanelContainer();
        pc.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println(GameMessage.START);
            }

            //由于设置关闭窗口时直接退出 下面的windowClosed将不再有用
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(GameMessage.CLOSE);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        //运行检测按键状态的线程
        new Thread(pc).start();

        //输出游戏信息
        System.out.println("\n【" +GameInfo.GAME_NAME + "】 \nVersion:\t" + GameInfo.VERSION +
                                                          "\nAuthor:\t"+ GameInfo.AUTHOR + "\n\n\n");

    }
}
