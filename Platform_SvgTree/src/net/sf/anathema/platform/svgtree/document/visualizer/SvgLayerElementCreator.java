package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;

public class SvgLayerElementCreator {
  public Element createXml(ILayer... layers) {
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element cascadeElement = new DefaultElement(group);
    for (ILayer layer : layers) {
      layer.addNodesToXml(cascadeElement);
    }
    for (ILayer layer : layers) {
      layer.addArrowsToXml(cascadeElement);
    }
    return cascadeElement;
  }
}