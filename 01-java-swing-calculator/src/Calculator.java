import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    // TODO: Basic requirements : Frames, fields, buttons, panel, operators and numbers
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] fnButtons = new JButton[8];
    JButton add, sub, mul, div, clr, dec, equ, del;
    JPanel panel;
    Font font = new Font("Ink Free", Font.BOLD, 30);
    double num1 = 0, num2 = 0, res = 0;
    char operator; // holds one of +,-,*,/

    // Initilization
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setResizable(false);

        // adding textfield
        textField = new JTextField();
        textField.setBounds(31, 25, 320, 50);
        textField.setFont(font);
        textField.setEditable(false); // can't edit manually, but by clicking buttons

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        clr = new JButton("Clear");
        equ = new JButton("=");
        dec = new JButton(".");
        del = new JButton("Delete");

        fnButtons[0] = add;
        fnButtons[1] = sub;
        fnButtons[2] = mul;
        fnButtons[3] = div;
        fnButtons[4] = clr;
        fnButtons[5] = equ;
        fnButtons[6] = dec;
        fnButtons[7] = del;

        for (int i = 0; i < 8; ++i) {
            fnButtons[i].addActionListener(this);
//            fnButtons[i].setFont(font);
            fnButtons[i].setFocusable(true);
        }

        for (int i = 0; i < 10; ++i) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
        }

        del.setBounds(40, 400, 150, 50);
        clr.setBounds(200, 400, 150, 50);

        panel = new JPanel();
        panel.setBounds(32, 90, 320, 280);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
//        panel.setBackground(Color.GREEN);

        for (int i = 0; i < 3; ++i) {
            panel.add(numberButtons[i + 1]);
        }
        panel.add(add);
        for (int i = 3; i < 6; ++i) {
            panel.add(numberButtons[i + 1]);
        }
        panel.add(sub);
        for (int i = 6; i < 9; ++i) {
            panel.add(numberButtons[i + 1]);
        }
        panel.add(mul);
        panel.add(dec);
        panel.add(numberButtons[0]);
        panel.add(equ);
        panel.add(div);

        frame.add(panel);
        frame.add(del);
        frame.add(clr);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; ++i) {

            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }

        }
        if (e.getSource() == dec) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == add) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText(" ");
        }

        if (e.getSource() == sub) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText(" ");
        }
        if (e.getSource() == mul) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText(" ");
        }
        if (e.getSource() == div) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText(" ");
        }

        if (e.getSource() == equ) {

            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(res));
            num1 = res;
        }
        if (e.getSource() == clr) {
            textField.setText("");
        }
        if(e.getSource() == del){
            String str = textField.getText();
            textField.setText("");
            for(int i = 0 ; i<str.length()-1; ++i){
                textField.setText(textField.getText() + str.charAt(i));
            }
        }
    }

}