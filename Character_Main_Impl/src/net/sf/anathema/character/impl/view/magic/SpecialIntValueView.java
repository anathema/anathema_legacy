package net.sf.anathema.character.impl.view.magic;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.trait.view.ITraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.platform.tree.view.interaction.SpecialContent;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SpecialIntValueView implements IIntValueView, SpecialContent{
  private final ITraitView view;

  public SpecialIntValueView(ITraitView view) {
    this.view = view;
  }

  @Override
  public void setValue(int newValue) {
    view.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    view.removeIntValueChangedListener(listener);
  }

  @Override
  public void setMaximum(int maximalValue) {
    view.setMaximum(maximalValue);
  }

  @Override
  public void addTo(JComponent menu) {
    JPanel container = new JPanel(new GridDialogLayout(2, false));
    view.addComponents(container);
    menu.add(container);
  }
}