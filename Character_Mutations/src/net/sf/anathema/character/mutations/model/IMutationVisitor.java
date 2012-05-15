package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.mutations.model.types.AttributeEnhancingMutation;
import net.sf.anathema.character.mutations.model.types.AttributePointsProvidingMutation;
import net.sf.anathema.character.mutations.model.types.BrawlWeaponProvidingMutation;
import net.sf.anathema.character.mutations.model.types.SoakProvidingMutation;

public interface IMutationVisitor {

  void visitAttributePointsProvidingMutation(AttributePointsProvidingMutation mutation);

  void visitAttributeEnhancingMutation(AttributeEnhancingMutation mutation);

  void acceptSoakProvidingMutation(SoakProvidingMutation mutation);

  void acceptBrawlWeaponProvidingMutation(BrawlWeaponProvidingMutation mutation);
}