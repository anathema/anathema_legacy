package net.sf.anathema.hero.magic.display;

public interface MagicViewListener {

  void removeMagicRequested(Object[] removedMagic);

  void addMagicRequested(Object[] addedMagic);
}