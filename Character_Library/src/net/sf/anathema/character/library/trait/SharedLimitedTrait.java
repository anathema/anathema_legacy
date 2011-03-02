package net.sf.anathema.character.library.trait;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;

public class SharedLimitedTrait extends DefaultTrait
{
	private final List<SharedLimitedTrait> links;
	private final IIncrementChecker incrementChecker;
	
	public SharedLimitedTrait(ITraitType type, ITraitTemplate template,
			IIncrementChecker incrementChecker, ITraitContext context,
			List<SharedLimitedTrait> links)
	{
		super(new TraitRules(type, template, context.getLimitationContext()), context, new FriendlyValueChangeChecker());
		this.links = links == null ? new ArrayList<SharedLimitedTrait>() : links;
		this.links.add(this);
		this.incrementChecker = incrementChecker;
	}
	
	public List<SharedLimitedTrait> getLinks()
	{
		return links;
	}
	
	@Override
	public final void setCurrentValue(int value)
	{
		if (!incrementChecker.isValidIncrement(value - getCurrentValue()))
			return;
		
		adjustValue(value);
		pullLinks(value);		
	}
	
	@Override
	public final void setCreationValue(int value)
	{
		super.setCreationValue(value);
		pullLinks(value);
	}
	
	private final void pullLinks(int value)
	{
		for (SharedLimitedTrait trait : links)
			if (trait.getCurrentValue() != value)
				trait.adjustValue(value);
	}
	
	private final void adjustValue(int value)
	{
		super.setCurrentValue(value);
	}

}
