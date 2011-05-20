package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.mutations.model.types.AttributeEnhancingMutation;
import net.sf.anathema.character.mutations.model.types.AttributePointsProvidingMutation;
import net.sf.anathema.character.mutations.model.types.BrawlWeaponProvidingMutation;
import net.sf.anathema.character.mutations.model.types.SoakProvidingMutation;

public interface IMutationVisitor {

  public void visitAttributePointsProvidingMutation(AttributePointsProvidingMutation mutation);

  public void visitAttributeEnhancingMutation(AttributeEnhancingMutation mutation);

  public void acceptSoakProvidingMutation(SoakProvidingMutation mutation);

  public void acceptBrawlWeaponProvidingMutation(BrawlWeaponProvidingMutation mutation);
}