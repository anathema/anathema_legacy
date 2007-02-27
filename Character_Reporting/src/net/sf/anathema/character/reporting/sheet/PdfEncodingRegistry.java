package net.sf.anathema.character.reporting.sheet;

import java.awt.Color;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.collection.Table;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public class PdfEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, IPdfPartEncoder> partEncoderTable = new Table<ICharacterType, IExaltedEdition, IPdfPartEncoder>();
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private IPdfContentBoxEncoder weaponContentEncoder;
  private IPdfContentBoxEncoder armourContentEncoder;
  private IPdfContentBoxEncoder intimaciesEncoder;
  private IPdfContentBoxEncoder possessionsEncoder;
  private IPdfContentBoxEncoder linguisticsEncoder;

  public PdfEncodingRegistry() {
    this.baseFont = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
    this.symbolBaseFont = new Font(Font.SYMBOL, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(false);
  }

  public BaseFont getBaseFont() {
    return baseFont;
  }

  public BaseFont getSymbolBaseFont() {
    return symbolBaseFont;
  }

  public void setWeaponContentEncoder(IPdfContentBoxEncoder encoder) {
    this.weaponContentEncoder = encoder;
  }

  public void setArmourContentEncoder(IPdfContentBoxEncoder encoder) {
    this.armourContentEncoder = encoder;
  }

  public void setIntimaciesEncoder(IPdfContentBoxEncoder intimaciesEncoder) {
    this.intimaciesEncoder = intimaciesEncoder;
  }

  public IPdfContentBoxEncoder getWeaponContentEncoder() {
    return weaponContentEncoder;
  }

  public IPdfContentBoxEncoder getArmourContentEncoder() {
    return armourContentEncoder;
  }

  public IPdfContentBoxEncoder getIntimaciesEncoder() {
    return intimaciesEncoder;
  }

  public IPdfContentBoxEncoder getPossessionsEncoder() {
    return possessionsEncoder;
  }

  public IPdfContentBoxEncoder getLinguisticsEncoder() {
    return linguisticsEncoder;
  }

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, IPdfPartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public IPdfPartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }

  public boolean hasPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.contains(type, edition);
  }

  public void setPossessionsEncoder(IPdfContentBoxEncoder encoder) {
    this.possessionsEncoder = encoder;
  }

  public void setLinguisticsEncoder(IPdfContentBoxEncoder encoder) {
    this.linguisticsEncoder = encoder;
  }
}