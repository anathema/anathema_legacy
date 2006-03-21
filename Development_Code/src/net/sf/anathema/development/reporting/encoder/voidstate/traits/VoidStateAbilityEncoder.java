package net.sf.anathema.development.reporting.encoder.voidstate.traits;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.VoidstateAbilitySetPageEncoder;
import net.sf.anathema.development.reporting.util.AbilitiesEncoder;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.framework.reporting.encoding.TextEncoding;

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

  public int encodeAbilites(Element bandElement, ICharacterTemplate template, Point position) {
    Rectangle boxRectangle = calculateAbilityExtents(position);
    Rectangle textRectangle = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Abilities");

    Rectangle abilityRectangle = VoidstateAbilitySetPageEncoder.calculateExtents(basicsEncoder);
    abilityRectangle.y = textRectangle.y;
    String subreportParameterName = IAbilityReportConstants.SUBREPORT_ABILITY_SET;
    encodeSubreport(bandElement, abilityRectangle, subreportParameterName);
    int abilityHeight = abilityRectangle.height;
    // abilityHeight += TEXT_PADDING;
    abilityHeight += encodeSpecialties(bandElement, textRectangle, abilityHeight);
    encodeCrossComment(bandElement, textRectangle, abilityHeight);
    return boxRectangle.height;
  }

  public Rectangle calculateAbilityExtents(Point position) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(30, 5, position);
  }

  private int encodeAbilityGroup(
      Element bandElement,
      Rectangle textBounds,
      int yOffset,
      IIdentifiedTraitTypeGroup abilityGroup) {
    int originalYOffset = yOffset;
    Point groupPosition = new Point(textBounds.x + LINE_HEIGHT, textBounds.y + yOffset);
    int groupWidth = textBounds.width - LINE_HEIGHT;
    int groupHeight = abilitiesEncoder.encodeAbilityGroup(bandElement, abilityGroup, groupPosition, groupWidth, true);
    Element textField = TextEncoding.addTextFieldElement(bandElement);
    addReportElement(textField, textBounds.x, textBounds.y + yOffset, LINE_HEIGHT, groupHeight);
    TextEncoding.addVerticalTextElement(textField, FONT_SIZE, VALUE_CENTER);
    // NOTE: Größere Fontsize wirft den gesamten Bogen durcheinander. Warum?
    TextEncoding.addTextFieldExpression(textField, quotify((abilityGroup.getGroupId().getId())));
    yOffset += groupHeight;
    return yOffset - originalYOffset;
  }

  private int encodeCrossComment(Element bandElement, Rectangle textBounds, int yOffset) {
    traitEncoder.addCross(bandElement, textBounds.x, textBounds.y + yOffset + 4);
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