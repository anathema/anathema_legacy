package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

public interface ICharmContext {

  public ICharmLearnStrategy getCharmLearnStrategy();

  public IGenericCharmConfiguration getCharmConfiguration();
}