package net.sf.anathema.character.reporting.pdf.rendering.boxes.social;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilities;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.library.trait.specialties.HighestSpecialty;
import net.sf.anathema.character.main.model.traits.GenericTraitCollectionFacade;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.second.content.combat.StatsModifiers;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;

public class SocialCombatStatsBoxEncoder implements ContentEncoder {

  private final Resources resources;

  public SocialCombatStatsBoxEncoder(Resources resources) {
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    ICharacterStatsModifiers modifiers = StatsModifiers.allStatsModifiers(reportSession.getHero());
    float valueWidth = bounds.width;
    Bounds valueBounds = new Bounds(bounds.x, bounds.y + 3, valueWidth, bounds.height);
    float valueHeight = encodeValues(graphics, valueBounds, reportSession.getHero(), modifiers) - 5;
    Bounds attackTableBounds = new Bounds(bounds.x, bounds.y, valueWidth, bounds.height - valueHeight);

    ITableEncoder tableEncoder = new SocialCombatStatsTableEncoder(resources);
    float attackHeight = tableEncoder.encodeTable(graphics, reportSession, attackTableBounds);
    Bounds actionBounds = new Bounds(bounds.x, bounds.y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeActionTable(graphics, actionBounds);
    float center = bounds.x + valueWidth / 2f;
    Bounds commentBounds = new Bounds(center + 4, bounds.y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeDVTable(graphics, commentBounds);
    Position lineStart = new Position(center, bounds.y + 3);
    float lineEndY = bounds.y + 6 * COMMENT_FONT_SIZE;
    graphics.createVerticalLineByCoordinate(lineStart, lineEndY).encode();
  }

  private void encodeDVTable(SheetGraphics graphics, Bounds bounds) throws DocumentException {
    Font font = graphics.createTableFont();
    Font commentFont = graphics.createCommentFont();
    float[] columnWidths = new float[]{4, 5};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = resources.getString("Sheet.SocialCombat.DVModifiers.Header");
    TableCell headerCell = createCommonActionsCell(new Phrase(header, font));
    headerCell.setColspan(columnWidths.length);
    headerCell.setPaddingTop(1.5f);
    table.addCell(headerCell);
    String actionSubHeader = resources.getString("Sheet.SocialCombat.DVModifiers.Source");
    table.addCell(createCommonActionsCell(new Phrase(actionSubHeader, commentFont)));
    String speedSubHeader = resources.getString("Sheet.SocialCombat.DVModifiers.Modifier");
    table.addCell(createCommonActionsCell(new Phrase(speedSubHeader, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont)));
    createCommonDVRow(graphics, table, "Appearance");
    createCommonDVRow(graphics, table, "Motivation");
    createCommonDVRow(graphics, table, "Virtue");
    createCommonDVRow(graphics, table, "Intimacy");
    graphics.createSimpleColumn(bounds).withElement(table).encode();
  }

  private void createCommonDVRow(SheetGraphics graphics, PdfPTable table, String sourceId) {
    Font commentFont = graphics.createCommentFont();
    String sourceName = resources.getString("Sheet.SocialCombat.DVModifiers." + sourceId + ".Name");
    table.addCell(createCommonActionsCell(new Phrase(sourceName, commentFont)));
    String dvModifier = resources.getString("Sheet.SocialCombat.DVModifiers." + sourceId + ".DV");
    table.addCell(createCommonActionsCell(new Phrase(dvModifier, commentFont)));
  }

  private float encodeActionTable(SheetGraphics graphics, Bounds bounds) throws DocumentException {
    Font font = graphics.createTableFont();
    Font commentFont = graphics.createCommentFont();
    float[] columnWidths = new float[]{4, 2.5f, 2f};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = resources.getString("Sheet.SocialCombat.CommonActions.Header");
    TableCell headerCell = createCommonActionsCell(new Phrase(header, font));
    headerCell.setColspan(columnWidths.length);
    headerCell.setPaddingTop(1.5f);
    table.addCell(headerCell);
    String actionSubheader = resources.getString("Sheet.SocialCombat.CommonActions.Action");
    table.addCell(createCommonActionsCell(new Phrase(actionSubheader, commentFont)));
    String speedSubheader = resources.getString("Sheet.SocialCombat.CommonActions.Speed");
    table.addCell(createCommonActionsCell(new Phrase(speedSubheader, commentFont)));
    String dvPenSubheader = resources.getString("Sheet.SocialCombat.CommonActions.DV");
    table.addCell(createCommonActionsCell(new Phrase(dvPenSubheader, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont)));
    addCommonActionsRow(graphics, table, "JoinDebate");
    addCommonActionsRow(graphics, table, "Attack");
    addCommonActionsRow(graphics, table, "Monologue");
    addCommonActionsRow(graphics, table, "Misc");
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  private void addCommonActionsRow(SheetGraphics graphics, PdfPTable table, String actionId) {
    Font commentFont = graphics.createCommentFont();
    String actionName = resources.getString("Sheet.SocialCombat.CommonActions." + actionId + ".Name");
    table.addCell(createCommonActionsCell(new Phrase(actionName, commentFont)));
    String actionSpeed = resources.getString("Sheet.SocialCombat.CommonActions." + actionId + ".Speed");
    table.addCell(createCommonActionsCell(new Phrase(actionSpeed, commentFont)));
    String actionDV = resources.getString("Sheet.SocialCombat.CommonActions." + actionId + ".DV");
    table.addCell(createCommonActionsCell(new Phrase(actionDV, commentFont)));
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }

  private float encodeValues(SheetGraphics graphics, Bounds bounds, Hero hero, ICharacterStatsModifiers equipment) {
    IGenericTraitCollection traitCollection = new GenericTraitCollectionFacade(TraitModelFetcher.fetch(hero));
    HighestSpecialty awarenessSpecialty = new HighestSpecialty(hero, AbilityType.Awareness);
    HighestSpecialty integritySpecialty = new HighestSpecialty(hero, AbilityType.Integrity);
    String joinLabel = resources.getString("Sheet.SocialCombat.JoinDebateBattle");
    String dodgeLabel = resources.getString("Sheet.SocialCombat.DodgeMDV");
    String normalLabel = resources.getString("Sheet.Combat.NormalSpecialty");
    int joinDebate = CharacterUtilities.getJoinDebate(traitCollection, equipment);
    int joinDebateWithSpecialty = CharacterUtilities.getJoinDebateWithSpecialty(traitCollection, equipment, awarenessSpecialty.getValue());
    int dodgeMDV = CharacterUtilities.getDodgeMdv(traitCollection, equipment);
    int dodgeMDVWithSpecialty = CharacterUtilities.getDodgeMdvWithSpecialty(traitCollection, equipment, integritySpecialty.getValue());
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(2, upperLeftCorner, bounds.width, 3);
    displayJoinDebateWithSpecialty(graphics, encoder, joinLabel, joinDebate, joinDebateWithSpecialty, normalLabel + awarenessSpecialty);
    displayDodgeWithSpecialty(graphics, encoder, dodgeLabel, dodgeMDV, dodgeMDVWithSpecialty, normalLabel + integritySpecialty);
    return encoder.getHeight() + 1;
  }

  private void displayJoinDebateWithSpecialty(SheetGraphics graphics, LabelledValueEncoder encoder, String joinLabel, int joinDebate,
                                              int joinDebateWithSpecialty, String joinDebateSpecialtyLabel) {
    if (joinDebate != joinDebateWithSpecialty) {
      encoder.addLabelledValue(graphics, 0, joinLabel, joinDebate, joinDebateWithSpecialty);
      encoder.addComment(graphics, joinDebateSpecialtyLabel, 0);
    } else {
      encoder.addLabelledValue(graphics, 0, joinLabel, joinDebate);
      encoder.addComment(graphics, "", 0);
    }
  }

  private void displayDodgeWithSpecialty(SheetGraphics graphics, LabelledValueEncoder encoder, String dodgeLabel, int dodgeMDV,
                                         int dodgeMDVWithSpecialty, String dodgeSpecialtyLabel) {
    if (dodgeMDV != dodgeMDVWithSpecialty) {
      encoder.addLabelledValue(graphics, 1, dodgeLabel, dodgeMDV, dodgeMDVWithSpecialty);
      encoder.addComment(graphics, dodgeSpecialtyLabel, 1);
    } else {
      encoder.addLabelledValue(graphics, 1, dodgeLabel, dodgeMDV);
    }
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.SocialCombat");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
