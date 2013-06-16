package net.sf.anathema.character.main.templateparser;

import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.lib.testing.clone.AbstractDeepCloneableTest;

public class GenericPresentationTemplateTest extends AbstractDeepCloneableTest {

  @Override
  protected GenericPresentationTemplate createObjectUnderTest() {
    return new GenericPresentationTemplate();
  }
}