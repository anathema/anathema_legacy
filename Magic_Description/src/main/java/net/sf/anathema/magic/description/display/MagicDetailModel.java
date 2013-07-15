package net.sf.anathema.magic.description.display;

public interface MagicDetailModel {

  boolean isActive(String magicId);

  void setDetailFor(String magicId);
}
