package game.tankwar.ui;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    //调用父类方法完成初始化
    /*
        继承JFrame的类是画框
        Panel为面板、画板
        Graphics(图画、画图)是画笔

        画框JFrame 构造器中调用this.add方法添加画板 new会创建窗口
           ->面板 本类 ， 重写paint方法
                ->画笔    本类中的paint方法的形参， paint方法会绘制图形
     */

    /**
     * 此方法(paint)用于在画板上绘制图形
     * 使用 g 提供的方法可以进行绘制
     *   调用：
     *      1.在第一次加载时会被自动调用
     *      2.在窗口最小化后恢复时被调用
     *      3.窗口大小发生变化时被调用
     *      4.repaint方法时被调用
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        /*
            测试图片
         */
//        try {
//            //resources会被添加到类路径中，因此可以找到
//            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/picture/test.jpg"));
//            g.drawImage(image, 10, 10, this);
//        } catch (Exception e) {
//            System.out.println("读取图片时出错！");
//        }
        /*
            测试字体和字符串
         */
//        g.setColor(Color.black) ;
//        g.setFont(new Font("隶书", Font.BOLD, 50));
//        g.drawString("啊米诺斯",100,100);//这里的坐标是！！！字符串的左下角




    }


}
