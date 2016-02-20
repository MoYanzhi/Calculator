import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by ht on 2016/2/14.
 * 这是计算器的主界面，程序从这里开始启动。
 * 计算器的运算流程为：输入一个值→触发运算符 清空stringbuffer中的东西，设置result的值，判断compute是否为空，不为空则计算相应运算后清空compute，重复这流程。
 */
public class MainPanel extends JFrame {

    private ResultPanel resultPanel = new ResultPanel();
    private CountPanel countPanel = new CountPanel(resultPanel);


    public MainPanel() throws HeadlessException {

        this.setLayout(new BorderLayout());
        JPanel pl = new JPanel(new GridLayout(1, 0));
        pl.add(countPanel.getNumPanel());
        JPanel pr = new JPanel(new GridLayout(1, 0));
        pr.add(countPanel.getCountPanel());
        JPanel p = new JPanel(new BorderLayout());
        p.add(pl,BorderLayout.WEST);
        p.add(countPanel.getFunPanel(), BorderLayout.NORTH);
        p.add(pr,BorderLayout.CENTER);

        this.getContentPane().add(resultPanel.getResultField(),BorderLayout.CENTER);
        this.getContentPane().add(p, BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws IOException {
        //获取当前屏幕的长和宽
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        RandomAccessFile raf = new RandomAccessFile("countFile.w", "rw");
        int count = 0;
        long point = raf.length();
        if (point != 0) {
            count = raf.readInt();
            raf.seek(0);
            raf.writeInt(++count);
        } else {
            raf.writeInt(1);
        }
        raf.close();

        MainPanel mainPanel = new MainPanel();
        mainPanel.pack();
        mainPanel.setResizable(false);
        mainPanel.setLocation(screenWidth / 2 - mainPanel.getWidth() / 2, screenHeight / 2 - mainPanel.getHeight() / 2);
        mainPanel.setTitle("Calculator is started " + count + " times");
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setVisible(true);
    }
}
