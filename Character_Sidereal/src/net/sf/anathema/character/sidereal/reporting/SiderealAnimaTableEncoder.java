package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.sheet.common.anima.AnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SiderealAnimaTableEncoder extends AnimaTableEncoder {
  public SiderealAnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    super(resources, baseFont, fontSize);
  }

  @Override
  protected String getSecondLevelStealth() {
    return getString("Sheet.AnimaTable.StealthNormal"); //$NON-NLS-1$
  }

  @Override
  protected String getThirdLevelStealth() {
    return getString("Sheet.AnimaTable.Stealth2InDark"); //$NON-NLS-1$
  }

}
