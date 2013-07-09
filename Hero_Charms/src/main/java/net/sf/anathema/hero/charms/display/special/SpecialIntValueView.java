package net.sf.anathema.hero.charms.display.special;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.library.trait.view.TraitView;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.platform.tree.view.interaction.SpecialContent;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SpecialIntValueView implements IntValueView, SpecialContent {
  private final TraitView view;

  public SpecialIntValueView(TraitView view) {
    this.view = view;
  }

  @Override
  public void setValue(int newValue) {
    view.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IntValueChangedListener listener) {
    view.removeIntValueChangedListener(listener);
  }

  @Override
  public void addTo(JComponent menu) {
    JPanel container = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
    view.addComponents(container);
    menu.add(container);
  }
}