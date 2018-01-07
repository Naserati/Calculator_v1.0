package nazarov.calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator {

    public static void main(String[] args) {
        CalcFrame frame = new CalcFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
    }
}
class CalcFrame extends JFrame{
    public CalcFrame(){
        setTitle("Калькулятор");
        CalcPanel panel = new CalcPanel();
        add(panel);
    }
}
class CalcPanel extends  JPanel{
    Font BigFont = new Font("TimesRoman", Font.BOLD, 20);
    JButton display;
    JPanel panel;
    int result = 0;
    String lastCommand = "=";

    private boolean start = true;

    public CalcPanel(){
        setLayout(new BorderLayout());
        display = new JButton();
        display.setEnabled(false);
        display.setPreferredSize(new Dimension(300, 80));
        add(display, BorderLayout.NORTH);
        ActionListener in = new InListener();
        ActionListener com = new ComListener();

        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));

        addButton("7", in);
        addButton("8", in);
        addButton("9", in);
        addButton("/", com);

        addButton("4", in);
        addButton("5", in);
        addButton("6", in);
        addButton("*", com);

        addButton("1", in);
        addButton("2", in);
        addButton("3", in);
        addButton("-", com);

        addButton("C", com);
        addButton("0", in);
        addButton("=", com);
        addButton("+", com);

        add(panel, BorderLayout.CENTER);

    }
    private void addButton(String name, ActionListener listener){
        JButton button = new JButton(name);
        button.setFont(BigFont);
        button.addActionListener(listener);
        panel.add(button);
    }
    private class InListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(start){
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + command);
        }
    }
    private class ComListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(start){
                lastCommand = command;
                 }
            else {
                calculate(Integer.parseInt(display.getText()));
                lastCommand = command;
                start = true;
            }
            if (command.equals("C")) {
                display.setText(null);
                lastCommand = "=";
            }
            }
        }
        private void calculate (int x){
            try {

                switch (lastCommand) {
                    case "+":
                        result += x;
                        break;
                    case "-":
                        result -= x;
                        break;
                    case "*":
                        result *= x;
                        break;
                    case "/":
                        result /= x;
                        break;
                    case "=":
                        result = x;
                        break;
                }
            } catch (ArithmeticException ar) {
                JOptionPane.showMessageDialog(null, "На ноль делить нельзя!");
            }
            display.setText("" + result);
        }
    }