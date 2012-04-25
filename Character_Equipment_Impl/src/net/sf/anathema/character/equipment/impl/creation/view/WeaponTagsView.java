package net.sf.anathema.character.equipment.impl.creation.view;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;

public class WeaponTagsView implements IWeaponTagsView {

  private final JPanel content = new JPanel(new GridDialogLayout(3, true));

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void requestFocus() {
    //nothing to do
  }

  @Override
  public void dispose() {
    //nothing to do
  }

  @Override
  public JCheckBox addCheckBox(String string) {
    JCheckBox box = new JCheckBox(string);
    content.add(box);
    return box;
  }
}
