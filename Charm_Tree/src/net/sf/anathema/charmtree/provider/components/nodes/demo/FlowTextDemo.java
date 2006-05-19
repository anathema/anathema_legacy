package net.sf.anathema.charmtree.provider.components.nodes.demo;

import java.awt.Dimension;
import java.io.IOException;

import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;
import net.sf.anathema.charmtree.batik.AnathemaCanvas;
import net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants;
import net.sf.anathema.charmtree.provider.svg.SVGCreationUtils;
import net.sf.anathema.charmtree.provider.svg.SVGDocumentFrameFactory;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.apache.batik.dom.svg12.SVG12DOMImplementation;
import org.apache.batik.util.SVG12Constants;
import org.apache.batik.util.SVGConstants;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.DOMWriter;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.svg.SVGDocument;

import de.jdemo.extensions.SwingDemoCase;

public class FlowTextDemo extends SwingDemoCase {
  public void demo() throws DocumentException, IOException {
    org.dom4j.Document document = new SVGDocumentFrameFactory().createFrame(new ICharmPresentationProperties() {
      public Dimension getCharmDimension() {
        return new Dimension(200, 100);
      }

      public String getCharmFramePolygonString() {
        return "15,15 20,20 15,15";
      }

      public Dimension getGapDimension() {
        return new Dimension(50, 25);
      }
    });
    Element rootElement = document.getRootElement();
    addFlowTextElement(rootElement);
    DocumentUtilities.save(document, System.out);
    DOMImplementation implementation = SVG12DOMImplementation.getDOMImplementation();
    SVGDocument svgDocument = (SVGDocument) new DOMWriter().write(document, implementation);
    AnathemaCanvas canvas = new AnathemaCanvas();
    canvas.setDocument(svgDocument);
    show(canvas);
  }

  private void addFlowTextElement(Element element) {
    QName textName = SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_ROOT_TAG);
    Element flowText = element.addElement(textName);
    Element region = flowText.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_REGION_TAG));
    Element regionRect = region.addElement(SVGCreationUtils.createSVGQName(SVGConstants.SVG_RECT_TAG));
    regionRect.addAttribute(SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(100));
    regionRect.addAttribute(SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(100));
    regionRect.addAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(200));
    regionRect.addAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(100));
    regionRect.addAttribute("visibility", "hidden");
    Element flowDiv = flowText.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_DIV_TAG));
    Element paragraph = flowDiv.addElement(SVGCreationUtils.createSVGQName(SVG12Constants.SVG_FLOW_PARA_TAG));
    paragraph.addAttribute(SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_15);
    paragraph.addAttribute("text-align", SVGConstants.SVG_MIDDLE_VALUE);
    paragraph.addAttribute("margin-top", "10");
    paragraph.addAttribute("margin-bottom", "10");
    paragraph.addAttribute("margin-left", "10");
    paragraph.addAttribute("margin-right", "10");
    paragraph.setText("This is my text that will flow brilliantly like a Solar Dodge Charm");
  }
}