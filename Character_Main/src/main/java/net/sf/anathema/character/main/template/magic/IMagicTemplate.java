package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.Magic;

public interface IMagicTemplate {

  boolean canBuyFromFreePicks(Magic magic);

  ISpellMagicTemplate getSpellMagic();
}