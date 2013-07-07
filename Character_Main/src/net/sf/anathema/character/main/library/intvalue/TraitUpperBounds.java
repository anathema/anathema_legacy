package net.sf.anathema.character.main.library.intvalue;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.framework.value.TwoUpperBounds;

public class TraitUpperBounds implements TwoUpperBounds {

  private final Trait trait;

  public TraitUpperBounds(Trait trait) {
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