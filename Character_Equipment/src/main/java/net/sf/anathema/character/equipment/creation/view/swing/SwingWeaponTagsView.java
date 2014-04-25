package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.creation.presenter.WeaponTagsView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class SwingWeaponTagsView implements WeaponTagsView {

  private final JPanel content = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(3)));

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public JCheckBox addCheckBox(String string) {
    JCheckBox box = new JCheckBox(string);
    content.add(box);
    return box;
  }
}