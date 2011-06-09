package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

final class ComboSelectionListCellRenderer extends LegalityCheckListCellRenderer {
  private static final long serialVersionUID = 1735343957161490343L;
  private final IComboConfiguration comboConfiguration;

  public ComboSelectionListCellRenderer(IResources resources, IComboConfiguration comboConfiguration) {
    super(resources);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  protected boolean isLegal(Object object) {
    return comboConfiguration.isComboLegal((ICharm) object);
  }
}