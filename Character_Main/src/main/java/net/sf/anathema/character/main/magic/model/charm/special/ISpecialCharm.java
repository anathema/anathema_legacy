package net.sf.anathema.character.main.magic.model.charm.special;

public interface ISpecialCharm {

  void accept(ISpecialCharmVisitor visitor);

  String getCharmId();
}