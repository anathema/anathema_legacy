package net.sf.anathema.hero.magic.costs;

import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.model.Magic;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCosts;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsLevel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicCreationCostTest {

  private MagicPointsTemplate template = new MagicPointsTemplate();
  private DummyCharm charm = new DummyCharm("test");
  private CostAnalyzer costAnalyzerMock = mock(CostAnalyzer.class);

  @Test
  public void calculatesHighCostsForUnfavoredMartialArtsCharmHigherThanStandardLevel() throws Exception {
    template.generalCreationPoints.highMartialArtsCosts = 10;
    configureCostAnalyzer(MartialArtsLevel.Celestial, false);
    assertCharmCosts(10, MartialArtsLevel.Terrestrial);
   }

  @Test
  public void calculatesAlwaysHighCostsForSiderealMartialArts() throws Exception {
    template.generalCreationPoints.highMartialArtsCosts = 10;
    configureCostAnalyzer(MartialArtsLevel.Sidereal, false);
    assertCharmCosts(10, MartialArtsLevel.Sidereal);
  }

  @Test
  public void calculatesHighCostsForFavoredMartialArtsCharmHigherThanStandardLevel() throws Exception {
    template.favoredCreationPoints.highMartialArtsCosts = 9;
    configureCostAnalyzer(MartialArtsLevel.Celestial, true);
    assertCharmCosts(9, MartialArtsLevel.Terrestrial);
  }

  @Test
  public void calculatesNonNormalFavoredCharmCostsForFavoredMartialArtsOfStandardLevel() throws Exception {
    template.favoredCreationPoints.costs = 5;
    configureCostAnalyzer(null, true);
    assertCharmCosts(5, MartialArtsLevel.Terrestrial);
  }

  @Test
  public void calculatesNormalUnfavoredCharmCostsForFavoredMartialArtsOfStandardLevel() throws Exception {
    template.generalCreationPoints.costs = 6;
    configureCostAnalyzer(null, false);
    assertCharmCosts(6, MartialArtsLevel.Terrestrial);
  }

  private void assertCharmCosts(int expectedValue, MartialArtsLevel standardMartialArtsLevel) {
    MagicCreationCosts magicCosts = new MagicCreationCosts(template, standardMartialArtsLevel);
    int charmCost = magicCosts.getMagicCosts(charm, costAnalyzerMock);
    assertThat(expectedValue, is(charmCost));
  }

  private void configureCostAnalyzer(MartialArtsLevel level, boolean favored) {
    when(costAnalyzerMock.getMartialArtsLevel((Magic) any())).thenReturn(level);
    when(costAnalyzerMock.isMagicFavored((Magic) any())).thenReturn(favored);
  }
}
