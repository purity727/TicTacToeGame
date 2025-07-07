import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class tictactoegame extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isPlayerOneTurn = true;
    private final String POLAR_BEAR = "\uD83D\uDC3B\u200D\u2744\uFE0F"; // 🐻‍❄️
    private final String FOX = "\uD83E\uDD8A";                         // 🦊

    public tictactoegame() {

        JOptionPane.showMessageDialog(null, "Welcome to Tic Tac Toe!\nPlayer 1 = 🐻‍❄️, Player 2 = 🦊");

        setTitle("Tic Tac Toe - Polar_bear vs Fox");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("SansSerif", Font.PLAIN, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return;
        }

        clicked.setText(isPlayerOneTurn ? POLAR_BEAR : FOX);

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, (isPlayerOneTurn ? "Player 1 (🐻‍❄️)" : "Player 2 (🦊)") + " wins!");
            restartPrompt();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            restartPrompt();
        } else {
            isPlayerOneTurn = !isPlayerOneTurn;
        }
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) return false;
        }
        return true;
    }

    private boolean checkWin() {
        int[][] winPatterns = {
                {0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6}
        };

        for (int[] pattern : winPatterns) {
            String a = buttons[pattern[0]].getText();
            String b = buttons[pattern[1]].getText();
            String c = buttons[pattern[2]].getText();

            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                return true;
            }
        }

        return false;
    }

    private void restartPrompt() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Restart Game", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            for (JButton button : buttons) {
                button.setText("");
            }
            isPlayerOneTurn = true;
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new tictactoegame());
    }
}
