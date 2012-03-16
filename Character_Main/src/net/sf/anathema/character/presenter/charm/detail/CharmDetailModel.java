package net.sf.anathema.character.presenter.charm.detail;

public interface CharmDetailModel {
  
  boolean isActive(String charmId);
  
  void setDetailFor(String charmId);
}
