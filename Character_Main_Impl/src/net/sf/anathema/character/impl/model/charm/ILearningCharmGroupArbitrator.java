package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;

public interface ILearningCharmGroupArbitrator extends ICharmGroupArbitrator {

  String[] getUncompletedCelestialMartialArtsGroups(ILearningCharmGroup[] groups);

  boolean isCelestialMartialArtsGroupCompleted(ILearningCharmGroup[] groups);
}