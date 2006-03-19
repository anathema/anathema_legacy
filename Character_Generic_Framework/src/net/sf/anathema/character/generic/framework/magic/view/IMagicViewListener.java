package net.sf.anathema.character.generic.framework.magic.view;

public interface IMagicViewListener {

  public void magicRemoved(Object[] removedMagic);

  public void magicAdded(Object[] addedMagic);
}