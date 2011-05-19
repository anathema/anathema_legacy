package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.mutations.model.types.AttributeEnhancingMutation;
import net.sf.anathema.character.mutations.model.types.AttributePointsProvidingMutation;
import net.sf.anathema.character.mutations.model.types.BrawlWeaponProvidingMutation;
import net.sf.anathema.character.mutations.model.types.SoakProvidingMutation;

public class MutationVisitorAdapter implements IMutationVisitor {

  public void visitAttributePointsProvidingMutation(AttributePointsProvidingMutation mutation) {
    //Nothing to do
  }

  public void visitAttributeEnhancingMutation(AttributeEnhancingMutation mutation) {
    //Nothing to do
  }

  public void acceptSoakProvidingMutation(SoakProvidingMutation mutation) {
    //Nothing to do
  }

  public void acceptBrawlWeaponProvidingMutation(BrawlWeaponProvidingMutation mutation) {
    //Nothing to do
  }
}