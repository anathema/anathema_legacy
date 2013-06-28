package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.magic.ICharm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmGroupTest {
  @Test
  public void equalsSelf() throws Exception {
    CharmGroup group = createGroupWithCharacterType();
    assertThat(group.equals(group), is(true));
  }

  @Test
  public void doesNotEqualSimilarGroup() throws Exception {
    CharmGroup group = createGroupWithCharacterType();
    CharmGroup group2 = createGroupWithCharacterType();
    assertThat(group.equals(group2), is(false));
  }

  private CharmGroup createGroupWithCharacterType() {
    return new CharmGroup(new DummyExaltCharacterType(), "AnyId", new ICharm[0], false);
  }
}