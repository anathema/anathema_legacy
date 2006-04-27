package net.sf.anathema.character.equipment.impl.creation.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.equipment.creation.view.ICloseCombatStatisticsView;

public class CloseCombatStatisticsView implements ICloseCombatStatisticsView {

  private JPanel panel = new JPanel();
  
  public JComponent getContent() {
    return panel;
  }

  public void requestFocus() {
    //Nothing to do
  }

  public void dispose() {
    //Nothing to do
  }
}