package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;

public interface IEncodingRegistry {

  BaseFont getSymbolBaseFont();

  BaseFont getBaseFont();
}