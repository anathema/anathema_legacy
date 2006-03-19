package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;

public interface ICharacterCharmSelectionPresenter {

  public void setCharmVisuals(ILearningCharmGroup selectedLearnCharmGroup, ICharmSelectionView selectionView);

}
