package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfContentEncoder {

  public float encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds);
}