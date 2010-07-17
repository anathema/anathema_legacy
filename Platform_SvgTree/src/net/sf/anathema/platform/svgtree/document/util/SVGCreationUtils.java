package net.sf.anathema.platform.svgtree.document.util;

import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.XMLConstants;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class SVGCreationUtils {

  private static final Namespace svgNamespace = new Namespace(null, SVGConstants.SVG_NAMESPACE_URI);
  private static final Namespace xlinkNamespace = new Namespace("xlink", XMLConstants.XLINK_NAMESPACE_URI); //$NON-NLS-1$ //$NON-NLS-2$

  public static QName createXLinkQName() {
    return new QName(XMLConstants.XLINK_HREF_ATTRIBUTE, xlinkNamespace);
  }

  public static QName createSVGQName(String elementName) {
    return new QName(elementName, svgNamespace);
  }
}