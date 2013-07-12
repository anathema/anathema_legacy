package net.sf.anathema.hero.perspective;

import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.hero.perspective.model.CharacterIdentifier;
import net.sf.anathema.lib.util.Identifier;

public interface DescriptiveFeatures {

  String getPrintName();

  CharacterIdentifier getIdentifier();

  ITemplateType getTemplateType();

  Identifier getCasteType();

  boolean isDirty();
}
