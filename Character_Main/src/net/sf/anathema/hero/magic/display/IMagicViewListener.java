package net.sf.anathema.hero.magic.display;

public interface IMagicViewListener {

  void magicRemoved(Object[] removedMagic);

  void magicAdded(Object[] addedMagic);
}