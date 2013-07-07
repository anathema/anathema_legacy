package net.sf.anathema.character.main.charm.options;

import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.type.ICharacterType;

public interface CharmTemplateRetriever {
  ICharmTemplate getCharmTemplate(ICharacterType type);
}