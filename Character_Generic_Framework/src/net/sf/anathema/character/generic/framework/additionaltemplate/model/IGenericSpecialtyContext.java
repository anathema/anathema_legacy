package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ISpecialtyListChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IGenericSpecialtyContext {
	public INamedGenericTrait[] getSpecialties(ITraitType traitType);
	
	public void addSpecialtyListChangeListener(ISpecialtyListChangeListener listener);
}
