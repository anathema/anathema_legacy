package net.sf.anathema.character.main.trait;

import net.sf.anathema.character.generic.impl.traits.limitation.AgeBasedLimitation;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.model.IntegerDescription;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AgeBasedLimitationTest {

  private DummyHero hero = new DummyHero();
  private int absoluteLimit = 10;
  private AgeBasedLimitation limitation = new AgeBasedLimitation(absoluteLimit);

  @Test
  public void limitsTo5ForAgeBelow100() throws Exception {
    assertThatMaximumForAgeIs(99, 5);
  }

  @Test
  public void limitsTo6ForAgeBelow250() throws Exception {
    assertThatMaximumForAgeIs(249, 6);
  }

  @Test
  public void limitsTo7ForAgeBelow500() throws Exception {
    assertThatMaximumForAgeIs(499, 7);
  }

  @Test
  public void limitsTo8ForAgeBelow1000() throws Exception {
    assertThatMaximumForAgeIs(999, 8);
  }

  @Test
  public void doesNotLimitFrom1000YearsOnward() throws Exception {
    assertThatMaximumForAgeIs(1000, absoluteLimit);
  }

  private void assertThatMaximumForAgeIs(int age, int value) {
    HeroConcept concept = mock(HeroConcept.class);
    when(concept.getId()).thenReturn(HeroConcept.ID);
    when(concept.getAge()).thenReturn(new IntegerDescription(age));
    hero.addModel(concept);
    assertThat(limitation.getCurrentMaximum(hero, false), is(value));
  }
}
