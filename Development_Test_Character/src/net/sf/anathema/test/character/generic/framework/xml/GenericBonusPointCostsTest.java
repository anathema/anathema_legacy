package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.lib.lang.clone.test.AbstractDeepCloneableTest;

public class GenericBonusPointCostsTest extends AbstractDeepCloneableTest {

  @Override
  protected GenericBonusPointCosts createObjectUnderTest() {
    return new GenericBonusPointCosts();
  }
}