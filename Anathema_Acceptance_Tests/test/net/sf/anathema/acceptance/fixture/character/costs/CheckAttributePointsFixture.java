package net.sf.anathema.acceptance.fixture.character.costs;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.model.creation.IBonusPointManagement;

public class CheckAttributePointsFixture extends AbstractCheckPointsFixture {

  public int primaryDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeModel(AttributeGroupPriority.Primary).getValue();
  }

  public int secondaryDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeModel(AttributeGroupPriority.Secondary).getValue();
  }

  public int tertiaryDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeModel(AttributeGroupPriority.Tertiary).getValue();
  }

  public int getTotalDotsSpent() {
    IBonusPointManagement management = createManagement();
    return management.getAttributeModel(AttributeGroupPriority.Primary).getValue()
        + management.getAttributeModel(AttributeGroupPriority.Secondary).getValue()
        + management.getAttributeModel(AttributeGroupPriority.Tertiary).getValue();
  }
}