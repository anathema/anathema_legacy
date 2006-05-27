package net.sf.anathema.charmtree.batik.intvalue;

import org.w3c.dom.Element;

public class DomUtilities {

  public static void setAttribute(Element element, String attribute, String value) {
    element.setAttributeNS(null, attribute, value);
  }

  public static void setAttribute(Element element, String attribute, double value) {
    element.setAttributeNS(null, attribute, String.valueOf(value));
  }
}