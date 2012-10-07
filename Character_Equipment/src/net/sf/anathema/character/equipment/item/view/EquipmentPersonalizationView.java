package net.sf.anathema.character.equipment.item.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class EquipmentPersonalizationView implements IPageContent, IEquipmentPersonalizationView {
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
  private JComponent focusComponent;

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void addEntry(String label, JTextField text) {
    content.add(new JLabel(label));
    content.add(text, new CC().growX().pushX());
    if (focusComponent == null) {
      focusComponent = text;
    }
  }

  @Override
  public void requestFocus() {
    focusComponent.requestFocus();
  }
}