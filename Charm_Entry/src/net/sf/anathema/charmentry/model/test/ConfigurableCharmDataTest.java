package net.sf.anathema.charmentry.model.test;

import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.charmentry.model.ConfigurableCharmData;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;

public class ConfigurableCharmDataTest extends BasicTestCase {

  private ConfigurableCharmData data;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    data = new ConfigurableCharmData();
  }

  public void testChangePrerequisite() throws Exception {
    IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
    IGenericTrait expected = new ValuedTraitType(AbilityType.Athletics, 2);
    data.addPrerequisite(unwanted);
    data.addPrerequisite(expected);
    assertEquals(1, data.getPrerequisites().length);
    assertTrue(ArrayUtilities.contains(data.getPrerequisites(), expected));
    assertFalse(ArrayUtilities.contains(data.getPrerequisites(), unwanted));
  }

  public void testChangePrimaryPrerequisiteType() throws Exception {
    IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
    IGenericTrait expected = new ValuedTraitType(AbilityType.Awareness, 4);
    data.setPrimaryPrerequisite(unwanted);
    data.setPrimaryPrerequisite(expected);
    assertEquals(1, data.getPrerequisites().length);
    assertTrue(ArrayUtilities.contains(data.getPrerequisites(), expected));
    assertFalse(ArrayUtilities.contains(data.getPrerequisites(), unwanted));
    assertEquals(expected.getType(), data.getPrimaryPrerequisiteType());
  }

  public void testRemovePrerequisite() throws Exception {
    IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
    data.addPrerequisite(unwanted);
    data.removePrerequisite(unwanted);
    assertEquals(0, data.getPrerequisites().length);
    assertFalse(ArrayUtilities.contains(data.getPrerequisites(), unwanted));
  }

  public void testCannotRemovePrimaryPrerequisite() throws Exception {
    assertThrowsException(IllegalArgumentException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        IGenericTrait expected = new ValuedTraitType(AbilityType.Awareness, 4);
        data.setPrimaryPrerequisite(expected);
        data.removePrerequisite(expected);
        assertEquals(1, data.getPrerequisites().length);
        assertTrue(ArrayUtilities.contains(data.getPrerequisites(), expected));
      }
    });
  }

  public void testDefaultTemporaryCost() throws Exception {
    assertEquals(Cost.NULL_COST, data.getTemporaryCost().getEssenceCost());
    assertEquals(Cost.NULL_COST, data.getTemporaryCost().getWillpowerCost());
    assertEquals(HealthCost.NULL_HEALTH_COST, data.getTemporaryCost().getHealthCost());
  }

  public void testDefaultPermanentCost() throws Exception {
    assertEquals(Cost.NULL_COST, data.getPermanentCost().getEssenceCost());
    assertEquals(Cost.NULL_COST, data.getPermanentCost().getWillpowerCost());
    assertEquals(HealthCost.NULL_HEALTH_COST, data.getPermanentCost().getHealthCost());
    assertEquals(Cost.NULL_COST, data.getPermanentCost().getXPCost());
  }

  public void testClearPrimaryPrerequisite() throws Exception {
    IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
    data.setPrimaryPrerequisite(unwanted);
    data.clearPrimaryPrerequisite();
    assertEquals(0, data.getPrerequisites().length);
    assertNull(data.getPrimaryPrerequisiteType());
  }

  public void testClearPrimaryBySettingToNull() throws Exception {
    IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
    data.setPrimaryPrerequisite(unwanted);
    IGenericTrait expected = new ValuedTraitType(null, 1);
    data.setPrimaryPrerequisite(expected);
    assertEquals(0, data.getPrerequisites().length);
    assertNull(data.getPrimaryPrerequisiteType());
  }

  public void testPrimaryPrerequisteComesFirst() throws Exception {
    IGenericTrait prerequiste = new ValuedTraitType(AbilityType.Athletics, 4);
    data.addPrerequisite(prerequiste);
    IGenericTrait primary = new ValuedTraitType(AbilityType.Awareness, 4);
    data.setPrimaryPrerequisite(primary);
    assertEquals(2, data.getPrerequisites().length);
    assertEquals(primary, data.getPrerequisites()[0]);
  }
}