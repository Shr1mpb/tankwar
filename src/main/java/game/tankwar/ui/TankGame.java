package game.tankwar.ui;

import game.tankwar.constant.ExceptionInfo;
import game.tankwar.constant.GameInfo;
import game.tankwar.exception.ScreenException;
import game.tankwar.setting.GameSetting;

public class TankGame {
    /**
     * 创建并启动游戏面板
     * 启动并输出游戏名、版本、作者的信息
     */
    public void startGame() {
        //设置游戏
        GameSetting gameSetting = GameSetting.getInstance();
        gameSetting.setEnemyTankSize(2);
        gameSetting.setLevel(1);
        gameSetting.setWidth(1280);
        gameSetting.setHeight(960);
        if (gameSetting.getWidth() < 800 || gameSetting.getHeight() < 600) {
            throw new ScreenException(ExceptionInfo.SCREEN_EXCEPTION);
        }
        //创建游戏面板
        new PanelContainer();
        //输出游戏信息
        System.out.println("\n【" +GameInfo.GAME_NAME + "】 \nVersion:\t" + GameInfo.VERSION +
                                                          "\nAuthor:\t"+ GameInfo.AUTHOR + "\n\n\n");

    }
}
