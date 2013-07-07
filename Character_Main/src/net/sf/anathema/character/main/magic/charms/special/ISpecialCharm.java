package net.sf.anathema.character.main.magic.charms.special;

public interface ISpecialCharm {

  void accept(ISpecialCharmVisitor visitor);

  String getCharmId();
}