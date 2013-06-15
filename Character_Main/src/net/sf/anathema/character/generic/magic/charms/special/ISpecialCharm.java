package net.sf.anathema.character.generic.magic.charms.special;

public interface ISpecialCharm {

  void accept(ISpecialCharmVisitor visitor);

  String getCharmId();
}