package net.sf.anathema.hero.charms.sheet.encoder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import net.sf.anathema.hero.charms.sheet.content.IMagicStats;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.charms.sheet.content.CharmContentHelper;
import net.sf.anathema.hero.charms.sheet.content.GenericCharmContent;
import net.sf.anathema.hero.charms.sheet.content.GenericCharmContentHelper;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncodingMetrics;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TableCell;
import net.sf.anathema.hero.sheet.pdf.encoder.table.AbstractTableEncoder;
import net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

import java.util.Arrays;
import java.util.List;

public class GenericCharmTableEncoder extends AbstractTableEncoder<ReportSession> {

  private final Resources resources;

  public GenericCharmTableEncoder(Resources resources) {
    this.resources = resources;
  }

  public float getRequestedHeight(SheetGraphics graphics, float width, ReportSession reportSession) {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, reportSession);
    return new PreferredGenericCharmHeight().getValue(metrics, width);
  }

  private GenericCharmContent createContent(ReportSession session) {
    return session.createContent(GenericCharmContent.class);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return createContent(session).hasContent() && new GenericCharmContentHelper(session.getHero()).hasDisplayedGenericCharms();
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    PdfContentByte directContent = graphics.getDirectContent();
    CharmContentHelper helper = new CharmContentHelper(session.getHero());
    List<TraitType> traits = new GenericCharmContentHelper(session.getHero()).getGenericCharmTraits();
    Font font = graphics.createTableFont();
    PdfTemplate learnedTemplate = createCharmDotTemplate(directContent, BaseColor.BLACK);
    PdfTemplate notLearnedTemplate = createCharmDotTemplate(directContent, BaseColor.WHITE);
    PdfPTable table = new PdfPTable(createColumnWidths(traits.size() + 1));
    table.setWidthPercentage(100);
    table.addCell(new TableCell(new Phrase(), Rectangle.NO_BORDER));
    for (TraitType trait : traits) {
      table.addCell(createHeaderCell(graphics, directContent, trait));
    }
    for (IMagicStats stats : helper.getGenericCharmStats()) {
      if (!helper.shouldShowCharm(stats))
      	continue;
      Phrase charmPhrase = new Phrase(stats.getNameString(resources), font);
      table.addCell(new TableCell(charmPhrase, Rectangle.NO_BORDER));
      String genericId = stats.getName().getId();
      for (TraitType trait : traits) {
        table.addCell(createGenericCell(helper, trait, genericId, learnedTemplate, notLearnedTemplate));
      }
    }
    return table;
  }

  private PdfTemplate createCharmDotTemplate(PdfContentByte directContent, BaseColor color) {
    float lineWidth = 0.75f;
    float templateSize = IVoidStateFormatConstants.SMALL_SYMBOL_HEIGHT - 1 + 2 * lineWidth;
    PdfTemplate template = directContent.createTemplate(templateSize, templateSize);
    template.setColorFill(color);
    template.setColorStroke(BaseColor.BLACK);
    template.setLineWidth(lineWidth);
    float radius = templateSize / 2 - lineWidth;
    template.circle(templateSize / 2, templateSize / 2, radius);
    template.fillStroke();
    return template;
  }

  private PdfPCell createGenericCell(CharmContentHelper helper, TraitType type, String genericId, PdfTemplate learnedTemplate, PdfTemplate notLearnedTemplate) throws DocumentException {
    final String charmId = genericId + "." + type.getId();
    boolean isLearned = helper.isGenericCharmLearned(charmId);
    Image image = Image.getInstance(isLearned ? learnedTemplate : notLearnedTemplate);
    TableCell tableCell = new TableCell(image);
    tableCell.setPadding(0);
    return tableCell;
  }

  private PdfPCell createHeaderCell(SheetGraphics graphics, PdfContentByte directContent, TraitType abilityType) throws DocumentException {
    directContent.setColorStroke(BaseColor.BLACK);
    directContent.setColorFill(BaseColor.BLACK);
    String text = resources.getString(abilityType.getId());
    if (text.length() >= IVoidStateFormatConstants.TYPE_LONG_FORM_CUTOFF) {
      text = resources.getString(abilityType.getId() + ".Short");
    }
    BaseFont baseFont = graphics.getBaseFont();
    float ascentPoint = baseFont.getAscentPoint(text, IVoidStateFormatConstants.TABLE_FONT_SIZE);
    float descentPoint = baseFont.getDescentPoint(text, IVoidStateFormatConstants.TABLE_FONT_SIZE);
    float templateWidth = baseFont.getWidthPoint(text, IVoidStateFormatConstants.TABLE_FONT_SIZE);
    float templateHeight = ascentPoint - descentPoint;

    PdfTemplate template = directContent.createTemplate(templateWidth, templateHeight);
    template.beginText();
    template.setFontAndSize(baseFont, IVoidStateFormatConstants.TABLE_FONT_SIZE);
    template.showTextAligned(Element.ALIGN_LEFT, text, 0, -descentPoint, 0);
    template.endText();
    Image image = Image.getInstance(template);
    image.setRotationDegrees(90);
    TableCell tableCell = new TableCell(image);
    tableCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    tableCell.setPaddingTop(5);
    return tableCell;
  }

  private float[] createColumnWidths(int columnCount) {
    float[] columnWidths = new float[columnCount];
    Arrays.fill(columnWidths, 1);
    columnWidths[0] = (int) (.4 * columnCount);
    return columnWidths;
  }
}
