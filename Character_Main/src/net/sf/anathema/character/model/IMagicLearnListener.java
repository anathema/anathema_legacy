package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicLearnListener<T extends IMagic> {

  public void magicLearned(T[] magic);

  public void magicForgotten(T[] magic);
}