package net.sf.anathema.character.generic.framework.magic.view;

public interface IMagicViewListener {

  void magicRemoved(Object[] removedMagic);

  void magicAdded(Object[] addedMagic);
}