package net.sf.anathema.hero.model;

import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

public interface HeroModel {

  Identifier getId();

  void initialize(InitializationContext context, Hero hero);

  void initializeListening(ChangeAnnouncer announcer);
}
