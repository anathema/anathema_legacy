package net.sf.anathema.hero.model;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

public interface Hero extends Iterable<HeroModel> {

  HeroTemplate getTemplate();

  ChangeAnnouncer getChangeAnnouncer();

  <M extends HeroModel> M getModel(Identifier id);

  boolean isFullyLoaded();
}
