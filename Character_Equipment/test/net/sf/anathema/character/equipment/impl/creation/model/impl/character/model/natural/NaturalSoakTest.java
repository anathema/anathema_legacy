package net.sf.anathema.character.equipment.impl.creation.model.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.natural.DefaultNaturalSoak;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.main.testing.dummy.DummyEssenceCharacterType;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.main.testing.dummy.DummyMundaneCharacterType;
import org.junit.Assert;
import org.junit.Test;

public class NaturalSoakTest {

  @Test
  public void testSoakForMortals() {
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new DummyMundaneCharacterType(), null);
    Assert.assertEquals(Integer.valueOf(0), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(Integer.valueOf(2), naturalSoak.getSoak(HealthType.Bashing));
  }

  @Test
  public void testSoakForExalts() {
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new DummyExaltCharacterType(), null);
    Assert.assertEquals(new Integer(1), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak(HealthType.Bashing));
  }

  @Test
  public void testSoakForEssenceUsers() {
    DefaultNaturalSoak naturalSoak = new DefaultNaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new DummyEssenceCharacterType(), null);
    Assert.assertEquals(new Integer(1), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak(HealthType.Bashing));
  }
}