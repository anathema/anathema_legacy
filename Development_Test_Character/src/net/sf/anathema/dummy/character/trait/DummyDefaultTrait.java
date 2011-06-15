package net.sf.anathema.dummy.character.trait;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class DummyDefaultTrait extends DummyModifiableGenericTrait implements IDefaultTrait {

  public DummyDefaultTrait(ITraitType traitType) {
    super(traitType);
  }

  public void addRangeListener(IChangeListener listener) {
    //not yet implemented
  }

  public ISubTraitContainer createSpecialtiesContainer() {
    return null;
  }

  public int getMinimalValue() {
    return 0;
  }
  
  public void applyCapModifier(int modifier)
  {
	  // not yet implemented
  }
  
  public int getUnmodifiedMaximalValue()
  {
	  // not yet implemented
	  return 0;
  }
  
  public int getModifiedMaximalValue()
  {
	  // not yet implemented
	  return 0;
  }
  
  public void validate() {
	  // not yet implemented
  }

  public void resetCurrentValue() {
    //not yet implemented
  }

  public void setModifiedCreationRange(int newInitialValue, int newUpperValue) {
    //not yet implemented
  }

  public void accept(ITraitVisitor visitor) {
    visitor.visitDefaultTrait(this);
  }

  public void addCreationPointListener(IIntValueChangedListener listener) {
    //not yet implemented
  }

  public void addCurrentValueListener(IIntValueChangedListener listener) {
    //not yet implemented
  }
  
  public int getCalculationMinValue()
  {
	return 0;
  }

  public int getInitialValue() {
    return 0;
  }

  public int getMaximalValue() {
    return 0;
  }

  public void removeCreationPointListener(IIntValueChangedListener listener) {
    //not yet implemented
  }

  public void removeCurrentValueListener(IIntValueChangedListener listener) {
    //not yet implemented
  }

  public void resetCreationValue() {
    //not yet implemented
  }

  public void resetExperiencedValue() {
    //not yet implemented
  }

  public void setCreationValue(int value) {
    //not yet implemented
  }

  public void setExperiencedValue(int value) {
    //not yet implemented
  }
  
  public void setUncheckedCreationValue(int value)
  {
	    //not yet implemented
  }

  public void setUncheckedExperiencedValue(int value)
  {
	    //not yet implemented
  }

  public int getAbsoluteMinValue() {
    return 0;
  }

  public int getCalculationValue() {
    return 0;
  }

  public int getCreationCalculationValue() {
    return 0;
  }

  public int getCreationValue() {
    return 0;
  }

  public int getExperiencedCalculationValue() {
    return 0;
  }

  public int getExperiencedValue() {
    return 0;
  }

  public int getZeroCalculationValue() {
    return 0;
  }

  public boolean isLowerable() {
    return false;
  }
}