package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.anima.AbstractAnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SolarAnimaTableEncoder extends AbstractAnimaTableEncoder {

  public SolarAnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    super(resources, baseFont, fontSize);
  }

  @Override
  protected String getFifthLevelRange(IGenericCharacter character) {
    return "16+"; //$NON-NLS-1$
  }

  @Override
  protected String getFourthLevelRange(IGenericCharacter character) {
    return "11-15"; //$NON-NLS-1$
  }

  @Override
  protected String getThirdLevelRange(IGenericCharacter character) {
    return "8-10"; //$NON-NLS-1$
  }

  @Override
  protected String getSecondLevelRange(IGenericCharacter character) {
    return "4-7"; //$NON-NLS-1$
  }

  @Override
  protected String getFirstLevelRange(IGenericCharacter character) {
    return "1-3"; //$NON-NLS-1$
  }

  @Override
  protected String getFifthLevelKey() {
    return "Sheet.AnimaTable.TotemicAura"; //$NON-NLS-1$
  }

  @Override
  protected String getFourthLevelKey() {
    return "Sheet.AnimaTable.BrilliantBonfire"; //$NON-NLS-1$
  }

  @Override
  protected String getThirdLevelKey() {
    return "Sheet.AnimaTable.CoruscantAura"; //$NON-NLS-1$
  }

  @Override
  protected String getSecondLevelKey() {
    return "Sheet.AnimaTable.CasteMarkBurns"; //$NON-NLS-1$
  }

  @Override
  protected String getFirstLevelKey() {
    return "Sheet.AnimaTable.CasteMarkGlitters"; //$NON-NLS-1$
  }
}