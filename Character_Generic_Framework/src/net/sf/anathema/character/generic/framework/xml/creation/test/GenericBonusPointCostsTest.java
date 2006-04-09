package net.sf.anathema.character.generic.framework.xml.creation.test;

import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.lib.lang.clone.ICloneable;
import net.sf.anathema.lib.lang.clone.test.AbstractDeepCloneableTest;

public class GenericBonusPointCostsTest extends AbstractDeepCloneableTest {

  @Override
  protected ICloneable createObjectUnderTest() {
    return new GenericBonusPointCosts();
  }
}