package net.sf.anathema.hero.model;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

public interface HeroModel {

  Identifier getId();

  void initialize(HeroEnvironment environment, Hero hero);

  void initializeListening(ChangeAnnouncer announcer);
}
