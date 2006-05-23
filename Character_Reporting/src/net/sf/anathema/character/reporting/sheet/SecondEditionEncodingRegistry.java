package net.sf.anathema.character.reporting.sheet;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionEncodingRegistry {

  private IPdfContentEncoder weaponContentEncoder;
  private IPdfContentEncoder armourContentEncoder;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;

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

  public IPdfContentEncoder getWeaponContentEncoder() {
    return weaponContentEncoder;
  }

  public IPdfContentEncoder getArmourContentEncoder() {
    return armourContentEncoder;
  }
}