package game.tankwar.setting;

import lombok.Data;

/**
 * 游戏设置
 * 这里使用 懒汉式单例 来完成游戏设置
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
}
