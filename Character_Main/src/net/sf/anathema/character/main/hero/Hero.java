package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.lib.util.Identifier;

public interface Hero {

  HeroTemplate getTemplate();

  ChangeAnnouncer getChangeAnnouncer();

  <M extends HeroModel> M getModel(Identifier id);

  boolean isFullyLoaded();
}
