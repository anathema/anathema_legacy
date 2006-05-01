package net.sf.anathema.character.equipment.impl.creation.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;

public class WeaponTagsView implements IWeaponTagsView {

  private JPanel content = new JPanel();

  public JComponent getContent() {
    return content;
  }

  public void requestFocus() {
    //nothing to do
  }

  public void dispose() {
    //nothing to do
  }
}
