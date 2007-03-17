package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicLearnCharmGroup;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;

public interface ILearningCharmGroup extends ICharmGroup, IBasicLearnCharmGroup {

  public void toggleLearned(ICharm charm);

  public void addCharmLearnListener(ICharmLearnListener listener);

  public ICharm[] getCreationLearnedCharms();

  public void learnCharm(ICharm charm, boolean experienced);

  public void learnCharmNoParents(ICharm charm, boolean experienced);

  public boolean isUnlearnable(ICharm charm);

  public ICharm[] getExperienceLearnedCharms();

  public void forgetCharm(ICharm child, boolean experienced);

  public void forgetAll();

  public boolean hasLearnedCharms();

  public ISpecialCharmLearnListener createSpecialCharmLearnListenerFor(ICharm charm);

  public ICharm[] getCoreCharms();

  public void unlearnExclusives();
}