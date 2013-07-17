package net.sf.anathema.hero.magic.model.rules;

import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.rules.CharmsRulesImpl;
import net.sf.anathema.hero.charms.model.rules.MartialArtsRules;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.dummy.DummyCasteType;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.testing.CharmObjectMother;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CharmRulesTest {

  private CharmsTemplate template = new CharmsTemplate();
  private DummyCharm celestialMartialArmsCharm = CharmObjectMother.createMartialArtsCharm(MartialArtsLevel.Celestial);
  private DummyCharm siderealMartialArmsCharm = CharmObjectMother.createMartialArtsCharm(MartialArtsLevel.Sidereal);

  @Test
  public void createsStandardMartialArtsLevelFromString() throws Exception {
    template.martialArts.standardLevel = "Mortal";
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.getMartialArtsRules().getStandardLevel(), is(MartialArtsLevel.Mortal));
  }

  @Test
  public void cannotLearnCharmsForCharmTypeNone() throws Exception {
    template.charmType = "None";
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.canLearnCharms(), is(false));
  }

  @Test
  public void canLearnCharmsForCharmTypeSolar() throws Exception {
    template.charmType = "Solar";
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.canLearnCharms(), is(true));
  }

  @Test
  public void forbidsAllowHighLevelMartialArtsWhenNotConfigured() throws Exception {
    template.martialArts.highLevelAtCreation = false;
    template.martialArts.standardLevel = "Mortal";
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.getMartialArtsRules().isCharmAllowed(celestialMartialArmsCharm, false), is(false));
  }

  @Test
  public void allowsAllowHighLevelMartialArtsWhenConfigured() throws Exception {
    template.martialArts.highLevelAtCreation = true;
    template.martialArts.standardLevel = "Terrestrial";
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    MartialArtsRules martialArtsRules = charmsRules.getMartialArtsRules();
    boolean charmAllowed = martialArtsRules.isCharmAllowed(celestialMartialArmsCharm, false);
    assertThat(charmAllowed, is(true));
  }

  @Test
  public void forbidsSuperHighMartialArts() throws Exception {
    template.martialArts.highLevelAtCreation = true;
    template.martialArts.standardLevel = "Terrestrial";
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.getMartialArtsRules().isCharmAllowed(siderealMartialArmsCharm, false), is(false));
  }

  @Test
  public void allowsAlienCharmsForConfiguredCastes() throws Exception {
    template.alienCharmCastes.add("AlienAllowed");
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.isAllowedAlienCharms(new DummyCasteType("AlienAllowed")), is(true));
  }

  @Test
  public void forbidsAlienCharmsForNotConfiguredCastes() throws Exception {
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.isAllowedAlienCharms(new DummyCasteType("AlienForbidden")), is(false));
  }
}
