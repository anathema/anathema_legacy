package net.sf.anathema.character.main.magic.view;

public interface IMagicViewListener {

  void magicRemoved(Object[] removedMagic);

  void magicAdded(Object[] addedMagic);
}