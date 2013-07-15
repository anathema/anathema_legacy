package net.sf.anathema.hero.charms.display.magic;

public interface MagicViewListener {

  void removeMagicRequested(Object[] removedMagic);

  void addMagicRequested(Object[] addedMagic);
}