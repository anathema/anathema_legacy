package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;

public interface ICharacterExternalsTemplate {

  ITemplateType getTemplateType();

  IPresentationProperties getPresentationProperties();
}
