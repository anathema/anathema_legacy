package net.sf.anathema.hero.magic.costs;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostsTto;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCosts;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicCostTest {

  private MagicCreationCostsTto tto = new MagicCreationCostsTto();
  private DummyCharm charm = new DummyCharm("test");
  private CostAnalyzer costAnalyzerMock = mock(CostAnalyzer.class);

  @Test
  public void calculatesHighCostsForUnfavoredMartialArtsCharmHigherThanStandardLevel() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
    tto.general.highLevelMartialArtsCost = 10;
    configureCostAnalyzer(MartialArtsLevel.Celestial, false);
    assertCharmCosts(10);
   }

  @Test
  public void calculatesAlwaysHighCostsForSiderealMartialArts() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Sidereal;
    tto.general.highLevelMartialArtsCost = 10;
    configureCostAnalyzer(MartialArtsLevel.Sidereal, false);
    assertCharmCosts(10);
  }

  @Test
  public void calculatesHighCostsForFavoredMartialArtsCharmHigherThanStandardLevel() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
    tto.favored.highLevelMartialArtsCost = 9;
    configureCostAnalyzer(MartialArtsLevel.Celestial, true);
    assertCharmCosts(9);
  }

  @Test
  public void calculatesNonNormalFavoredCharmCostsForFavoredMartialArtsOfStandardLevel() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
    tto.favored.charmCost = 5;
    configureCostAnalyzer(null, true);
    assertCharmCosts(5);
  }

  @Test
  public void calculatesNormalUnfavoredCharmCostsForFavoredMartialArtsOfStandardLevel() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
    tto.general.charmCost = 6;
    configureCostAnalyzer(null, false);
    assertCharmCosts(6);
  }

  private void assertCharmCosts(int expectedValue) {
    MagicCreationCosts magicCosts = new MagicCreationCosts(tto);
    int charmCost = magicCosts.getMagicCosts(charm, costAnalyzerMock);
    assertThat(expectedValue, is(charmCost));
  }

  private void configureCostAnalyzer(MartialArtsLevel level, boolean favored) {
    when(costAnalyzerMock.getMartialArtsLevel((Magic) any())).thenReturn(level);
    when(costAnalyzerMock.isMagicFavored((Magic) any())).thenReturn(favored);
  }
}
