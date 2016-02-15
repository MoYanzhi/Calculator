import javax.swing.*;
import java.awt.event.*;

/**
 * Created by ht on 2016/2/14.
 * 这是计算器的显示界面，目前设置为单行显示
 */
public class ResultPanel implements ActionListener {
    //    private JTextArea resultArea;
    //JTextArea 无法右对齐，暂时采用TextField

    private JTextField resultField;

    public ResultPanel() {

        resultField = new JTextField("0");
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        resultField.setEditable(false);
        resultField.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public JTextField getResultField() {
        return resultField;
    }
}
