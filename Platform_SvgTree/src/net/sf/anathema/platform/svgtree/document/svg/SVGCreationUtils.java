package net.sf.anathema.platform.svgtree.document.svg;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class SVGCreationUtils {

  private static final Namespace svgNamespace = new Namespace(null, SVGDOMImplementation.SVG_NAMESPACE_URI);
  private static final Namespace xlinkNamespace = new Namespace("xlink", "http://www.w3.org/1999/xlink"); //$NON-NLS-1$ //$NON-NLS-2$

  public static QName createXLinkQName() {
    return new QName(SVGConstants.SVG_HREF_ATTRIBUTE, xlinkNamespace);
  }

  public static QName createSVGQName(String elementName) {
    return new QName(elementName, svgNamespace);
  }
}