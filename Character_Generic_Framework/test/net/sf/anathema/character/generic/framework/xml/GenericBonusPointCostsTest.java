package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.lib.testing.clone.AbstractDeepCloneableTest;

public class GenericBonusPointCostsTest extends AbstractDeepCloneableTest {

  @Override
  protected GenericBonusPointCosts createObjectUnderTest() {
    return new GenericBonusPointCosts();
  }
}