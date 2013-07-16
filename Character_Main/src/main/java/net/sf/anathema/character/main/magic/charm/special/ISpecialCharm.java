package net.sf.anathema.character.main.magic.charm.special;

public interface ISpecialCharm {

  void accept(ISpecialCharmVisitor visitor);

  String getCharmId();
}