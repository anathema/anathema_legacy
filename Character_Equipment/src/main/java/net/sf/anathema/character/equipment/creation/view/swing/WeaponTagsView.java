package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.layout.LayoutUtils;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class WeaponTagsView implements IWeaponTagsView {

  private final JPanel content = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(3)));

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void requestFocus() {
    //nothing to do
  }

  @Override
  public JCheckBox addCheckBox(String string) {
    JCheckBox box = new JCheckBox(string);
    content.add(box);
    return box;
  }
}