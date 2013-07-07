package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.magic.IMagic;

public interface IMagicLearnListener<T extends IMagic> {

  void magicLearned(T[] magic);

  void magicForgotten(T[] magic);
}