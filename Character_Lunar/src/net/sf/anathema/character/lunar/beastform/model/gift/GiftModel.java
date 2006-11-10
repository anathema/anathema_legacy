package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.ArrayList;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.quality.model.AbstractQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class GiftModel extends AbstractQualityModel<IGift> implements IGiftModel {

  private final IGift[] allGifts;
  private int picks = 0;

  public GiftModel(ICharacterModelContext context, IBeastformModel model) {
    super(context);
    this.allGifts = GiftProvider.getAllGifts();
    model.addCharmLearnCountChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        GiftModel.this.picks = newValue == 0 ? 0 : 2 + newValue - 1;
        fireModelChangedEvent();
      }
    });
  }

  @Override
  public boolean isSelectable(IGift quality) {
    if (quality == null) {
      return false;
    }
    if (getSelectedQualities().length >= picks) {
      return false;
    }
    boolean prerequisitesFulfilled = quality.prerequisitesFulfilled(getSelectedQualities());
    return super.isSelectable(quality) && prerequisitesFulfilled;
  }

  public IGift[] getAvailableQualities() {
    ArrayList<IGift> availableGifts = new ArrayList<IGift>();
    for (IGift gift : allGifts) {
      if (isSelected(gift)) {
        continue;
      }
      availableGifts.add(gift);
    }
    return availableGifts.toArray(new IGift[availableGifts.size()]);
  }

  public int getAllowedPicks() {
    return picks;
  }

  public boolean isCreationLearnedSelectionInExperiencedCharacter(IQualitySelection<IGift> selection) {
    return selection.isCreationActive() && getContext().getBasicCharacterContext().isExperienced();
  }

  public IGift getGiftById(String giftId) {
    for (IGift gift : allGifts) {
      if (gift.getId().equals(giftId)) {
        return gift;
      }
    }
    throw new IllegalArgumentException("No gift found for id \"" //$NON-NLS-1$
        + giftId
        + "\"."); //$NON-NLS-1$
  }
}