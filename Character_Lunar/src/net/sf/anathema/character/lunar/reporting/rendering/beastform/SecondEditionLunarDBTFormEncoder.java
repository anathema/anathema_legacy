package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.lunar.reporting.rendering.GiftEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionLunarDBTFormEncoder implements IBoxContentEncoder {

  private final String notes = "Sheet.Lunar.WarForm";
  private final static int PHYSICAL_MAX = 15;
  private final IResources resources;
  private final PdfTraitEncoder smallTraitEncoder;
  private final BaseFont baseFont;
  private final float lineHeight = IVoidStateFormatConstants.LINE_HEIGHT - 4;

  public SecondEditionLunarDBTFormEncoder(BaseFont baseFont, IResources resources, float smallWidth) {
    this.resources = resources;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
    this.baseFont = baseFont;
  }

  public String getHeaderKey(ReportContent content) {
    return "Lunar.WarForm"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {

    IGroupedTraitType[] attributeGroups = reportContent.getCharacter().getTemplate().getAttributeGroups();
    IBeastformModel additionalModel = (IBeastformModel) reportContent.getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    encodeAttributes(graphics, bounds, attributeGroups, traitCollection);
    encodeNotes(graphics, bounds);
    encodeMutations(graphics, bounds, reportContent);
  }

  private final void encodeNotes(SheetGraphics graphics, Bounds bounds) {
    final float offsetX = 0;
    final float offsetY = 42;
    final int numNotes = 4;
    try {
      for (int i = 1; i <= numNotes; i++) {
        writeLine(graphics, resources.getString(notes + ".Note" + i), bounds, offsetX, offsetY + lineHeight * (i - 1));
      }
    }
    catch (DocumentException e) {
    }
  }

  private final void writeLine(SheetGraphics graphics, String text, Bounds bounds, float offsetX, float offsetY) throws DocumentException {
    Font font = TableEncodingUtilities.createFont(baseFont);
    Bounds newBounds = new Bounds(bounds.x + offsetX, bounds.y + bounds.height - offsetY, bounds.width / 2 - offsetX, lineHeight);
    font.setSize(IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), new Phrase(text, font), newBounds, lineHeight);
  }

  private final void encodeMutations(SheetGraphics graphics, Bounds bounds, ReportContent content) {
    final int horizontalSpacing = 15;
    final int verticalSpacing = 5;
    Bounds newBounds = new Bounds(bounds.x + bounds.getWidth() * 1 / 2 + horizontalSpacing, bounds.y + verticalSpacing,
      bounds.getWidth() * 1 / 2 - horizontalSpacing, bounds.height - 2 * verticalSpacing);
    IBoxContentEncoder encoder = new GiftEncoder(baseFont, resources);
    try {
      new PdfBoxEncoder(resources, baseFont).encodeBox(content, graphics, encoder, newBounds);
    }
    catch (DocumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private final void encodeAttributes(SheetGraphics graphics, Bounds contentBounds, IGroupedTraitType[] attributeGroups,
    IGenericTraitCollection traitCollection) {
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - 2 * groupSpacing;
    int maximum = PHYSICAL_MAX;
    float width = contentBounds.getWidth() * 1 / 2;
    for (IGroupedTraitType groupedTraitType : attributeGroups) {
      if (!groupedTraitType.getGroupId().equals(AttributeGroupType.Physical.name())) {
        continue;
      }

      ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = resources.getString("AttributeType.Name." + traitType.getId()); //$NON-NLS-1$
      int value = traitCollection.getTrait(traitType).getCurrentValue();
      Position position = new Position(contentBounds.x, y);
      y -= smallTraitEncoder.encodeWithText(graphics, traitLabel, position, width, value, maximum);
    }
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
