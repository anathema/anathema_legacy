package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

public abstract class BasicTemplateParsingTestCase {

  protected final Element getDocumentRoot(String xml) throws AnathemaException {
    return DocumentUtilities.read(xml).getRootElement();
  }
}