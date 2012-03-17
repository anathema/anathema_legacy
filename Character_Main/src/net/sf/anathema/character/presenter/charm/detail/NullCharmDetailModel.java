package net.sf.anathema.character.presenter.charm.detail;

public class NullCharmDetailModel implements CharmDetailModel {
  @Override
  public boolean isActive(String charmId) {
    return false;
  }

  @Override
  public void setDetailFor(String charmId) {
    //nothing to do
  }
}
