package net.sf.anathema.character.equipment.impl.creation.model.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;

import org.junit.Assert;
import org.junit.Test;

public class NaturalSoakTest {

  @Test
  public void testSoakForMortals() {
    NaturalSoak naturalSoak = new NaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new ICharacterType() {
      @Override
      public void accept(ICharacterTypeVisitor abstractSupportedCharacterTypeVisitor) {
        // Nothing to do
      }

      @Override
      public boolean isExaltType() {
        return false;
      }

      @Override
      public String getId() {
        return "Mortal"; //$NON-NLS-1$
      }

      @Override
      public int compareTo(CharacterType o) {
        // Since returning an arbitrary value would cause
        // surprising result, the compareTo must not be used.
        throw new UnsupportedOperationException();
      }

      @Override
      public FavoringTraitType getFavoringTraitType() {
        // TODO Auto-generated method stub
        return null;
      }
    });
    Assert.assertEquals(Integer.valueOf(0), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(Integer.valueOf(2), naturalSoak.getSoak(HealthType.Bashing));
  }

  @Test
  public void testSoakForExalts() {
    NaturalSoak naturalSoak = new NaturalSoak(new ValuedTraitType(AttributeType.Stamina, 2), new ICharacterType() {
      @Override
      public void accept(ICharacterTypeVisitor abstractSupportedCharacterTypeVisitor) {
        // Nothing to do
      }

      @Override
      public boolean isExaltType() {
        return true;
      }

      @Override
      public String getId() {
        return "Mortal"; //$NON-NLS-1$
      }

      @Override
      public int compareTo(CharacterType o) {
        // Nothing to do
        return 0;
      }

      @Override
      public FavoringTraitType getFavoringTraitType() {
        // TODO Auto-generated method stub
        return null;
      }
    });
    Assert.assertEquals(new Integer(1), naturalSoak.getSoak(HealthType.Lethal));
    Assert.assertEquals(new Integer(2), naturalSoak.getSoak(HealthType.Bashing));
  }
}