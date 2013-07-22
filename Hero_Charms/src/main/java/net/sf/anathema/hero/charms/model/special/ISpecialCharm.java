package net.sf.anathema.hero.charms.model.special;

public interface ISpecialCharm {

  void accept(ISpecialCharmVisitor visitor);

  String getCharmId();
}