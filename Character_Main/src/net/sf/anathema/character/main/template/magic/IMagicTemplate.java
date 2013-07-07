package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.IMagic;

public interface IMagicTemplate {

  boolean canBuyFromFreePicks(IMagic magic);

  ISpellMagicTemplate getSpellMagic();

  ICharmTemplate getCharmTemplate();
}