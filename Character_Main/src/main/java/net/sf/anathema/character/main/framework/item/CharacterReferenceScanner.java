package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.framework.perspective.model.CharacterReference;
import net.sf.anathema.lib.util.Identifier;

public interface CharacterReferenceScanner {
  CharacterType getCharacterType(CharacterReference reference);

  Identifier getCasteType(CharacterReference reference);

  ITemplateType getTemplateType(CharacterReference reference);
}