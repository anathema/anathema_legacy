package net.sf.anathema.character.library.trait;

public interface IModifiableCapTrait
{
	public void applyCapModifier(int modifier);
	
	public int getModifiedMaximalValue();
	
	public int getUnmodifiedMaximalValue();
	
	public void setUncheckedCreationValue(int value);
	
	public void setUncheckedExperiencedValue(int value);
}
