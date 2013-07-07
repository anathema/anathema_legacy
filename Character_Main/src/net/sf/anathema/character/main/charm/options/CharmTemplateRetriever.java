package net.sf.anathema.character.main.charm.options;

import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface CharmTemplateRetriever {
  ICharmTemplate getCharmTemplate(ICharacterType type);
}