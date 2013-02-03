package net.sf.anathema.character.presenter.magic.detail;

public interface MagicDetailModel {
  
  boolean isActive(String magicId);
  
  void setDetailFor(String magicId);
}
