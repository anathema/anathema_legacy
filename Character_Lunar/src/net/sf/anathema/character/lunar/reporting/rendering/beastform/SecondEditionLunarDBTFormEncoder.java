package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.lunar.reporting.rendering.GiftEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionLunarDBTFormEncoder implements ContentEncoder {

  private static final String NOTES = "Sheet.Lunar.WarForm";
  private final static int PHYSICAL_MAX = 15;
  private final IResources resources;
  private final PdfTraitEncoder smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
  private static final float LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 4;

  public SecondEditionLunarDBTFormEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {

    IGroupedTraitType[] attributeGroups = reportContent.getCharacter().getTemplate().getAttributeGroups();
    IBeastformModel additionalModel = (IBeastformModel) reportContent.getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    encodeAttributes(graphics, bounds, attributeGroups, traitCollection);
    encodeNotes(graphics, bounds);
    encodeMutations(graphics, bounds, reportContent);
  }

  private void encodeNotes(SheetGraphics graphics, Bounds bounds) {
    float offsetX = 0;
    float offsetY = 42;
    int numNotes = 4;
    try {
      for (int i = 1; i <= numNotes; i++) {
        writeLine(graphics, resources.getString(NOTES + ".Note" + i), bounds, offsetX, offsetY + LINE_HEIGHT * (i - 1));
      }
    } catch (DocumentException e) {
    }
  }

  private void writeLine(SheetGraphics graphics, String text, Bounds bounds, float offsetX, float offsetY) throws DocumentException {
    Font font = graphics.createTableFont();
    Bounds newBounds = new Bounds(bounds.x + offsetX, bounds.y + bounds.height - offsetY, bounds.width / 2 - offsetX, LINE_HEIGHT);
    font.setSize(IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    graphics.createSimpleColumn(newBounds).withLeading(LINE_HEIGHT).andTextPart(new Phrase(text, font)).encode();
  }

  private void encodeMutations(SheetGraphics graphics, Bounds bounds, ReportContent content) {
    int horizontalSpacing = 15;
    int verticalSpacing = 5;
    Bounds newBounds = new Bounds(bounds.x + bounds.getWidth() * 1 / 2 + horizontalSpacing, bounds.y + verticalSpacing, bounds.getWidth() * 1 / 2 - horizontalSpacing, bounds.height - 2 * verticalSpacing);
    ContentEncoder encoder = new GiftEncoder();
    try {
      new PdfBoxEncoder().encodeBox(content, graphics, encoder, newBounds);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void encodeAttributes(SheetGraphics graphics, Bounds contentBounds, IGroupedTraitType[] attributeGroups, IGenericTraitCollection traitCollection) {
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

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Lunar.WarForm");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
