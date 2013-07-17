package net.sf.anathema.hero.charms.model.special.charms;

public interface ISpecialCharm {

  void accept(ISpecialCharmVisitor visitor);

  String getCharmId();
}