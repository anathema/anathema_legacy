package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class MutationsModel extends AbstractMutationsModel
{
	  private final IAdditionalModelBonusPointCalculator calculator;
	  private IValueView<Integer> bonusPointSpentView;
	  private IValueView<Integer> bonusPointGainedView;

	  public MutationsModel(final ICharacterModelContext context) {
	    super(context);
	    this.allMutations = MutationProvider.getMutations(context.getBasicCharacterContext().getRuleSet().getEdition(),
	    		new IMutationRules()
			    {
					@Override
					public boolean acceptMutation(IMutation mutation) {
						return true;
					}
			    });
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
		   bonusPointSpentView = overview.addIntegerValueView(
			        resources.getString("Mutations.Overview.BonusSpent"), 2);
		   bonusPointGainedView = overview.addIntegerValueView(
			        resources.getString("Mutations.Overview.BonusGained"), 2);
	  }
	  
	  public void updateOverview()
	  {
		  calculator.recalculate();
		  bonusPointSpentView.setValue(calculator.getBonusPointCost());
		  bonusPointGainedView.setValue(calculator.getBonusPointsGranted());
	  }
}