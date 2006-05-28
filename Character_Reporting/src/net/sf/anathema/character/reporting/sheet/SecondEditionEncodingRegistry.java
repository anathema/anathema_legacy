package net.sf.anathema.character.reporting.sheet;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionEncodingRegistry {

  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private final Map<CharacterType, IPdfContentEncoder> greatCurseEncoder = new HashMap<CharacterType, IPdfContentEncoder>();
  private IPdfContentEncoder weaponContentEncoder;
  private IPdfContentEncoder armourContentEncoder;
  private IPdfContentEncoder intimaciesEncoder;

  public SecondEditionEncodingRegistry() {
    try {
      this.baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
      this.symbolBaseFont = BaseFont.createFont(BaseFont.SYMBOL, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
    }
    catch (Exception e) {
      throw new UnreachableCodeReachedException();
    }
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
  
  public IPdfContentEncoder getGreatCurseEncoder(CharacterType type) {
    return greatCurseEncoder.get(type);
  }

  public void setGreatCurseEncoder(CharacterType characterType, IPdfContentEncoder encoder) {
    greatCurseEncoder.put(characterType, encoder);
  }
}