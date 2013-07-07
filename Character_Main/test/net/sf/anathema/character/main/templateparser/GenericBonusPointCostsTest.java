package net.sf.anathema.character.main.templateparser;

import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.lib.testing.clone.AbstractDeepCloneableTest;

public class GenericBonusPointCostsTest extends AbstractDeepCloneableTest {

  @Override
  protected GenericBonusPointCosts createObjectUnderTest() {
    return new GenericBonusPointCosts();
  }
}