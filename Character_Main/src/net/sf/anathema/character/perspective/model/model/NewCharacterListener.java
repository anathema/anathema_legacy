package net.sf.anathema.character.perspective.model.model;

import net.sf.anathema.character.generic.template.ITemplateType;

public interface NewCharacterListener {

  void added(CharacterIdentifier identifier, String printName, ITemplateType templateType);
}
