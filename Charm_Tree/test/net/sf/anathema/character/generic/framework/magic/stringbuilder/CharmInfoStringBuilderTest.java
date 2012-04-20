package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.impl.rules.SourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.description.AggregatedMagicDescription;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.resources.IResources;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmInfoStringBuilderTest {

  DummyCharm charm = new DummyCharm("id");
  MagicDescriptionProvider provider = mock(MagicDescriptionProvider.class);

  @Before
  public void fillCharmWithData() {
    charm.setTemporaryCost(new CostList(Cost.NULL_COST, Cost.NULL_COST, HealthCost.NULL_HEALTH_COST, Cost.NULL_COST));
    charm.setDuration(SimpleDuration.getDuration("x"));
    charm.setCharmType(CharmType.ExtraAction);
    charm.setPrerequisites(new ValuedTraitType[0]);
    charm.setEssence(new ValuedTraitType(OtherTraitType.Essence, 5));
    charm.setSource(new SourceBook("Book"));
  }

  @Before
  public void connectCharmAndDescription() {
    when(provider.getCharmDescription(charm)).thenReturn(
            new AggregatedMagicDescription(Lists.<MagicDescription>newArrayList()));
  }

  @Test
  public void cachesStringsOnceRequested() throws Exception {
    IResources resources = mock(IResources.class);
    CharmInfoStringBuilder charmInfoStringBuilder = new CharmInfoStringBuilder(resources, provider);
    String firstResult = charmInfoStringBuilder.getInfoString(charm, null);
    String secondResult = charmInfoStringBuilder.getInfoString(charm, null);
    assertThat(firstResult, is(sameInstance(secondResult)));
  }
}
