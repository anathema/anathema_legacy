package net.sf.anathema.hero.template.parser;

import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.lib.testing.clone.AbstractDeepCloneableTest;

public class GenericCreationPointsTest extends AbstractDeepCloneableTest {

  @Override
  protected GenericCreationPoints createObjectUnderTest() {
    return new GenericCreationPoints();
  }
}