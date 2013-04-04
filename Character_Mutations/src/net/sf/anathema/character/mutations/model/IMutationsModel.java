package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.Resources;

public interface IMutationsModel extends IQualityModel<IMutation> {
  void designOverview(IOverviewCategory category, Resources resources);

  void updateOverview();

  IMutation getMutationById(String giftId);

  void addOverviewChangedListener(IChangeListener listener);

  void addCharacterChangeListener(ICharacterChangeListener listener);
}
