package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.ArrayList;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.quality.model.AbstractQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class GiftModel extends AbstractQualityModel<IGift> implements IGiftModel {

  private final IGift[] allGifts;
  private final ChangeControl overviewControl = new ChangeControl();
  private int picks = 0;

  public GiftModel(final ICharacterModelContext context, IBeastformModel model) {
    super(context);
    this.allGifts = GiftProvider.getAllGifts(context.getBasicCharacterContext().getRuleSet().getEdition());
    model.addCharmLearnCountChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
    	  if (context.getBasicCharacterContext().getRuleSet().getEdition() == ExaltedEdition.FirstEdition)
    		  GiftModel.this.picks = newValue == 0 ? 0 : 2 + newValue - 1;
    	  else
    		  GiftModel.this.picks = newValue == 0 ? 0 : 4 + context.getTraitCollection().
    				  getTrait(OtherTraitType.Essence).getCurrentValue();
          overviewControl.fireChangedEvent();
      }
    });
  }

  @Override
  public boolean isSelectable(IGift quality) {
    if (quality == null) {
      return false;
    }
    if (getGroupCost(getSelectedQualities()) >= picks) {
      return false;
    }
    boolean prerequisitesFulfilled = quality.prerequisitesFulfilled(getSelectedQualities());
    return super.isSelectable(quality) && prerequisitesFulfilled;
  }
  
  private int getGroupCost(IQualitySelection<IGift>[] selection)
  {
	  int total = 0;
	  for (IQualitySelection<IGift> item : selection)
		  total += item.getPointValue();
	  return total;
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
  
  public int getSpentPicks()
  {
	  return getGroupCost(getSelectedQualities());
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
  
  public void addOverviewChangedListener(IChangeListener listener) {
    overviewControl.addChangeListener(listener);
  }
}