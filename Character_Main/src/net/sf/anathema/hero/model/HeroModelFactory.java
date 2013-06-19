package net.sf.anathema.hero.model;

import net.sf.anathema.hero.initialization.ModelTreeEntry;
import net.sf.anathema.hero.template.TemplateFactory;

public interface HeroModelFactory extends ModelTreeEntry {

  <M extends HeroModel> M create(TemplateFactory templateFactory);
}
