package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

final class ComboSelectionListCellRenderer extends LegalityCheckListCellRenderer {
  private final IComboConfiguration comboConfiguration;

  public ComboSelectionListCellRenderer(Resources resources, IComboConfiguration comboConfiguration) {
    super(resources);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  protected boolean isLegal(Object object) {
    return comboConfiguration.isComboLegal((ICharm) object);
  }
}
