package net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.FavorableAttributesEncoder;
import net.sf.anathema.development.reporting.util.IAttributesEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;

import org.dom4j.Element;

public class VoidstateBeastformAttributePageEncoder extends AbstractVoidstateAttributePageEncoder {
  private final VoidstateBasicsEncoder basicsEncoder;
  private final IAttributesEncoder attributesEncoder;

  public VoidstateBeastformAttributePageEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder) {
    this.basicsEncoder = basicsEncoder;
    this.attributesEncoder = new FavorableAttributesEncoder(traitEncoder);
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = createHorizontalRectangle(basicsEncoder);
    Rectangle verticalBoxRectangle = basicsEncoder.createOneColumnlBoxBoundsWithoutTitle(9, 1, new Point(0, 0));
    Rectangle verticalRectangle = basicsEncoder.encodeBoxWithoutHeader(bandElement, verticalBoxRectangle);
    Rectangle horizontalRectangle = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Attributes");
    fixRectangleBorders(bandElement);
    int attributeHeight = 0;
    attributeHeight = Math.max(attributeHeight, attributesEncoder.encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(AttributeGroupType.Physical),
        horizontalRectangle.y,
        horizontalRectangle.x,
        horizontalRectangle.width - 19,
        30));
    attributeHeight += TEXT_PADDING;
    attributeHeight += attributesEncoder.encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(AttributeGroupType.Social),
        verticalRectangle.y + attributeHeight,
        verticalRectangle.x,
        verticalRectangle.width - 4,
        10);
    attributeHeight += TEXT_PADDING;
    attributeHeight += attributesEncoder.encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(AttributeGroupType.Mental),
        verticalRectangle.y + attributeHeight,
        verticalRectangle.x,
        verticalRectangle.width - 4,
        10);
    return verticalBoxRectangle.height;
  }

  private void fixRectangleBorders(Element parent) {
    Rectangle rectangle = basicsEncoder.createCorrectedOneColumnBoxBoundsWithoutTitle(0, 0, new Point(
        1,
        LINE_HEIGHT * 4));
    rectangle.width -= 2;
    Element shapeElement = parent.addElement(TAG_RECTANGLE);
    Element reportElement = addReportElement(shapeElement, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    reportElement.addAttribute(ATTRIB_FORECOLOR, IJasperXmlConstants.VALUE_COLOR_WHITE);
    reportElement.addAttribute(ATTRIB_BACKCOLOR, IJasperXmlConstants.VALUE_COLOR_WHITE);
    reportElement.addAttribute(ATTRIB_POSITION_TYPE, VALUE_FIX_RELATIVE_TO_TOP);
    addGraphicsElement(shapeElement, VALUE_PEN_NONE);

    Rectangle rightBorderRepairRectangle = new Rectangle(rectangle);
    rightBorderRepairRectangle.width = 0;
    rightBorderRepairRectangle.x = 0;
    addLineElement(parent, rightBorderRepairRectangle, VALUE_PEN_THIN);

    Rectangle rightBorderStraightenRectangle = new Rectangle(rectangle);
    rightBorderStraightenRectangle.width = 0;
    addLineElement(parent, rightBorderStraightenRectangle, VALUE_PEN_THIN, false, Color.WHITE);

    Rectangle leftBorderRectangle = new Rectangle(rectangle);
    leftBorderRectangle.x = rectangle.width;
    leftBorderRectangle.width = 0;
    addLineElement(parent, leftBorderRectangle, VALUE_PEN_THIN, false, Color.WHITE);
  }

  public String getGroupName() {
    return "VoidstateBeastformAttributeSubreport";
  }

  public static int getOverlapHeight(VoidstateBasicsEncoder basicsEncoder) {
    return createHorizontalRectangle(basicsEncoder).height;
  }

  private static Rectangle createHorizontalRectangle(VoidstateBasicsEncoder basicsEncoder) {
    return basicsEncoder.createTwoColumnBoxBoundsWithoutTitle(2, 1, new Point(0, 0));
  }
}