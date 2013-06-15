package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicTemplate {

  boolean canBuyFromFreePicks(IMagic magic);

  ISpellMagicTemplate getSpellMagic();

  ICharmTemplate getCharmTemplate();
}