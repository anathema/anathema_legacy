package net.sf.anathema.magic.description.model;

import net.sf.anathema.character.main.presenter.magic.detail.MagicDetailModel;

public class MagicDescriptionEditDetailModel implements MagicDetailModel {

  private MagicDescriptionEditModel editModel;

  public MagicDescriptionEditDetailModel(MagicDescriptionEditModel editModel) {
    this.editModel = editModel;
  }

  @Override
  public boolean isActive(String magicId) {
    return editModel.isActive();
  }

  @Override
  public void setDetailFor(String magicId) {
    this.editModel.setEditId(magicId);
  }
}
