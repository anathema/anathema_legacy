package net.sf.anathema.character.main.magic.model.charms.options;

import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.type.CharacterType;

public interface CharmTemplateRetriever {
  ICharmTemplate getCharmTemplate(CharacterType type);
}