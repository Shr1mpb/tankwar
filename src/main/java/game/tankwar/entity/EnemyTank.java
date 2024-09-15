package game.tankwar.entity;


public class EnemyTank extends Tank {
    {
        setDirection(Direction.DOWN);//敌人坦克默认的方向是向下
    }
    public EnemyTank(int x, int y){
        super(x,y);
    }

    public EnemyTank(int x, int y, Direction direction) {
        super(x,y,direction);
    }
    public EnemyTank(int x, int y, Direction direction,int moveFactor) {
        super(x,y,direction,moveFactor);
    }

    public EnemyTank(int x, int y, int moveFactor) {
        super(x, y,moveFactor);
    }
}
