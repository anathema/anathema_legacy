package net.sf.anathema.character.impl.model.advance.test;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.impl.magic.test.DummyMartialArtsCharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.impl.model.advance.CostAnalyzer;
import net.sf.anathema.character.impl.model.creation.bonus.test.DummyCoreTraitConfiguration;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.util.IIdentificate;

import org.easymock.MockControl;

public class CostAnalyzerTest extends BasicTestCase {

  private static final String CHARM_ID = "charmId"; //$NON-NLS-1$
  private DummyCoreTraitConfiguration dummyCoreTraitConfiguration;
  private DummyBasicCharacterData basicCharacterData;
  private CostAnalyzer costAnalyzer;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.dummyCoreTraitConfiguration = new DummyCoreTraitConfiguration();
    this.basicCharacterData = new DummyBasicCharacterData();
    this.costAnalyzer = new CostAnalyzer(basicCharacterData, dummyCoreTraitConfiguration, FavoringTraitType.AbilityType);
  }

  public void testIsFavoredMagicDelegatesToMagic() throws Exception {
    MockControl magicControl = MockControl.createStrictControl(IMagic.class);
    IMagic magic = (IMagic) magicControl.getMock();
    IBasicCharacterData trueCharacterData = new DummyBasicCharacterData();
    magicControl.expectAndReturn(magic.isFavored(
        trueCharacterData,
        dummyCoreTraitConfiguration,
        FavoringTraitType.AbilityType), true);
    IBasicCharacterData falseCharacterData = new DummyBasicCharacterData();
    magicControl.expectAndReturn(magic.isFavored(
        falseCharacterData,
        dummyCoreTraitConfiguration,
        FavoringTraitType.AbilityType), false);
    magicControl.replay();
    assertTrue(new CostAnalyzer(trueCharacterData, dummyCoreTraitConfiguration, FavoringTraitType.AbilityType).isMagicFavored(magic));
    assertFalse(new CostAnalyzer(falseCharacterData, dummyCoreTraitConfiguration, FavoringTraitType.AbilityType).isMagicFavored(magic));
    magicControl.verify();
  }

  public void testGetMartialArtsLevelFromMartialArtsCharm() throws Exception {
    assertEquals(MartialArtsLevel.Terrestrial, costAnalyzer.getMartialArtsLevel(new DummyMartialArtsCharm(CHARM_ID) {
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }));
  }

  public void testGetMartialArtsLevelFromNonMartialArtsCharm() throws Exception {
    assertNull(costAnalyzer.getMartialArtsLevel(new DummyCharm(CHARM_ID)));
  }
}