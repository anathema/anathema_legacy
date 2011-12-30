package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfGenericCharmTableEncoder extends AbstractTableEncoder<ReportContent> {

  public static final int TYPE_LONGFORM_CUTOFF = 20;

  private final IResources resources;
  private final BaseFont baseFont;

  public PdfGenericCharmTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public float getRequestedHeight(ReportContent content) {
    float traitHeight = 0;
    for (ITraitType trait : getTraits(content.getCharacter())) {
      String text = resources.getString(trait.getId());
      if (text.length() >= TYPE_LONGFORM_CUTOFF) {
        text = resources.getString(trait.getId() + ".Short");
      }

      float height = baseFont.getWidthPoint(text, TableEncodingUtilities.TABLE_FONT_SIZE) + 5.794f;
      if (height > traitHeight) {
        traitHeight = height;
      }
    }
    return traitHeight + content.getCharacter().getGenericCharmStats().length * IVoidStateFormatConstants.LINE_HEIGHT;
  }

  public boolean hasContent(ReportContent content) {
    return content.getCharacter().getGenericCharmStats().length > 0;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    IGenericCharacter character = content.getCharacter();
    PdfContentByte directContent = graphics.getDirectContent();
    List<ITraitType> traits = getTraits(character);
    Font font = TableEncodingUtilities.createTableFont(baseFont);
    PdfTemplate learnedTemplate = createCharmDotTemplate(directContent, Color.BLACK);
    PdfTemplate notLearnedTemplate = createCharmDotTemplate(directContent, Color.WHITE);
    PdfPTable table = new PdfPTable(createColumnWidths(traits.size() + 1));
    table.setWidthPercentage(100);
    table.addCell(new TableCell(new Phrase(), Rectangle.NO_BORDER));
    for (ITraitType trait : traits) {
      table.addCell(createHeaderCell(directContent, trait));
    }
    for (IMagicStats stats : character.getGenericCharmStats()) {
      Phrase charmPhrase = new Phrase(stats.getNameString(resources), font);
      table.addCell(new TableCell(charmPhrase, Rectangle.NO_BORDER));
      String genericId = stats.getName().getId();
      for (ITraitType trait : traits) {
        table.addCell(createGenericCell(character, trait, genericId, learnedTemplate, notLearnedTemplate));
      }
    }
    return table;
  }

  private List<ITraitType> getTraits(IGenericCharacter character) {
    FavoringTraitType type = character.getTemplate().getMagicTemplate().getFavoringTraitType();
    List<ITraitType> traits = new ArrayList<ITraitType>();
    IIdentifiedTraitTypeGroup[] list = null;
    if (type == FavoringTraitType.AbilityType) {
      list = character.getAbilityTypeGroups();
    }
    if (type == FavoringTraitType.AttributeType) {
      list = character.getAttributeTypeGroups();
    }
    if (type == FavoringTraitType.YoziType) {
      list = character.getYoziTypeGroups();
    }

    for (ITraitTypeGroup group : list) {
      for (ITraitType trait : group.getAllGroupTypes()) {
        traits.add(trait);
      }
    }

    return traits;
  }

  private PdfTemplate createCharmDotTemplate(PdfContentByte directContent, Color color) {
    float lineWidth = 0.75f;
    float templateSize = IVoidStateFormatConstants.SMALL_SYMBOL_HEIGHT - 1 + 2 * lineWidth;
    PdfTemplate template = directContent.createTemplate(templateSize, templateSize);
    template.setColorFill(color);
    template.setColorStroke(Color.BLACK);
    template.setLineWidth(lineWidth);
    float radius = templateSize / 2 - lineWidth;
    template.circle(templateSize / 2, templateSize / 2, radius);
    template.fillStroke();
    return template;
  }

  private PdfPCell createGenericCell(IGenericCharacter character, ITraitType type, String genericId, PdfTemplate learnedTemplate,
    PdfTemplate notLearnedTemplate) throws DocumentException {
    final String charmId = genericId + "." + type.getId(); //$NON-NLS-1$
    List<IMagic> allLearnedMagic = character.getAllLearnedMagic();
    boolean isLearned = CollectionUtilities.find(allLearnedMagic, new IPredicate<IMagic>() {
      public boolean evaluate(IMagic value) {
        return charmId.equals(value.getId());
      }
    }) != null;
    Image image = Image.getInstance(isLearned ? learnedTemplate : notLearnedTemplate);
    TableCell tableCell = new TableCell(image);
    tableCell.setPadding(0);
    return tableCell;
  }

  private PdfPCell createHeaderCell(PdfContentByte directContent, ITraitType abilityType) throws DocumentException {
    directContent.setColorStroke(Color.BLACK);
    directContent.setColorFill(Color.BLACK);
    String text = resources.getString(abilityType.getId());
    if (text.length() >= TYPE_LONGFORM_CUTOFF) {
      text = resources.getString(abilityType.getId() + ".Short");
    }

    float ascentPoint = baseFont.getAscentPoint(text, TableEncodingUtilities.TABLE_FONT_SIZE);
    float descentPoint = baseFont.getDescentPoint(text, TableEncodingUtilities.TABLE_FONT_SIZE);
    float templateWidth = baseFont.getWidthPoint(text, TableEncodingUtilities.TABLE_FONT_SIZE);
    float templateHeight = ascentPoint - descentPoint;

    PdfTemplate template = directContent.createTemplate(templateWidth, templateHeight);
    template.beginText();
    template.setFontAndSize(baseFont, TableEncodingUtilities.TABLE_FONT_SIZE);
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
