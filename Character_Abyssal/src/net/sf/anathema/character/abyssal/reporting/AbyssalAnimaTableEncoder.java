package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.reporting.sheet.common.anima.AnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class AbyssalAnimaTableEncoder extends AnimaTableEncoder {

  public AbyssalAnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    super(resources, baseFont, fontSize);
  }

  @Override
  protected String getSecondLevelStealth() {
    return getResources().getString("Sheet.AnimaTable.StealthNormal"); //$NON-NLS-1$
  }
}