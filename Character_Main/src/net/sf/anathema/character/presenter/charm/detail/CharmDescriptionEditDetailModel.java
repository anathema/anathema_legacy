package net.sf.anathema.character.presenter.charm.detail;

public class CharmDescriptionEditDetailModel implements CharmDetailModel {

  private CharmDescriptionEditModel editModel;

  public CharmDescriptionEditDetailModel(CharmDescriptionEditModel editModel) {
    this.editModel = editModel;
  }

  @Override
  public boolean isActive() {
    return editModel.isActive();
  }

  @Override
  public void setDetailFor(String nodeId) {
    this.editModel.setEditId(nodeId);
  }
}
