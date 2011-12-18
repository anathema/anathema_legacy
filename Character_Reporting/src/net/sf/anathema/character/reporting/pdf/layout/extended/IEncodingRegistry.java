package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;

public interface IEncodingRegistry {
  void setWeaponContentEncoder(IBoxContentEncoder encoder);

  void setArmourContentEncoder(IBoxContentEncoder encoder);

  void setIntimaciesEncoder(IBoxContentEncoder intimaciesEncoder);

  void setMeritsAndFlawsEncoder(IBoxContentEncoder meritsAndFlawsEncoder);

  void setPossessionsEncoder(IBoxContentEncoder encoder);

  BaseFont getSymbolBaseFont();

  BaseFont getBaseFont();
}
