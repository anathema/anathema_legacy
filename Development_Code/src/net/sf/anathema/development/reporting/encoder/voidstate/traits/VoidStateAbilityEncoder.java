package net.sf.anathema.development.reporting.encoder.voidstate.traits;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.AbstractVoidstateAbilitySetPageEncoder;
import net.sf.anathema.development.reporting.util.AbilitiesEncoder;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;

import org.dom4j.Element;

public class VoidStateAbilityEncoder extends AbstractJasperEncoder implements IVoidStateFormatConstants {
  private final AbilitiesEncoder abilitiesEncoder;

  private final IOneColumnEncoder basicsEncoder;
  private final TraitEncoder traitEncoder;

  public VoidStateAbilityEncoder(IOneColumnEncoder basicsEncoder, TraitEncoder traitEncoder) {
    this.basicsEncoder = basicsEncoder;
    this.traitEncoder = traitEncoder;
    this.abilitiesEncoder = new AbilitiesEncoder(traitEncoder);
  }

  public int encodeAbilites(Element bandElement, Point position) {
    Rectangle boxRectangle = calculateAbilityExtents(position);
    Rectangle textRectangle = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Abilities");
    Rectangle abilityRectangle = AbstractVoidstateAbilitySetPageEncoder.calculateExtents(basicsEncoder);
    abilityRectangle.y = textRectangle.y;
    String subreportParameterName = IAbilityReportConstants.SUBREPORT_ABILITY_SET;
    encodeSubreport(bandElement, abilityRectangle, subreportParameterName);
    int abilityHeight = abilityRectangle.height;
    abilityHeight += encodeSpecialties(bandElement, textRectangle, abilityHeight);
    encodeCrossComment(bandElement, textRectangle, abilityHeight);
    return boxRectangle.height;
  }

  public Rectangle calculateAbilityExtents(Point position) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(30, 5, position);
  }

  private int encodeCrossComment(Element bandElement, Rectangle textBounds, int yOffset) {
    traitEncoder.encodeCross(bandElement, textBounds.x, textBounds.y + yOffset + 4);
    String comment = quotify(": This ability is commonly affected by mobility penalty");
    Rectangle commentBounds = new Rectangle(textBounds.x + 7, textBounds.y + yOffset, textBounds.width, LINE_HEIGHT);
    addTextElement(bandElement, comment, FONT_SIZE - 2, VALUE_LEFT, commentBounds);
    return LINE_HEIGHT;
  }

  private int encodeSpecialties(Element bandElement, Rectangle textBounds, int yOffset) {
    int originalOffsetY = yOffset;
    Point headerPosition = new Point(textBounds.x, textBounds.y + yOffset);
    yOffset += basicsEncoder.addCentralHeader(bandElement, headerPosition, textBounds.width, "Specialties");
    Point specialtiesPosition = new Point(textBounds.x, textBounds.y + yOffset);
    yOffset += abilitiesEncoder.encodeSpecialties(bandElement, specialtiesPosition, textBounds.width, 5, 3);
    return yOffset - originalOffsetY;
  }
}