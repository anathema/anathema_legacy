package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.generic.magic.charms.MartialArtsLevel.Celestial;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TerrestrialMartialArtsRules_AlreadyCompletedTest {
  public static final String Incomplete_Group_Id = "Incomplete Masters' Blessing";
  public static final String Complete_Group_Id = "Completed Harmony";
  private final TerrestrialMartialArtsRules rules = new TerrestrialMartialArtsRules();
  private final MartialArtsCharmConfiguration configuration = mock(MartialArtsCharmConfiguration.class);

  @Before
  public void allowHighLevelAtCreation() throws Exception {
    rules.setHighLevelAtCreation(true);
  }

  @Before
  public void learnEnlighteningCharm() {
    DummyCharm enlighteningCharm = new DummyCharm();
    enlighteningCharm.addKeyword(new CharmAttribute(ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE.getId(), false));
    when(configuration.getLearnedCharms()).thenReturn(new ICharm[]{enlighteningCharm});
  }

  @Before
  public void completeOneStyle() throws Exception {
    when(configuration.getCompleteCelestialMartialArtsGroups()).thenReturn(new String[]{Complete_Group_Id});
  }

  @Before
  public void startLearningAnySingleStyle() {
    when(configuration.getIncompleteCelestialMartialArtsGroups()).thenReturn(new String[]{Incomplete_Group_Id});
  }

  @Test
  public void allowsAnyCharmFromACompletedCelestialStyleEvenIfAnotherCelestialStyleIsAlreadyBegun() throws Exception {
    DummyCharm charm = createCelestialMartialArtsCharmFromCompleteGroup();
    boolean allowed = rules.isCharmAllowed(charm, configuration, false);
    assertThat(allowed, is(true));
  }

  private DummyCharm createCelestialMartialArtsCharmFromCompleteGroup() {
    DummyCharm charm = new DummyCharm();
    charm.setGroupId(Complete_Group_Id);
    charm.addKeyword(new CharmAttribute(MartialArtsUtilities.MARTIAL_ARTS.getId(), false));
    charm.addKeyword(new CharmAttribute(Celestial.getId(), false));
    return charm;
  }
}