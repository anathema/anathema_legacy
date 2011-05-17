package net.sf.anathema.character.thaumaturgy.model;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.library.trait.IValueChangeChecker;

public class ThaumaturgyProcedure extends ThaumaturgyMagic
{
	public ThaumaturgyProcedure(String art, String procedure, final int value, final IGenericTraitCollection collection, ITraitContext context) {
		super(art, procedure, 0, context, collection, new IValueChangeChecker()
		{
			@Override
			public boolean isValidNewValue(int newValue) {
				return value == newValue;
			}
			
		});
	}

}
