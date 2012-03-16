package net.sf.anathema.character.presenter.charm.detail;

public class CharmDetailModel implements DetailModel<String> {

  private CharmDescriptionEditModel editModel;

  public CharmDetailModel(CharmDescriptionEditModel editModel) {
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
