package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.lib.lang.clone.test.AbstractDeepCloneableTest;

public class GenericPresentationTemplateTest extends AbstractDeepCloneableTest {

  @Override
  protected GenericPresentationTemplate createObjectUnderTest() {
    return new GenericPresentationTemplate();
  }
}