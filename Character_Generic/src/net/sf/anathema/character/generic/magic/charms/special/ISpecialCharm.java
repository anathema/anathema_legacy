package net.sf.anathema.character.generic.magic.charms.special;

public interface ISpecialCharm {

  public void accept(ISpecialCharmVisitor visitor);

  public String getCharmId();
}