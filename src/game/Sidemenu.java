/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.GameItems.Card;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author kiran
 */
public class Sidemenu extends JPanel implements ActionListener {

    //Panel Components.
    private JLabel playerInfo;      //Display player's image. Name kept in border.
    private JButton makeAccusation; //Opens Accusation window.
    private JButton makeAssumption; //Opens Assumption window.
    private JButton endTurn;
    private JButton openNotebook;   //Opens NoteBook window

    //Panel Components that are class extensions.
    private NoteBook noteBookWindow;    //NoteBook Pop-Up window.
    private Guess assumptionWindow;     //Assumption Pop-Up window.
    private Guess accusationWindow;     //Accusation Pop-Up window.

    public Sidemenu(Player player) {

        //Create Pop=Up Windows, initial visibility: false.
        noteBookWindow = new NoteBook();
        assumptionWindow = new Guess("Make Assumption", player.getHand());
        accusationWindow = new Guess("Make Accusation", player.getHand());

        //Layout of inherited JPanel is a 3h x 1w grid.
        setLayout(new GridLayout(3, 1, 0, 2));

        //Create playerInfo label.
        playerInfo = new JLabel();
        playerInfo.setIcon(player.getPlayerIcon().getImage());
        playerInfo.setBorder(BorderFactory.createTitledBorder(player.getName() + "'s Turn:"));
        playerInfo.setHorizontalAlignment(SwingConstants.CENTER);

        //Create optionArea panel, adding buttons in 6h x 1w grid.
        JPanel optionArea = new JPanel(new GridLayout(6, 1, 10, 2));

        //Buttons for optionArea.
        makeAccusation = new JButton("Make Accusation");
        makeAccusation.setEnabled(false);
        makeAssumption = new JButton("Make Assumption");
        makeAssumption.setEnabled(false);
        endTurn = new JButton("End Turn");
        endTurn.setEnabled(false);
        openNotebook = new JButton("Open NoteBook");

        //Add implemented ActionListener to each button.
        makeAccusation.addActionListener(this);
        makeAssumption.addActionListener(this);
        openNotebook.addActionListener(this);

        //Add buttons to optionArea Panel.
        optionArea.add(makeAccusation);
        optionArea.add(makeAssumption);
        optionArea.add(openNotebook);
        optionArea.add(endTurn);

        //Add all components to main JPanel.
        add(playerInfo);
        add(optionArea);
    }

    /**
     * actionPerformed method catches user input.
     *
     * @param e recognizes presses of option buttons.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openNotebook) {
            if (!noteBookWindow.isVisible()) {
                noteBookWindow.setVisible(true);
            }
        } else if (e.getSource() == makeAssumption) {
            if (!assumptionWindow.isVisible()) {
                assumptionWindow.setVisible(true);
            }
        } else if (e.getSource() == makeAccusation) {
            if (!accusationWindow.isVisible()) {
                accusationWindow.setVisible(true);
            }
        }
    }

    /**
     * changeTurnIndicator method replaces image in playerInfo JLabel.
     *
     * @param nextPlayer Card that contains the name and image of next player.
     */
    public void changeTurnIndicator(Card nextPlayer) {
        playerInfo.setBorder(BorderFactory.createTitledBorder(nextPlayer.getName() + "'s Turn:"));
        playerInfo.setIcon(nextPlayer.getImage());
    }

    /**
     * toggleButtonsEnabled changes clickable state of buttons, to control user
     * interaction.
     *
     * @param toggle True turns buttons clickable.
     */
    public void toggleButtonsEnabled(boolean toggle) {
        makeAssumption.setEnabled(toggle);
        makeAccusation.setEnabled(toggle);
        endTurn.setEnabled(toggle);
    }

    public Guess getAssumptionWindow() {
        return assumptionWindow;
    }

    public Guess getAccusationWindow() {
        return accusationWindow;
    }

    public NoteBook getNoteBookWindow() {
        return noteBookWindow;
    }

    public JButton getMakeAccusation() {
        return makeAccusation;
    }

    public JButton getMakeAssumption() {
        return makeAssumption;
    }

    public JButton getEndTurn() {
        return endTurn;
    }
}
