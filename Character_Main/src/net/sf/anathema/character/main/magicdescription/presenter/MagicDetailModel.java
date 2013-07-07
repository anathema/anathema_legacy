package net.sf.anathema.character.main.magicdescription.presenter;

public interface MagicDetailModel {

  boolean isActive(String magicId);

  void setDetailFor(String magicId);
}
