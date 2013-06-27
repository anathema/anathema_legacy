package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.generic.impl.magic.charm.CharmGroup;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmGroupTest {
  @Test
  public void equalsCharmGroupWithMatchingCharacterTypeThatIsNotIdentical() throws Exception {
    CharmGroup group1 = createGroupWithCharacterType(new DummyExaltCharacterType());
    CharmGroup group2 = createGroupWithCharacterType(new DummyExaltCharacterType());
    assertThat(group1.equals(group2), is(true));
  }

  private CharmGroup createGroupWithCharacterType(DummyExaltCharacterType type) {
    return new CharmGroup(type, "AnyId", new ICharm[0], false);
  }
}