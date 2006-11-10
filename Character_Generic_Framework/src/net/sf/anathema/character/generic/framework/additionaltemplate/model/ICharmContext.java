package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

public interface ICharmContext extends IGenericCharmConfiguration {

  public boolean isLearned(ICharm charm);

  public ICharmLearnStrategy getCharmLearnStrategy();
}