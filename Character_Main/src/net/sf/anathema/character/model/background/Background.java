package net.sf.anathema.character.model.background;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;

public class Background extends DefaultTrait implements IBackground
{
	private final String description;

	public Background(String description,
			ITraitRules traitRules,
			ITraitContext traitContext, 
			IValueChangeChecker valueChangeChecker) {
		super(traitRules, traitContext, valueChangeChecker);
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}

}
