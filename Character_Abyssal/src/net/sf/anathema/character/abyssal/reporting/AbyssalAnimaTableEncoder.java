package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.reporting.sheet.common.anima.AbstractAnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class AbyssalAnimaTableEncoder extends AbstractAnimaTableEncoder {

  public AbyssalAnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    super(resources, baseFont, fontSize);
  }

  @Override
  protected String getFifthLevelKey() {
    return "Sheet.AnimaTable.TotemicAura"; //$NON-NLS-1$
  }

  @Override
  protected String getFirstLevelKey() {
    return "Sheet.AnimaTable.CasteMarkBranded"; //$NON-NLS-1$
  }

  @Override
  protected String getFourthLevelKey() {
    return "Sheet.AnimaTable.ChillingBonfire"; //$NON-NLS-1$
  }

  @Override
  protected String getSecondLevelKey() {
    return "Sheet.AnimaTable.CasteMarkBleeds"; //$NON-NLS-1$
  }

  @Override
  protected String getSecondLevelStealth() {
    return getResources().getString("Sheet.AnimaTable.StealthNormal"); //$NON-NLS-1$
  }

  @Override
  protected String getThirdLevelKey() {
    return "Sheet.AnimaTable.DarkAura"; //$NON-NLS-1$
  }
}