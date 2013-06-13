package net.sf.anathema.character.equipment.impl.creation.model.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.generic.dummy.DummyEssenceCharacterType;
import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.dummy.DummyMundaneCharacterType;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import org.junit.Assert;
import org.junit.Test;

public class NaturalSoakTest {

  @Test
  public void testSoakForMortals() {
    NaturalSoak naturalSoak = new NaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new DummyMundaneCharacterType());
    Assert.assertEquals(Integer.valueOf(0), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(Integer.valueOf(2), naturalSoak.getSoak(HealthType.Bashing));
  }

  @Test
  public void testSoakForExalts() {
    NaturalSoak naturalSoak = new NaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new DummyExaltCharacterType());
    Assert.assertEquals(new Integer(1), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak(HealthType.Bashing));
  }

  @Test
  public void testSoakForEssenceUsers() {
    NaturalSoak naturalSoak = new NaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new DummyEssenceCharacterType());
    Assert.assertEquals(new Integer(1), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak(HealthType.Bashing));
  }
}