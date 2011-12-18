package net.sf.anathema.character.reporting.pdf.rendering.boxes.social;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class SocialCombatStatsBoxEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final Font commentFont;
  private final Font font;

  public SocialCombatStatsBoxEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.font = TableEncodingUtilities.createFont(baseFont);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "SocialCombat"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    IEquipmentModifiers equipment = reportContent.getCharacter().getEquipmentModifiers();
    float valueWidth = graphics.getBounds().width;
    Bounds valueBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, valueWidth, graphics.getBounds().height);
    float valueHeight = encodeValues(graphics.getDirectContent(), valueBounds, reportContent.getCharacter().getTraitCollection(), equipment);
    Bounds attackTableBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, valueWidth, graphics.getBounds().height - valueHeight);

    ITableEncoder tableEncoder = new SocialCombatStatsTableEncoder(resources, baseFont);
    float attackHeight = tableEncoder.encodeTable(graphics.getDirectContent(), reportContent, attackTableBounds);
    Bounds actionBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeActionTable(graphics.getDirectContent(), actionBounds);
    final float center = graphics.getBounds().x + valueWidth / 2f;
    Bounds commentBounds = new Bounds(center + 4, graphics.getBounds().y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeDVTable(graphics.getDirectContent(), commentBounds);
    graphics.getDirectContent().moveTo(center, graphics.getBounds().y + 6 * IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    graphics.getDirectContent().lineTo(center, graphics.getBounds().y + 3);
  }

  private void encodeDVTable(PdfContentByte directContent, Bounds bounds) throws DocumentException {
    float[] columnWidths = new float[]{4, 5};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = resources.getString("Sheet.SocialCombat.DVModifiers.Header"); //$NON-NLS-1$
    final TableCell headerCell = createCommonActionsCell(new Phrase(header, font));
    headerCell.setColspan(columnWidths.length);
    headerCell.setPaddingTop(1.5f);
    table.addCell(headerCell);
    String actionSubheader = resources.getString("Sheet.SocialCombat.DVModifiers.Source"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(actionSubheader, commentFont)));
    String speedSubheader = resources.getString("Sheet.SocialCombat.DVModifiers.Modifier"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(speedSubheader, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    createCommonDVRow(table, "Appearance"); //$NON-NLS-1$
    createCommonDVRow(table, "Motivation"); //$NON-NLS-1$
    createCommonDVRow(table, "Virtue"); //$NON-NLS-1$
    createCommonDVRow(table, "Intimacy"); //$NON-NLS-1$
    ColumnText tableColumn = new ColumnText(directContent);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
  }

  private void createCommonDVRow(PdfPTable table, String sourceId) {
    String sourceName = resources.getString("Sheet.SocialCombat.DVModifiers." + sourceId + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(sourceName, commentFont)));
    String dvModifier = resources.getString("Sheet.SocialCombat.DVModifiers." + sourceId + ".DV"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(dvModifier, commentFont)));
  }

  private float encodeActionTable(PdfContentByte directContent, Bounds bounds) throws DocumentException {
    float[] columnWidths = new float[]{4, 2.5f, 2f};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = resources.getString("Sheet.SocialCombat.CommonActions.Header"); //$NON-NLS-1$
    final TableCell headerCell = createCommonActionsCell(new Phrase(header, font));
    headerCell.setColspan(columnWidths.length);
    headerCell.setPaddingTop(1.5f);
    table.addCell(headerCell);
    String actionSubheader = resources.getString("Sheet.SocialCombat.CommonActions.Action"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(actionSubheader, commentFont)));
    String speedSubheader = resources.getString("Sheet.SocialCombat.CommonActions.Speed"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(speedSubheader, commentFont)));
    String dvPenSubheader = resources.getString("Sheet.SocialCombat.CommonActions.DV"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(dvPenSubheader, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    addCommonActionsRow(table, "JoinDebate"); //$NON-NLS-1$
    addCommonActionsRow(table, "Attack"); //$NON-NLS-1$
    addCommonActionsRow(table, "Monologue"); //$NON-NLS-1$
    addCommonActionsRow(table, "Misc"); //$NON-NLS-1$
    ColumnText tableColumn = new ColumnText(directContent);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
    return table.getTotalHeight();
  }

  private void addCommonActionsRow(PdfPTable table, String actionId) {
    String actionName = resources.getString("Sheet.SocialCombat.CommonActions." + actionId + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionName, commentFont)));
    String actionSpeed = resources.getString("Sheet.SocialCombat.CommonActions." + actionId + ".Speed"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionSpeed, commentFont)));
    String actionDV = resources.getString("Sheet.SocialCombat.CommonActions." + actionId + ".DV"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionDV, commentFont)));
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }

  private float encodeValues(PdfContentByte directContent, Bounds bounds, IGenericTraitCollection traitCollection, IEquipmentModifiers equipment) {
    String joinLabel = resources.getString("Sheet.SocialCombat.JoinDebateBattle"); //$NON-NLS-1$
    String dodgeLabel = resources.getString("Sheet.SocialCombat.DodgeMDV"); //$NON-NLS-1$
    int joinDebate = CharacterUtilties.getJoinDebate(traitCollection, equipment);
    int dodgeMDV = CharacterUtilties.getDodgeMdv(traitCollection, equipment);
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 2, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(directContent, 0, joinLabel, joinDebate);
    encoder.addLabelledValue(directContent, 1, dodgeLabel, dodgeMDV);
    return encoder.getHeight() + 1;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
