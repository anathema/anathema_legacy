package net.sf.anathema.hero.magic.display;

public interface MagicViewListener {

  void magicRemoved(Object[] removedMagic);

  void magicAdded(Object[] addedMagic);
}