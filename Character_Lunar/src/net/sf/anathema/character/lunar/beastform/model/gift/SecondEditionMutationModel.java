package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.mutations.model.AbstractMutationsModel;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.MutationProvider;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class SecondEditionMutationModel extends AbstractMutationsModel
{
	private static final String TERRIFYING_BEASTMAN_ALTERATION = "Lunar.TerrifyingBeastmanAlteration";
	private ILabelledAlotmentView counter;
	private int maxPicks = 0;
	
	public SecondEditionMutationModel(final ICharacterModelContext context,
			IBeastformModel model)
	{
		super(context);
		this.allMutations = MutationProvider.getMutations(context.getBasicCharacterContext().getRuleSet().getEdition(),
					new DBTMutationRules());
		model.addCharmLearnCountChangedListener(new IIntValueChangedListener() {
		      public void valueChanged(int newValue) {
		    		  SecondEditionMutationModel.this.maxPicks = newValue == 0 ? 0 :
		    			  4 + (hasTBA(context) ? 2 : 1) *
		    			  	context.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
		          updateOverview();
		      }
		    });
	}
	
	private boolean hasTBA(ICharacterModelContext context)
	{
		for (ICharm charm : context.getCharmContext().getCharmConfiguration().getLearnedCharms())
			if (charm.getId().equals(TERRIFYING_BEASTMAN_ALTERATION))
				return true;
		return false;
	}
	
	@Override
	  public boolean isSelectable(IMutation quality) {
	    return super.isSelectable(quality) &&
	    	getGroupCost(getSelectedQualities()) + quality.getCost() <= maxPicks;
	  }
	
	  private int getGroupCost(IQualitySelection<IMutation>[] selection)
	  {
		  int total = 0;
		  for (IQualitySelection<IMutation> item : selection)
			  total += item.getPointValue();
		  return total;
	  }
	
	@Override
	public void designOverview(IOverviewCategory category, IResources resources) {
		counter = category.addAlotmentView(resources.getString("Lunar.DeadlyBeastmanTransformation.Gifts.Label_2nd"),
				2);
		updateOverview();
	}

	@Override
	public void updateOverview()
	{
		if (counter != null)
		{
			counter.setValue(getGroupCost(getSelectedQualities()));
			counter.setAlotment(maxPicks);
		}
	}

}
