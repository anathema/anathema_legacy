package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.charm.ComboLearnTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExperienceComboEditingSupportTest {

  public static final ICharm charmInCombo = mock(ICharm.class);
  public static final ICharm charmNotInCombo = mock(ICharm.class);

  private ComboLearnTime learntime = mock(ComboLearnTime.class);
  private IExperiencePointConfiguration experience = mock(IExperiencePointConfiguration.class);
  private Combo editedCombo = new Combo();
  private ExperienceComboEditingSupport support = new ExperienceComboEditingSupport(experience, editedCombo, learntime);

  @Before
  public void prepareCharms() throws Exception {
    when(charmInCombo.getCharmTypeModel()).thenReturn(new CharmTypeModel());
    when(charmNotInCombo.getCharmTypeModel()).thenReturn(new CharmTypeModel());
  }

  @Test
  public void allowsDebtWhenFinalizingCombos() throws Exception {
    Combo originalCombo = new Combo();
    support.startChanging(originalCombo);
    editedCombo.addCharm(charmNotInCombo, true);
    assertThat(support.canFinalizeWithXP(), is(true));
  }
}