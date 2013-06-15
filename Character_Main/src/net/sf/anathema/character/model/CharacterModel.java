package net.sf.anathema.character.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identified;

public interface CharacterModel {

  Identified getId();

  void initialize(ChangeAnnouncer announcer, Hero hero);
}
