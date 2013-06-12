package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.ComboBoxSelectionView;
import net.sf.anathema.platform.fx.FxObjectSelectionView;

public class SelectionViewFactory {

  public <T> FxObjectSelectionView<T> create(String label, AgnosticUIConfiguration<T> ui) {
    return new ComboBoxSelectionView<>(label, ui);
  }
}