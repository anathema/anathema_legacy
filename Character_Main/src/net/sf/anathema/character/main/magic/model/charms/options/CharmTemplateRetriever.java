package net.sf.anathema.character.main.magic.model.charms.options;

import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.type.CharacterType;

public interface CharmTemplateRetriever {
  CharmTemplate getCharmTemplate(CharacterType type);
}