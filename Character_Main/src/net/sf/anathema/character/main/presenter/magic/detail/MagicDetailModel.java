package net.sf.anathema.character.main.presenter.magic.detail;

public interface MagicDetailModel {

  boolean isActive(String magicId);

  void setDetailFor(String magicId);
}
