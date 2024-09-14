package game.tankwar;

import game.tankwar.ui.TankGame;

public class Starter {
    public static void main(String[] args) throws InterruptedException {
        //启动游戏
        TankGame tankGame = new TankGame();
        tankGame.startGame();

    }
}
