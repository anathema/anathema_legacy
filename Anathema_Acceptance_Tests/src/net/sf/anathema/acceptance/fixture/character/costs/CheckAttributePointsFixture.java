package net.sf.anathema.acceptance.fixture.character.costs;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.model.creation.IBonusPointManagement;

public class CheckAttributePointsFixture extends AbstractCheckPointsFixture {
  
  public int primaryDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeDotsSpent(AttributeGroupPriority.Primary);
  }

  public int secondaryDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeDotsSpent(AttributeGroupPriority.Secondary);
  }

  public int tertiaryDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeDotsSpent(AttributeGroupPriority.Tertiary);
  }

  public int getTotalDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeDotsSpent(AttributeGroupPriority.Primary)
        + management.getAttributeDotsSpent(AttributeGroupPriority.Secondary)
        + management.getAttributeDotsSpent(AttributeGroupPriority.Tertiary);
  }
}