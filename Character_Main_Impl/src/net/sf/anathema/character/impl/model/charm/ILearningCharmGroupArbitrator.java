package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;

public interface ILearningCharmGroupArbitrator extends ICharmGroupArbitrator {

  public String[] getUncompletedCelestialMartialArtsGroups(ILearningCharmGroup[] groups);

  public boolean isCelestialMartialArtsGroupCompleted(ILearningCharmGroup[] groups);


}