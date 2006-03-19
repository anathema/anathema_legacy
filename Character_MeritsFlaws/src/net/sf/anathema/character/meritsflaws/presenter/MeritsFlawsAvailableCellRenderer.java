package net.sf.anathema.character.meritsflaws.presenter;

import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class MeritsFlawsAvailableCellRenderer extends LegalityCheckListCellRenderer {

  private final IMeritsFlawsModel model;

  public MeritsFlawsAvailableCellRenderer(IMeritsFlawsModel model, IResources resources) {
    super(resources);
    this.model = model;
  }

  @Override
  protected String getPrintName(IResources res, Object value) {
    IPerk perk = (IPerk) value;
    return res.getString(perk.getType().getId() + "." //$NON-NLS-1$
        + perk.getCategory().getId()
        + "." //$NON-NLS-1$
        + perk.getId());
  }

  @Override
  protected boolean isLegal(Object object) {
    return model.isSelectable((IPerk) object);
  }
}