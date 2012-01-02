package net.sf.anathema.character.reporting.pdf.rendering.boxes.social;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;

public class SocialCombatStatsBoxEncoder implements IBoxContentEncoder {

  private final IResources resources;

  public SocialCombatStatsBoxEncoder(IResources resources) {
    this.resources = resources;
  }

  public String getHeaderKey(ReportContent content) {
    return "SocialCombat"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    IEquipmentModifiers equipment = reportContent.getCharacter().getEquipmentModifiers();
    float valueWidth = bounds.width;
    Bounds valueBounds = new Bounds(bounds.x, bounds.y, valueWidth, bounds.height);
    float valueHeight = encodeValues(graphics, valueBounds, reportContent.getCharacter().getTraitCollection(), equipment);
    Bounds attackTableBounds = new Bounds(bounds.x, bounds.y, valueWidth, bounds.height - valueHeight);

    ITableEncoder tableEncoder = new SocialCombatStatsTableEncoder(resources);
    float attackHeight = tableEncoder.encodeTable(graphics, reportContent, attackTableBounds);
    Bounds actionBounds = new Bounds(bounds.x, bounds.y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeActionTable(graphics, actionBounds);
    final float center = bounds.x + valueWidth / 2f;
    Bounds commentBounds = new Bounds(center + 4, bounds.y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeDVTable(graphics, commentBounds);
    Position lineStart = new Position(center, bounds.y + 3);
    float lineEndY = bounds.y + 6 * COMMENT_FONT_SIZE;
    graphics.createVerticalLineByCoordinate(lineStart, lineEndY).encode();
  }

  private void encodeDVTable(SheetGraphics graphics, Bounds bounds) throws DocumentException {
    Font font = graphics.createTableFont();
    Font commentFont = graphics.createCommentFont();
    float[] columnWidths = new float[] { 4, 5 };
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = resources.getString("Sheet.SocialCombat.DVModifiers.Header"); //$NON-NLS-1$
    final TableCell headerCell = createCommonActionsCell(new Phrase(header, font));
    headerCell.setColspan(columnWidths.length);
    headerCell.setPaddingTop(1.5f);
    table.addCell(headerCell);
    String actionSubHeader = resources.getString("Sheet.SocialCombat.DVModifiers.Source"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(actionSubHeader, commentFont)));
    String speedSubHeader = resources.getString("Sheet.SocialCombat.DVModifiers.Modifier"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(speedSubHeader, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    createCommonDVRow(graphics, table, "Appearance"); //$NON-NLS-1$
    createCommonDVRow(graphics, table, "Motivation"); //$NON-NLS-1$
    createCommonDVRow(graphics, table, "Virtue"); //$NON-NLS-1$
    createCommonDVRow(graphics, table, "Intimacy"); //$NON-NLS-1$
    graphics.createSimpleColumn(bounds).withElement(table).encode();
  }

  private void createCommonDVRow(SheetGraphics graphics, PdfPTable table, String sourceId) {
    Font commentFont = graphics.createCommentFont();
    String sourceName = resources.getString("Sheet.SocialCombat.DVModifiers." + sourceId + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(sourceName, commentFont)));
    String dvModifier = resources.getString("Sheet.SocialCombat.DVModifiers." + sourceId + ".DV"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(dvModifier, commentFont)));
  }

  private float encodeActionTable(SheetGraphics graphics, Bounds bounds) throws DocumentException {
    Font font = graphics.createTableFont();
    Font commentFont = graphics.createCommentFont();
    float[] columnWidths = new float[] { 4, 2.5f, 2f };
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
    addCommonActionsRow(graphics, table, "JoinDebate"); //$NON-NLS-1$
    addCommonActionsRow(graphics, table, "Attack"); //$NON-NLS-1$
    addCommonActionsRow(graphics, table, "Monologue"); //$NON-NLS-1$
    addCommonActionsRow(graphics, table, "Misc"); //$NON-NLS-1$
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  private void addCommonActionsRow(SheetGraphics graphics, PdfPTable table, String actionId) {
    Font commentFont = graphics.createCommentFont();
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

  private float encodeValues(SheetGraphics graphics, Bounds bounds, IGenericTraitCollection traitCollection, IEquipmentModifiers equipment) {
    String joinLabel = resources.getString("Sheet.SocialCombat.JoinDebateBattle"); //$NON-NLS-1$
    String dodgeLabel = resources.getString("Sheet.SocialCombat.DodgeMDV"); //$NON-NLS-1$
    int joinDebate = CharacterUtilties.getJoinDebate(traitCollection, equipment);
    int dodgeMDV = CharacterUtilties.getDodgeMdv(traitCollection, equipment);
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(2, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(graphics, 0, joinLabel, joinDebate);
    encoder.addLabelledValue(graphics, 1, dodgeLabel, dodgeMDV);
    return encoder.getHeight() + 1;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
