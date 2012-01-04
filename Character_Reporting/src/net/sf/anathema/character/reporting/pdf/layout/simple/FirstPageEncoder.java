package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.AbstractPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ABILITIES_WITH_CRAFTS_AND_SPECIALTIES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ANIMA;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ATTRIBUTES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBAT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GREAT_CURSE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.HEALTH_AND_MOVEMENT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.INTIMACIES_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.MERITS_AND_FLAWS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.NOTES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PANOPLY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PERSONAL_INFO;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.SOCIAL_COMBAT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.VIRTUES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.WILLPOWER_SIMPLE;

public class FirstPageEncoder extends AbstractPageEncoder {
  private static final float ANIMA_HEIGHT = 128;
  private static final int ATTRIBUTE_HEIGHT = 128;
  private static final int FIRST_ROW_HEIGHT = 51;
  private static final int VIRTUE_HEIGHT = 72;
  private static final int SOCIAL_COMBAT_HEIGHT = 115;
  private static final int WILLPOWER_HEIGHT = 43;
  private static final int ARMOUR_HEIGHT = 80;
  private static final int HEALTH_HEIGHT = 99;
  private final PageConfiguration configuration;

  public FirstPageEncoder(EncoderRegistry encoders, IResources resources, PageConfiguration configuration) {
    super(resources, encoders);
    this.configuration = configuration;
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    LayoutField personalInfo = encodeBox(graphics, content, createStartField(FIRST_ROW_HEIGHT, 2), PERSONAL_INFO);
    LayoutField essence = encodeBox(graphics, content, personalInfo.placeOnRightSide(), ESSENCE_SIMPLE);
    LayoutField attributes = encodeBox(graphics, content, personalInfo.placeBelowWithHeight(ATTRIBUTE_HEIGHT), ATTRIBUTES);
    encodeBox(graphics, content, attributes.fillRemainingColumnBelow(), ABILITIES_WITH_CRAFTS_AND_SPECIALTIES);
    LayoutField anima = encodeBox(graphics, content, essence.placeBelowWithHeight(ANIMA_HEIGHT), ANIMA);
    LayoutField social = encodeBox(graphics, content, anima.placeBelowWithHeight(SOCIAL_COMBAT_HEIGHT), SOCIAL_COMBAT, MERITS_AND_FLAWS);
    LayoutField virtues = encodeBox(graphics, content, attributes.placeOnRightSideWithHeight(VIRTUE_HEIGHT), VIRTUES);
    LayoutField greatCurse = encodeBox(graphics, content, virtues.placeBelowBottomAlignedTo(anima), GREAT_CURSE);
    LayoutField willpower = encodeBox(graphics, content, greatCurse.placeBelowWithHeight(WILLPOWER_HEIGHT), WILLPOWER_SIMPLE);
    LayoutField intimacies = encodeBox(graphics, content, willpower.placeBelowBottomAlignedTo(social), INTIMACIES_SIMPLE, NOTES);
    float preferredArsenalHeight = getPreferredEncoderHeight(content, ARSENAL);
    LayoutField arsenal = encodeBox(graphics, content, intimacies.placeDoubleColumnBelowWithHeight(preferredArsenalHeight), ARSENAL);
    LayoutField panoply = encodeBox(graphics, content, arsenal.placeDoubleColumnBelowWithHeight(ARMOUR_HEIGHT), PANOPLY);
    LayoutField health = encodeBox(graphics, content, panoply.placeDoubleColumnBelowWithHeight(HEALTH_HEIGHT), HEALTH_AND_MOVEMENT);
    encodeBox(graphics, content, health.fillRemainingDoubleColumnBelow(), COMBAT);
    new CopyrightEncoder(configuration, CONTENT_HEIGHT).encodeCopyright(graphics);
  }

  private LayoutField createStartField(float height, int columnSpan) {
    return LayoutField.CreateUpperLeftFieldWithHeightAndColumnSpan(configuration, height, columnSpan, CONTENT_HEIGHT);
  }
}
