package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class BasicTemplateParsingTestCase extends BasicTestCase {

  protected final Element getDocumentRoot(String xml) throws AnathemaException {
    return DocumentUtilities.read(xml).getRootElement();
  }
}