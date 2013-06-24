package net.sf.anathema.character.impl.view.magic;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.trait.view.ITraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.platform.tree.view.interaction.SpecialContent;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SpecialIntValueView implements IIntValueView, SpecialContent {
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
  public void addTo(JComponent menu) {
    JPanel container = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
    view.addComponents(container);
    menu.add(container);
  }
}