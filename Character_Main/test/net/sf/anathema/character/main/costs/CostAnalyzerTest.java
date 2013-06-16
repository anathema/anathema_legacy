package net.sf.anathema.character.main.costs;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.impl.model.advance.CostAnalyzer;
import net.sf.anathema.character.magic.dummy.DummyCharm;
import net.sf.anathema.character.main.testing.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.main.testing.dummy.trait.DummyCoreTraitConfiguration;
import net.sf.anathema.lib.util.Identifier;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class CostAnalyzerTest {

  private static final String CHARM_ID = "charmId";
  private DummyCoreTraitConfiguration dummyCoreTraitConfiguration = new DummyCoreTraitConfiguration();
  private DummyBasicCharacterData basicCharacterData = new DummyBasicCharacterData();
  private CostAnalyzer costAnalyzer = new CostAnalyzer(basicCharacterData, dummyCoreTraitConfiguration);

  @Test
  public void testIsFavoredMagicDelegatesToMagic() throws Exception {
    IMagic magic = Mockito.mock(IMagic.class);
    IBasicCharacterData trueCharacterData = new DummyBasicCharacterData();
    Mockito.when(magic.isFavored(trueCharacterData, dummyCoreTraitConfiguration)).thenReturn(true);
    IBasicCharacterData falseCharacterData = new DummyBasicCharacterData();
    when(magic.isFavored(falseCharacterData, dummyCoreTraitConfiguration)).thenReturn(false);
    assertTrue(new CostAnalyzer(trueCharacterData, dummyCoreTraitConfiguration).isMagicFavored(magic));
    assertFalse(new CostAnalyzer(falseCharacterData, dummyCoreTraitConfiguration).isMagicFavored(magic));
  }

  @Test
  public void testGetMartialArtsLevelFromMartialArtsCharm() throws Exception {
    assertEquals(MartialArtsLevel.Terrestrial, costAnalyzer.getMartialArtsLevel(new DummyCharm(CHARM_ID) {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial");
      }
    }));
  }

  @Test
  public void testGetMartialArtsLevelFromNonMartialArtsCharm() throws Exception {
    assertNull(costAnalyzer.getMartialArtsLevel(new DummyCharm(CHARM_ID)));
  }
}