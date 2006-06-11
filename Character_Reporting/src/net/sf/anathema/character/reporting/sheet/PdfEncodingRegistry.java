package net.sf.anathema.character.reporting.sheet;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.Table;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.second.part.SecondEditionMortalPartEncoder;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public class PdfEncodingRegistry {

  private final Table<CharacterType, IExaltedEdition, IPdfPartEncoder> partEncoderTable = new Table<CharacterType, IExaltedEdition, IPdfPartEncoder>();
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private IPdfContentEncoder weaponContentEncoder;
  private IPdfContentEncoder armourContentEncoder;
  private IPdfContentEncoder intimaciesEncoder;

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

  public void setWeaponContentEncoder(IPdfContentEncoder encoder) {
    this.weaponContentEncoder = encoder;
  }

  public void setArmourContentEncoder(IPdfContentEncoder encoder) {
    this.armourContentEncoder = encoder;
  }

  public void setIntimaciesEncoder(IPdfContentEncoder intimaciesEncoder) {
    this.intimaciesEncoder = intimaciesEncoder;
  }

  public IPdfContentEncoder getWeaponContentEncoder() {
    return weaponContentEncoder;
  }

  public IPdfContentEncoder getArmourContentEncoder() {
    return armourContentEncoder;
  }

  public IPdfContentEncoder getIntimaciesEncoder() {
    return intimaciesEncoder;
  }

  public void setPartEncoder(CharacterType type, IExaltedEdition edition, IPdfPartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public IPdfPartEncoder getPartEncoder(CharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }
  
  public boolean hasPartEncoder(CharacterType type, IExaltedEdition edition) {
    return partEncoderTable.contains(type, edition);
  }
}