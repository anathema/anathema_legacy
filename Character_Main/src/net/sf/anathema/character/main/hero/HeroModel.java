package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

public interface HeroModel {

  Identifier getId();

  void initialize(InitializationContext context, Hero hero);

  void initializeListening(ChangeAnnouncer announcer);
}
