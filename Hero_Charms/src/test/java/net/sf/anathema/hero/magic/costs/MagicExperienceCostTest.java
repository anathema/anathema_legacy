package net.sf.anathema.hero.magic.costs;

import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceCosts;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsLevel;
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
    pointsTemplate.generalExperiencePoints.costs = 10;
    configureCostAnalyzer(null, false);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(10));
  }

  @Test
  public void calculatesCostsForFavoredCharm() throws Exception {
    pointsTemplate.favoredExperiencePoints.costs = 8;
    configureCostAnalyzer(null, true);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(8));
  }

  @Test
  public void calculatesGeneralCostsForUnfavoredMartialArtsCharmAtStandardLevel() throws Exception {
    pointsTemplate.generalExperiencePoints.costs = 9;
    configureCostAnalyzer(MartialArtsLevel.Celestial, false);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(9));
  }

  @Test
  public void calculatesGeneralCostsForFavoredMartialArtsCharmAtStandardLevel() throws Exception {
    pointsTemplate.favoredExperiencePoints.costs = 11;
    configureCostAnalyzer(MartialArtsLevel.Celestial, true);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(11));
  }

  @Test
  public void calculatesCostsForUnfavoredHighMartialArts() throws Exception {
    pointsTemplate.generalExperiencePoints.highMartialArtsCosts = 15;
    configureCostAnalyzer(MartialArtsLevel.Sidereal, false);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(15));
   }

  @Test
  public void calculatesCostsForFavoredHighMartialArts() throws Exception {
    pointsTemplate.favoredExperiencePoints.highMartialArtsCosts = 13;
    configureCostAnalyzer(MartialArtsLevel.Sidereal, true);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(13));
  }
}