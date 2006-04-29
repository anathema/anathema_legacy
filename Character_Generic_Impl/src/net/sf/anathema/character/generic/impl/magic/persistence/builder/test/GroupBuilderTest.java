package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.HeaderStringBuilder;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GroupBuilderTest extends BasicTestCase {

  private HeaderStringBuilder builder = new HeaderStringBuilder("group");

  public void testBuildGroup() throws Exception {
    String xml = "<charm group=\"test\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String group = builder.build(rootElement);
    assertEquals("test", group); //$NON-NLS-1$
  }
}
