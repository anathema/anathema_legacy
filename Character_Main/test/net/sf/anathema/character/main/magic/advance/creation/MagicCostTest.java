package net.sf.anathema.character.main.magic.advance.creation;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttributeImpl;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.character.main.template.experience.CostAnalyzer;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostsTto;
import net.sf.anathema.hero.magic.advance.creation.MagicCostsImpl;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicCostTest {

  private MagicCreationCostsTto tto = new MagicCreationCostsTto();
  private DummyCharm charm = new DummyCharm("test");
  private CostAnalyzer costAnalyzer = mock(CostAnalyzer.class);

  @Test
  public void calculatesHighCostsForUnfavoredMartialArtsCharmHigherThanStandardLevel() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
    tto.general.highLevelMartialArtsCost = 10;
    makeMartialArtsCharmOfLevel(MartialArtsLevel.Celestial);
    when(costAnalyzer.isMagicFavored(charm)).thenReturn(false);
    assertCharmCosts(10);
   }

  @Test
  public void calculatesAlwaysHighCostsForSiderealMartialArts() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Sidereal;
    tto.general.highLevelMartialArtsCost = 10;
    makeMartialArtsCharmOfLevel(MartialArtsLevel.Sidereal);
    when(costAnalyzer.isMagicFavored(charm)).thenReturn(false);
    assertCharmCosts(10);
  }

  @Test
  public void calculatesHighCostsForFavoredMartialArtsCharmHigherThanStandardLevel() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
    tto.favored.highLevelMartialArtsCost = 9;
    makeMartialArtsCharmOfLevel(MartialArtsLevel.Celestial);
    when(costAnalyzer.isMagicFavored(charm)).thenReturn(true);
    assertCharmCosts(9);
  }

  @Test
  public void calculatesNormalFavoredCharmCostsForFavoredMartialArtsOfStandardLevel() throws Exception {
    tto.standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
    tto.favored.charmCost = 5;
    makeMartialArtsCharmOfLevel(MartialArtsLevel.Terrestrial);
    when(costAnalyzer.isMagicFavored(charm)).thenReturn(true);
    assertCharmCosts(5);
  }

  private void assertCharmCosts(int expectedValue) {
    int charmCost = new MagicCostsImpl(tto).getMagicCosts(charm, costAnalyzer);
    assertThat(expectedValue, is(charmCost));
  }

  private void makeMartialArtsCharmOfLevel(MartialArtsLevel martialArtsLevel) {
    charm.attributes.add(new MagicAttributeImpl(AbilityType.MartialArts.getId(), false));
    charm.attributes.add(new MagicAttributeImpl(martialArtsLevel.getId(), false));
  }
}
