package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.main.hero.initialization.ModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;

public interface HeroModelFactory extends ModelTreeEntry {

  <M extends HeroModel> M create(TemplateFactory templateFactory);
}
