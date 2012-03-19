package net.sf.anathema.character.presenter.magic.detail;

public class NullCharmDetailModel implements MagicDetailModel {
  @Override
  public boolean isActive(String magicId) {
    return false;
  }

  @Override
  public void setDetailFor(String magicId) {
    //nothing to do
  }
}
