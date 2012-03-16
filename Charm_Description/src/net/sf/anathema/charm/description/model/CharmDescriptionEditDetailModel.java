package net.sf.anathema.charm.description.model;

import net.sf.anathema.character.presenter.charm.detail.CharmDetailModel;

public class CharmDescriptionEditDetailModel implements CharmDetailModel {

  private CharmDescriptionEditModel editModel;

  public CharmDescriptionEditDetailModel(CharmDescriptionEditModel editModel) {
    this.editModel = editModel;
  }

  @Override
  public boolean isActive(String charmId) {
    return editModel.isActive();
  }

  @Override
  public void setDetailFor(String nodeId) {
    this.editModel.setEditId(nodeId);
  }
}
