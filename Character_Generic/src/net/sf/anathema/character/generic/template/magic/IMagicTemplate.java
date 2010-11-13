package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicTemplate {

  public boolean canBuyFromFreePicks(IMagic magic);

  public ISpellMagicTemplate getSpellMagic();

  public ICharmTemplate getCharmTemplate();

  public FavoringTraitType getFavoringTraitType();
}