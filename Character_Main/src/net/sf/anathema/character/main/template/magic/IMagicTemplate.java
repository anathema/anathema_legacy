package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.magic.Magic;

public interface IMagicTemplate {

  boolean canBuyFromFreePicks(Magic magic);

  ISpellMagicTemplate getSpellMagic();

  ICharmTemplate getCharmTemplate();
}