package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

public interface ICharmContext extends IGenericCharmConfiguration {

  public ICharmLearnStrategy getCharmLearnStrategy();
}