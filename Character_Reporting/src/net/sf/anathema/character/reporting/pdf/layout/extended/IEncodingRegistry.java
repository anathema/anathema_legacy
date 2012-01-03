package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;

public interface IEncodingRegistry {

  void setArmourContentEncoder(ContentEncoder encoder);

  void setPossessionsEncoder(ContentEncoder encoder);

  BaseFont getSymbolBaseFont();

  BaseFont getBaseFont();
}
