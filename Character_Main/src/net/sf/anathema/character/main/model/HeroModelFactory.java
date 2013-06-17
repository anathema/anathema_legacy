package net.sf.anathema.character.main.model;

import net.sf.anathema.character.main.model.initialization.ModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;

public interface HeroModelFactory extends ModelTreeEntry {

  <M extends HeroModel> M create(TemplateFactory templateFactory);
}
