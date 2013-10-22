package net.sf.anathema.hero.model;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

public interface HeroModel {

  Identifier getId();

  //TODO (urs): Only Charms, Spells and Equipment make use of the Environment parameter. Refactor to work without. 
  void initialize(HeroEnvironment environment, Hero hero);

  void initializeListening(ChangeAnnouncer announcer);
}
