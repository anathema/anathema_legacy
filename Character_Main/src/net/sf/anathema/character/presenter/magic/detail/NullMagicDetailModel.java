package net.sf.anathema.character.presenter.magic.detail;

public class NullMagicDetailModel implements MagicDetailModel {
  @Override
  public boolean isActive(String magicId) {
    return false;
  }

  @Override
  public void setDetailFor(String magicId) {
    //nothing to do
  }
}
