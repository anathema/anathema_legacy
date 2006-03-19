package net.sf.anathema.character.generic.framework.xml.presentation.test;

import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.lib.lang.clone.ICloneable;
import net.sf.anathema.lib.lang.clone.test.AbstractDeepCloneableTest;

public class GenericPresentationTemplateTest extends AbstractDeepCloneableTest {

  @Override
  protected ICloneable createObjectUnderTest() {
    return new GenericPresentationTemplate();
  }
}