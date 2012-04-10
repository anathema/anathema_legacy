package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.framework.value.TwoUpperBounds;

public class TraitUpperBounds implements TwoUpperBounds {

  private final IModifiableCapTrait trait;

  public TraitUpperBounds(IModifiableCapTrait trait) {
    this.trait = trait;
  }

  @Override
  public int getOriginalBound() {
    return trait.getUnmodifiedMaximalValue();
  }

  @Override
  public int getModifiedBound() {
    return trait.getModifiedMaximalValue();
  }

  @Override
  public boolean haveBoundsChanged(int oldOriginalBound, int oldModifiedBound) {
    boolean orignalHasChanged = getOriginalBound() != oldOriginalBound;
    boolean modifiedHasChanged = getModifiedBound() != oldModifiedBound;
    return orignalHasChanged || modifiedHasChanged;
  }
}