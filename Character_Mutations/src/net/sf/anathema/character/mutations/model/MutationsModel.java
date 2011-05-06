package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class MutationsModel extends AbstractMutationsModel
{
	  private final IAdditionalModelBonusPointCalculator calculator;
	  private IValueView<Integer> bonusPointView;

	  public MutationsModel(final ICharacterModelContext context) {
	    super(context);
	    this.allMutations = MutationProvider.getAllMutations(context.getBasicCharacterContext().getRuleSet().getEdition());
	    this.calculator = new MutationsBonusPointCalculator(this);
	  }
	  
	  @Override
	  public boolean isSelectable(IMutation quality) {
	    if (quality == null) {
	      return false;
	    }
	    boolean prerequisitesFulfilled = quality.prerequisitesFulfilled(getSelectedQualities());
	    return super.isSelectable(quality) && prerequisitesFulfilled;
	  }
	  
	  public void designOverview(IOverviewCategory overview, IResources resources)
	  {
		   bonusPointView = overview.addIntegerValueView(
			        resources.getString("Mutations.Overview.BonusSpent"), 2);
	  }
	  
	  public void updateOverview()
	  {
		  calculator.recalculate();
		  bonusPointView.setValue(calculator.getBonusPointCost());
	  }
}