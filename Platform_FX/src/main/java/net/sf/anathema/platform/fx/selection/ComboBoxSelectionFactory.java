package net.sf.anathema.platform.fx.selection;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.FxObjectSelectionView;

public class ComboBoxSelectionFactory implements SelectionViewFactory {

  @Override public <T> FxObjectSelectionView<T> create(String label, AgnosticUIConfiguration<T> ui) {
    return new ComboBoxSelectionView<>(label, ui);
  }
}