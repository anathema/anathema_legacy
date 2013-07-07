package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.template.presentation.IPresentationProperties;

public interface ICharacterExternalsTemplate {

  ITemplateType getTemplateType();

  IPresentationProperties getPresentationProperties();
}
