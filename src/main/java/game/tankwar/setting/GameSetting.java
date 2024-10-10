package game.tankwar.setting;

import lombok.Data;

/**
 * 游戏设置
 * 这里使用 饿汉式单例 来完成游戏设置
 */
@Data
public class GameSetting {
    private static GameSetting gameSetting = new GameSetting();
    private GameSetting(){}
    public static GameSetting getInstance() {
        return gameSetting;
    }
    /*
        下面是设置
     */
    private int enemyTankSize;
    private int level;
    private int remainingTankNum;
    private int width;//分辨率长
    private int height;//分辨率宽
    private int maxBulletCount;//我方坦克最多子弹数
    private int maxEnemyBulletCount;//敌人坦克最多子弹数
    private long shotLag;//射击延迟(10^-9秒)
    private long enemyShotLag;//敌方射击延迟
}
