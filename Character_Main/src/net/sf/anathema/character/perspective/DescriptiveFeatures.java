package net.sf.anathema.character.perspective;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.lib.util.Identified;

public interface DescriptiveFeatures {

  String getPrintName();

  CharacterIdentifier getIdentifier();

  ITemplateType getTemplateType();

  Identified getCasteType();

  boolean isDirty();
}
