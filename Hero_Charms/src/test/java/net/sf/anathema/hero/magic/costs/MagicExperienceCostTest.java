package net.sf.anathema.hero.magic.costs;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceCosts;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicExperienceCostTest {

  private MagicPointsTemplate pointsTemplate = new MagicPointsTemplate();
  private MagicExperienceCosts experienceCosts = new MagicExperienceCosts(pointsTemplate, MartialArtsLevel.Celestial);
  private CostAnalyzer costAnalyzerMock = mock(CostAnalyzer.class);
  private DummyCharm charm = new DummyCharm("Charm");

  private void configureCostAnalyzer(MartialArtsLevel martialArtsLevel, boolean favored) {
    when(costAnalyzerMock.getMartialArtsLevel(charm)).thenReturn(martialArtsLevel);
    when(costAnalyzerMock.isMagicFavored(charm)).thenReturn(favored);
  }

  @Test
  public void calculatesCostsForGeneralCharm() throws Exception {
    pointsTemplate.generalPoints.experienceCosts = 10;
    configureCostAnalyzer(null, false);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(10));
  }

  @Test
  public void calculatesCostsForFavoredCharm() throws Exception {
    pointsTemplate.favoredPoints.experienceCosts = 8;
    configureCostAnalyzer(null, true);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(8));
  }

  @Test
  public void calculatesGeneralCostsForUnfavoredMartialArtsCharmAtStandardLevel() throws Exception {
    pointsTemplate.generalPoints.experienceCosts = 9;
    configureCostAnalyzer(MartialArtsLevel.Celestial, false);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(9));
  }

  @Test
  public void calculatesGeneralCostsForFavoredMartialArtsCharmAtStandardLevel() throws Exception {
    pointsTemplate.favoredPoints.experienceCosts = 11;
    configureCostAnalyzer(MartialArtsLevel.Celestial, true);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(11));
  }

  @Test
  public void calculatesCostsForUnfavoredHighMartialArts() throws Exception {
    pointsTemplate.generalPoints.highMartialArtsExperience = 15;
    configureCostAnalyzer(MartialArtsLevel.Sidereal, false);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(15));
   }

  @Test
  public void calculatesCostsForFavoredHighMartialArts() throws Exception {
    pointsTemplate.favoredPoints.highMartialArtsExperience = 13;
    configureCostAnalyzer(MartialArtsLevel.Sidereal, true);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(13));
  }
}