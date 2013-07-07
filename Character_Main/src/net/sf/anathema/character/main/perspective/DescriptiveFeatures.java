package net.sf.anathema.character.main.perspective;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.main.perspective.model.CharacterIdentifier;
import net.sf.anathema.lib.util.Identifier;

public interface DescriptiveFeatures {

  String getPrintName();

  CharacterIdentifier getIdentifier();

  ITemplateType getTemplateType();

  Identifier getCasteType();

  boolean isDirty();
}
