package net.sf.anathema.character.thaumaturgy.model;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.IValueChangeChecker;

public class ThaumaturgyDegree extends ThaumaturgyMagic
{
	private static int[] occultRequirements = { 0, 1, 3, 5 };
	
	public ThaumaturgyDegree(String name,
			final IGenericTraitCollection collection,
			ITraitContext context) {
		super(name, null, 0, context, collection, new IValueChangeChecker()
		{
			@Override
			public boolean isValidNewValue(int value) {
				return collection.getTrait(AbilityType.Occult).getCurrentValue() >=
					occultRequirements[value];
			}
			
		});			
	}
}
