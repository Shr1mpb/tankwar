package game.tankwar.ui;

import game.tankwar.constant.GameInfo;

public class TankGame {
    /**
     * 创建并启动游戏面板
     * 启动并输出游戏名、版本、作者的信息
     */
    public void startGame() {
        //创建游戏面板
        new PanelContainer();
        //输出游戏信息
        System.out.println("\n【" +GameInfo.gameName + "】 \nVersion:\t" + GameInfo.version +
                                                          "\nAuthor:\t"+ GameInfo.author + "\n\n\n");

    }
}
