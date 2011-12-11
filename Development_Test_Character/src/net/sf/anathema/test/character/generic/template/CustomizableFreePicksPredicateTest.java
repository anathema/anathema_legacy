package net.sf.anathema.test.character.generic.template;

import net.sf.anathema.character.generic.dummy.magic.DummySpell;
import net.sf.anathema.character.generic.impl.template.magic.CustomizableFreePicksPredicate;
import net.sf.anathema.dummy.character.magic.DummyCharm;

import org.junit.Assert;
import org.junit.Test;

public class CustomizableFreePicksPredicateTest {

  @Test
  public void testDefaultTrue() throws Exception {
    Assert.assertTrue(new CustomizableFreePicksPredicate(true).evaluate(new DummySpell()));
  }

  @Test
  public void testDefaultFalse() throws Exception {
    Assert.assertFalse(new CustomizableFreePicksPredicate(false).evaluate(new DummySpell()));
  }

  @Test
  public void testIdException() throws Exception {
    CustomizableFreePicksPredicate predicate = new CustomizableFreePicksPredicate(true);
    String id = "Dummy"; //$NON-NLS-1$
    predicate.addIdException(id);
    Assert.assertFalse(predicate.evaluate(new DummyCharm(id)));
  }

  @Test
  public void testCharmGroupException() throws Exception {
    CustomizableFreePicksPredicate predicate = new CustomizableFreePicksPredicate(true);
    final String id = "DummyGroup"; //$NON-NLS-1$
    predicate.addCharmGroupException(id);
    Assert.assertFalse(predicate.evaluate(new DummyCharm("Dummy") { //$NON-NLS-1$
      @Override
      public String getGroupId() {
        return id;
      }
    }));
  }
}