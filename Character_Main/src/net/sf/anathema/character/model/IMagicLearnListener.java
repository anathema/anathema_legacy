package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicLearnListener<T extends IMagic> {

  void magicLearned(T[] magic);

  void magicForgotten(T[] magic);
}