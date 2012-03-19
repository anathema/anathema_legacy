package net.sf.anathema.character.presenter.charm.detail;

public interface MagicDetailModel {
  
  boolean isActive(String magicId);
  
  void setDetailFor(String magicId);
}
