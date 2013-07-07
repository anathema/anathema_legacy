package net.sf.anathema.character.main.magic.charms.options;

import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.type.ICharacterType;

public interface CharmTemplateRetriever {
  ICharmTemplate getCharmTemplate(ICharacterType type);
}