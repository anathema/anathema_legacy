package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;

public class SecondEditionGiftModel extends GiftModel
{
	public SecondEditionGiftModel(final ICharacterModelContext context, IBeastformModel model)
	{
		super(context, model);
	}
	
	@Override
	public void removeQualitySelection(IQualitySelection<IGift> selection)
	{
		    for (IQualitySelection<IGift> existingSelection : getSelectedQualities()) {
		      if (existingSelection.equals(selection))
		      {
		    	removeQualityCompletely(existingSelection);
		        fireModelChangedEvent();
		        break;
		      }
		    }
     }
}
