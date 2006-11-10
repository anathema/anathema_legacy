package net.sf.anathema.character.reporting.sheet.common.anima;

import java.awt.Color;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class AnimaTableEncoder extends AbstractTableEncoder {

  private final IResources resources;
  private final Font headerFont;
  private final Font font;

  public AnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    this.resources = resources;
    this.headerFont = new Font(baseFont, fontSize, Font.ITALIC, Color.BLACK);
    this.font = new Font(baseFont, fontSize, Font.NORMAL, Color.BLACK);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    PdfPTable table = new PdfPTable(new float[] { 0.15f, 0.6f, 0.25f });
    table.setWidthPercentage(100);
    CharacterType type = character.getTemplate().getTemplateType().getCharacterType();
    String descriptionPrefix = "Sheet.AnimaTable.Description." + type; //$NON-NLS-1$
    table.addCell(createHeaderCell(resources.getString("Sheet.AnimaTable.Header.Motes"))); //$NON-NLS-1$
    table.addCell(createHeaderCell(resources.getString("Sheet.AnimaTable.Header.BannerFlare"))); //$NON-NLS-1$
    table.addCell(createHeaderCell(resources.getString("Sheet.AnimaTable.Header.Stealth"))); //$NON-NLS-1$

    table.addCell(createContentCell(getFirstLevelRange(character)));
    table.addCell(createContentCell(resources.getString(descriptionPrefix + ".First"))); //$NON-NLS-1$
    table.addCell(createContentCell(resources.getString("Sheet.AnimaTable.StealthNormal"))); //$NON-NLS-1$

    table.addCell(createContentCell(getSecondLevelRange(character)));
    table.addCell(createContentCell(resources.getString(descriptionPrefix + ".Second"))); //$NON-NLS-1$
    table.addCell(createContentCell(getSecondLevelStealth()));

    table.addCell(createContentCell(getThirdLevelRange(character)));
    table.addCell(createContentCell(resources.getString(descriptionPrefix + ".Third"))); //$NON-NLS-1$
    String stealthImpossible = resources.getString("Sheet.AnimaTable.StealthImpossible"); //$NON-NLS-1$
    table.addCell(createContentCell(stealthImpossible));

    table.addCell(createContentCell(getFourthLevelRange(character)));
    table.addCell(createContentCell(resources.getString(descriptionPrefix + ".Fourth"))); //$NON-NLS-1$
    table.addCell(createContentCell(stealthImpossible));

    table.addCell(createContentCell(getFifthLevelRange(character)));
    table.addCell(createContentCell(resources.getString(descriptionPrefix + ".Fifth"))); //$NON-NLS-1$
    table.addCell(createContentCell(stealthImpossible));
    return table;
  }

  protected String getSecondLevelStealth() {
    return "+2"; //$NON-NLS-1$
  }

  protected String getFifthLevelRange(IGenericCharacter character) {
    return "16+"; //$NON-NLS-1$
  }

  protected String getFourthLevelRange(IGenericCharacter character) {
    return "11-15"; //$NON-NLS-1$
  }

  protected String getThirdLevelRange(IGenericCharacter character) {
    return "8-10"; //$NON-NLS-1$
  }

  protected String getSecondLevelRange(IGenericCharacter character) {
    return "4-7"; //$NON-NLS-1$
  }

  protected String getFirstLevelRange(IGenericCharacter character) {
    return "1-3"; //$NON-NLS-1$
  }

  private PdfPCell createContentCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    cell.setPaddingTop(1);
    cell.setPaddingBottom(2);
    return cell;
  }

  private PdfPCell createHeaderCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.BOTTOM);
    return cell;
  }

  protected IResources getResources() {
    return resources;
  }
}
