package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;

public interface IEncodingRegistry {
  void setWeaponContentEncoder(IPdfContentBoxEncoder encoder);

  void setArmourContentEncoder(IPdfContentBoxEncoder encoder);

  void setIntimaciesEncoder(IPdfContentBoxEncoder intimaciesEncoder);

  void setMeritsAndFlawsEncoder(IPdfContentBoxEncoder meritsAndFlawsEncoder);

  void setPossessionsEncoder(IPdfContentBoxEncoder encoder);

  BaseFont getSymbolBaseFont();

  BaseFont getBaseFont();
}
