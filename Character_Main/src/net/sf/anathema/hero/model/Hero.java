package net.sf.anathema.hero.model;

import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.lib.util.Identifier;

public interface Hero {

  HeroTemplate getTemplate();

  ChangeAnnouncer getChangeAnnouncer();

  <M extends HeroModel> M getModel(Identifier id);

  boolean isFullyLoaded();
}
