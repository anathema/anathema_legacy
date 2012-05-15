package net.sf.anathema.character.library.trait;

public interface IModifiableCapTrait
{
	void applyCapModifier(int modifier);
	
	int getModifiedMaximalValue();
	
	int getUnmodifiedMaximalValue();
	
	void setUncheckedCreationValue(int value);
	
	void setUncheckedExperiencedValue(int value);
}
