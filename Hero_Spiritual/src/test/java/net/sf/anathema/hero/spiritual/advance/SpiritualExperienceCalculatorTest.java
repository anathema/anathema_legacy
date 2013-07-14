package net.sf.anathema.hero.spiritual.advance;

import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.points.MultiplyRatingCosts;
import net.sf.anathema.hero.dummy.trait.DummyTrait;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.hero.spiritual.advance.experience.SpiritualExperienceCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpiritualExperienceCalculatorTest {

  private IExperiencePointCosts experienceCosts = mock(IExperiencePointCosts.class);
  private SpiritualExperienceCalculator calculator = new SpiritualExperienceCalculator(experienceCosts);

  @Test
  public void testEssenceCosts() throws Exception {
    when(experienceCosts.getEssenceCosts()).thenReturn(new MultiplyRatingCosts(8));
    assertEquals(16, calculator.getEssenceCosts(DummyTrait.createLearnTrait(OtherTraitType.Essence, 2, 3)));
  }

  @Test
  public void testVirtueCosts() throws Exception {
    when(experienceCosts.getVirtueCosts()).thenReturn(new MultiplyRatingCosts(5));
    assertEquals(15, calculator.getVirtueCosts(DummyTrait.createLearnTrait(VirtueType.Compassion, 3, 4)));
  }

  @Test
  public void testWillpowerCosts() throws Exception {
    when(experienceCosts.getWillpowerCosts()).thenReturn(new MultiplyRatingCosts(1));
    assertEquals(3, calculator.getWillpowerCosts(DummyTrait.createLearnTrait(OtherTraitType.Willpower, 3, 4)));
  }
}