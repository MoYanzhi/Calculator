import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by ht on 2016/2/14.
 * 这是一个简易的计算器的键盘界面类，能够实现基本的加减乘除功能。
 * 本界面处理常规的数字界面外，设置了四个功能键，能够实现清零、转换分数（没做）、记忆最近一次的结果和删除字符功能。
 * 处理计算时，可以连续计算
 */
public class CountPanel extends JPanel implements ActionListener {

    /*设置各个按钮
     */
    private JButton[] funButtons = new JButton[4];
    private JButton[] numButtons = new JButton[12];
    private JButton[] countButtons = new JButton[4];

    private JPanel funPanel;
    private JPanel numPanel;
    private JPanel countPanel;

    private double result;
    private double reResult;

    private String compute = null;

    private int capicaty = 0;
    private StringBuffer stringBuffer = new StringBuffer(capicaty);

    private ResultPanel resultPanel;

    public CountPanel(ResultPanel resultPanel) {

        this.resultPanel = resultPanel;

        funPanel = new JPanel(new GridLayout(1, 4));
        numPanel = new JPanel(new GridLayout(4, 3));
        countPanel = new JPanel(new GridLayout(0, 1));
        funButtons[0] = new JButton("Ac"); //归零
        funButtons[1] = new JButton("Cv"); //转换成分数
        funButtons[2] = new JButton("Re"); //记忆结果
        funButtons[3] = new JButton("←");  //删除
        for (int i = 0; i < funButtons.length; i++) {
            funPanel.add(funButtons[i]);
            funButtons[i].addActionListener(this);
        }

        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
        }
        numButtons[10] = new JButton(".");
        numButtons[11] = new JButton("=");
        for (int i = 7; i < 10; i++) {
            numPanel.add(numButtons[i]);
        }
        for (int i = 4; i < 7; i++) {
            numPanel.add(numButtons[i]);
        }
        for (int i = 1; i < 4; i++) {
            numPanel.add(numButtons[i]);
        }
        numPanel.add(numButtons[0]);
        numPanel.add(numButtons[10]);
        numPanel.add(numButtons[11]);
        for (int i = 0; i < numButtons.length; i++) {
            numButtons[i].addActionListener(this);
        }

        countButtons[0] = new JButton("+");
        countButtons[1] = new JButton("-");
        countButtons[2] = new JButton("*");
        countButtons[3] = new JButton("÷");
        for (int i = 0; i < countButtons.length; i++) {
            countPanel.add(countButtons[i]);
            countButtons[i].addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = resultPanel.getResultField();
        if (stringBuffer.toString().startsWith(".")) {
            stringBuffer.insert(0, "0");
        }

        funAction(e, textField);

        countAction(e, textField);

        computeAction(e, textField);

    }

    /**
     * 监听计算按钮的方法
     *
     * @param e
     * @param textField 显示界面
     */
    private void computeAction(ActionEvent e, JTextField textField) {
        switch (e.getActionCommand()) {
            case "+":
                if (capicaty > 0) {
                    if (compute == null) {
                        result = Double.parseDouble(stringBuffer.toString());
                        textField.setText(String.valueOf(result));
                        capicaty = 0;
                        stringBuffer = new StringBuffer(capicaty);
                        compute = "+";
                    } else {
                        result = computeCase(compute);
                    }
                    textField.setText(String.valueOf(result));
                    capicaty = 0;
                    stringBuffer = new StringBuffer(capicaty);
                    compute = "+";
                } else compute = "+";
                break;
            case "-":
                if (capicaty > 0) {
                    if (compute == null) {
                        result = Double.parseDouble(stringBuffer.toString());
                        textField.setText(String.valueOf(result));
                        capicaty = 0;
                        stringBuffer = new StringBuffer(capicaty);
                        compute = "-";
                    } else {
                        result = computeCase(compute);
                    }
                    textField.setText(String.valueOf(result));
                    capicaty = 0;
                    stringBuffer = new StringBuffer(capicaty);
                    compute = "-";
                } else if (!textField.getText().equals("0")) {
                    compute = "-";
                }
                break;
            case "*":
                if (capicaty > 0) {
                    if (compute == null) {
                        result = Double.parseDouble(stringBuffer.toString());
                        textField.setText(String.valueOf(result));
                        capicaty = 0;
                        stringBuffer = new StringBuffer(capicaty);
                        compute = "*";
                    } else {
                        result = computeCase(compute);
                    }
                    textField.setText(String.valueOf(result));
                    capicaty = 0;
                    stringBuffer = new StringBuffer(capicaty);
                    compute = "*";
                } else if (!textField.getText().equals("0")) {
                    compute = "*";
                }
                break;
            case "÷":
                if (capicaty > 0) {
                    if (compute == null) {
                        result = Double.parseDouble(stringBuffer.toString());
                        textField.setText(String.valueOf(result));
                        capicaty = 0;
                        stringBuffer = new StringBuffer(capicaty);
                        compute = "÷";
                    } else {
                        result = computeCase(compute);
                    }
                    textField.setText(String.valueOf(result));
                    capicaty = 0;
                    stringBuffer = new StringBuffer(capicaty);
                    compute = "÷";
                } else if (!textField.getText().equals("0")) {
                    compute = "÷";
                }
                break;
            case "=":
                if (capicaty > 0) {
                    if (compute != null) {
                        result = computeCase(compute);
                        textField.setText(String.valueOf(result));
                        compute = null;
                        capicaty = 0;
                        stringBuffer = new StringBuffer(capicaty);
                    } else {
                        result = Double.parseDouble(textField.getText());
                        textField.setText(String.valueOf(result));
                        reResult = result;
                    }
                } else {
                    textField.setText(String.valueOf(result));
                    reResult = result;
                    compute = null;
                }
        }

    }

