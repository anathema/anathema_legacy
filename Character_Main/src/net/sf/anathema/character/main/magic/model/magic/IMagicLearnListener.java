package net.sf.anathema.character.main.magic.model.magic;

public interface IMagicLearnListener<T extends IMagic> {

  void magicLearned(T[] magic);

  void magicForgotten(T[] magic);
}