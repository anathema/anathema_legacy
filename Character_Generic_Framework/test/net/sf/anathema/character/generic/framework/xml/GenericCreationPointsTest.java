package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.lib.testing.clone.AbstractDeepCloneableTest;

public class GenericCreationPointsTest extends AbstractDeepCloneableTest {

  @Override
  protected GenericCreationPoints createObjectUnderTest() {
    return new GenericCreationPoints();
  }
}