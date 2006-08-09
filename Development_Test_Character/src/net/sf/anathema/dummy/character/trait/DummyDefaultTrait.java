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
  }

  public ISubTraitContainer createSpecialtiesContainer() {
    return null;
  }

  public int getMinimalValue() {
    return 0;
  }

  public void resetCurrentValue() {
  }

  public void setModifiedCreationRange(int newInitialValue, int newUpperValue) {
  }

  public void accept(ITraitVisitor visitor) {
    visitor.visitDefaultTrait(this);
  }

  public void addCreationPointListener(IIntValueChangedListener listener) {
  }

  public void addCurrentValueListener(IIntValueChangedListener listener) {
  }

  public int getInitialValue() {
    return 0;
  }

  public int getMaximalValue() {
    return 0;
  }

  public void removeCreationPointListener(IIntValueChangedListener listener) {
  }

  public void removeCurrentValueListener(IIntValueChangedListener listener) {
  }

  public void resetCreationValue() {
  }

  public void resetExperiencedValue() {
  }

  public void setCreationValue(int value) {
  }

  public void setExperiencedValue(int value) {
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