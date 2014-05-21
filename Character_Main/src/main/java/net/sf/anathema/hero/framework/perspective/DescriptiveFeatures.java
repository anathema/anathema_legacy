package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.lib.util.Identifier;

public interface DescriptiveFeatures {

  String getPrintName();

  CharacterIdentifier getIdentifier();

  TemplateType getTemplateType();

  Identifier getCasteType();

  boolean isDirty();
}
