package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.view.interaction.SpecialContent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    menuItem.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        listener.valueChanged(menuItem.isSelected());
      }
    });
  }

  @Override
  public void addTo(JComponent menu) {
    menu.add(menuItem);
  }
}
