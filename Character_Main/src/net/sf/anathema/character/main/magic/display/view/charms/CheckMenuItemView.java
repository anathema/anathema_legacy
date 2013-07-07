package net.sf.anathema.character.main.magic.display.view.charms;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.view.interaction.SpecialContent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckMenuItemView implements IBooleanValueView, SpecialContent {

  private final JCheckBoxMenuItem menuItem;

  public CheckMenuItemView(String label) {
    menuItem = new JCheckBoxMenuItem(label);
  }

  @Override
  public void setSelected(boolean selected) {
    menuItem.setSelected(selected);
  }

  @Override
  public void addChangeListener(final IBooleanValueChangedListener listener) {
    menuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(menuItem.isSelected());
      }
    });
  }

  @Override
  public void addTo(JComponent menu) {
    menu.add(menuItem);
  }
}
