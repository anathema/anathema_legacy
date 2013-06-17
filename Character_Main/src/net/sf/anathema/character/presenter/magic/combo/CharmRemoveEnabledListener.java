package net.sf.anathema.character.presenter.magic.combo;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CharmRemoveEnabledListener implements ListSelectionListener {
  private final JButton button;
  private final JList list;

  public CharmRemoveEnabledListener(JButton button, JList list) {
    this.button = button;
    this.list = list;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    button.setEnabled(!list.isSelectionEmpty() && list.getSelectedValue() != null);
  }
}