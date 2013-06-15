package net.sf.anathema.character.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

public interface CharacterModel {

  Identifier getId();

  void initialize(ChangeAnnouncer announcer, Hero hero);
}