    /**
     * 计算方法的辅助方法，用于处理加减乘除方法中相同的代码
     * @param compute
     * @return
     */
    private double computeCase(String compute) {
        switch (compute) {
            case "+":
                result += Double.parseDouble(stringBuffer.toString());
                break;
            case "-":
                result -= Double.parseDouble(stringBuffer.toString());
                break;
            case "*":
                result *= Double.parseDouble(stringBuffer.toString());
                break;
            case "÷":
                result /= Double.parseDouble(stringBuffer.toString());
                if (String.valueOf(result).equals("Infinity")) {
                    result = 0;
                }
                break;
        }

        return result;
    }

    /**
     * 处理数字按键的方法
     * @param e
     * @param textField
     */
    private void countAction(ActionEvent e, JTextField textField) {
        switch (e.getActionCommand()) {
            case "9":
                capicaty++;
                stringBuffer.append("9");
                textField.setText(stringBuffer.toString());
                break;
            case "8":
                capicaty++;
                stringBuffer.append("8");
                textField.setText(stringBuffer.toString());
                break;
            case "7":
                capicaty++;
                stringBuffer.append("7");
                textField.setText(stringBuffer.toString());
                break;
            case "6":
                capicaty++;
                stringBuffer.append("6");
                textField.setText(stringBuffer.toString());
                break;
            case "5":
                capicaty++;
                stringBuffer.append("5");
                textField.setText(stringBuffer.toString());
                break;
            case "4":
                capicaty++;
                stringBuffer.append("4");
                textField.setText(stringBuffer.toString());
                break;
            case "3":
                capicaty++;
                stringBuffer.append("3");
                textField.setText(stringBuffer.toString());
                break;
            case "2":
                capicaty++;
                stringBuffer.append("2");
                textField.setText(stringBuffer.toString());
                break;
            case "1":
                capicaty++;
                stringBuffer.append("1");
                textField.setText(stringBuffer.toString());
                break;
            case "0":
                capicaty++;
                stringBuffer.append("0");
                textField.setText(String.valueOf(Double.parseDouble(stringBuffer.toString())));
                break;
            case ".":
                boolean hasDot = false;
                for (int i = 0; i < stringBuffer.length(); i++) {
                    if (stringBuffer.charAt(i) == '.') {
                        hasDot = true;
                    }
                }
                if (!hasDot) {
                    capicaty++;
                    stringBuffer.append(".");
                    textField.setText(stringBuffer.toString());
                }
                break;
        }
    }


    /**
     * 处理功能按键的方法
     * @param e
     * @param textField
     */
    private void funAction(ActionEvent e, JTextField textField) {
        switch (e.getActionCommand()) {
            case "Ac":
                textField.setText("0");
                result = 0;
                capicaty = 0;
                stringBuffer = new StringBuffer(capicaty);
                compute = null;
                break;
            case "Cv":
                //todo
                break;
            case "Re":
                if (capicaty > 0 && compute != null) {
                    result = computeCase(compute);
                    reResult = result;
                    capicaty = 0;
                    stringBuffer = new StringBuffer(capicaty);
                    compute = null;
                } else {
                    reResult = result;
                    textField.setText(String.valueOf(reResult));
                }
                break;
            case "←":
                if (capicaty > 0) {
                    stringBuffer.deleteCharAt(--capicaty);
                    textField.setText(stringBuffer.toString());
                }
                break;
            default:
        }
    }


    public JPanel getFunPanel() {
        return funPanel;
    }

    public JPanel getNumPanel() {
        return numPanel;
    }

    public JPanel getCountPanel() {
        return countPanel;
    }
}
