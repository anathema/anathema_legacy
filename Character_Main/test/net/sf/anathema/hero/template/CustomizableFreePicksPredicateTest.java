package net.sf.anathema.hero.template;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.template.magic.CustomizableFreePicksPredicate;
import net.sf.anathema.hero.dummy.magic.DummySpell;
import org.junit.Assert;
import org.junit.Test;

public class CustomizableFreePicksPredicateTest {

  @Test
  public void testDefaultTrue() throws Exception {
    Assert.assertTrue(new CustomizableFreePicksPredicate(true).apply(new DummySpell()));
  }

  @Test
  public void testDefaultFalse() throws Exception {
    Assert.assertFalse(new CustomizableFreePicksPredicate(false).apply(new DummySpell()));
  }

  @Test
  public void testIdException() throws Exception {
    CustomizableFreePicksPredicate predicate = new CustomizableFreePicksPredicate(true);
    String id = "Dummy";
    predicate.addIdException(id);
    Assert.assertFalse(predicate.apply(new DummyCharm(id)));
  }

  @Test
  public void testCharmGroupException() throws Exception {
    CustomizableFreePicksPredicate predicate = new CustomizableFreePicksPredicate(true);
    final String id = "DummyGroup";
    predicate.addCharmGroupException(id);
    Assert.assertFalse(predicate.apply(new DummyCharm("Dummy") {
      @Override
      public String getGroupId() {
        return id;
      }
    }));
  }
}