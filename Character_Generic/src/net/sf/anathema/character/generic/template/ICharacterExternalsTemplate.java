package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;

public interface ICharacterExternalsTemplate {

  public ITemplateType getTemplateType();

  public IExaltedEdition getEdition();

  public IPresentationProperties getPresentationProperties();
  
  public boolean isLegacy();
}