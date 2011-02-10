package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IGiftModel extends IQualityModel<IGift> {

  public int getSpentPicks();
	
  public int getAllowedPicks();

  public IGift getGiftById(String giftId);

  public boolean isCreationLearnedSelectionInExperiencedCharacter(IQualitySelection<IGift> selection);

  public void addOverviewChangedListener(IChangeListener listener);
}