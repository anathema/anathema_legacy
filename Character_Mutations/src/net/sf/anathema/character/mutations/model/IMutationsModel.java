package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

public interface IMutationsModel extends IQualityModel<IMutation> {
  public void designOverview(IOverviewCategory category, IResources resources);

  public void updateOverview();

  public IMutation getMutationById(String giftId);

  public boolean isCreationLearnedSelectionInExperiencedCharacter(IQualitySelection<IMutation> selection);

  public void addOverviewChangedListener(IChangeListener listener);
}
