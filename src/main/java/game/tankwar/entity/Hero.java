package game.tankwar.entity;

/**
 * 坦克类型A
 */
public class Hero extends Tank {
    public Hero(int x, int y){
        super(x,y);
    }

    public Hero(int x, int y, Direction direction) {
        super(x,y,direction);
    }
    public Hero(int x, int y, Direction direction,int moveFactor) {
        super(x,y,direction,moveFactor);
    }

    public Hero(int x, int y, int moveFactor) {
        super(x, y,moveFactor);
    }


}
