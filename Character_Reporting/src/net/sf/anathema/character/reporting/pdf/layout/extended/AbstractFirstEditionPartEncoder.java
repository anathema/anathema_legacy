package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractFirstEditionPartEncoder implements IExtendedPartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public AbstractFirstEditionPartEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public final IResources getResources() {
    return resources;
  }

  public final BaseFont getBaseFont() {
    return baseFont;
  }

  public IBoxContentEncoder getSocialCombatEncoder() {
    return new HorizontalLineBoxContentEncoder(1, "MeritsFlaws"); //$NON-NLS-1$
  }

  public IBoxContentEncoder getIntimaciesEncoder(ExtendedEncodingRegistry registry) {
    return new HorizontalLineBoxContentEncoder(1, "Notes"); //$NON-NLS-1$
  }

  public IBoxContentEncoder getHealthEncoder() {
    return null;
  }

  public IBoxContentEncoder getMovementEncoder() {
    return null;
  }

  public float getWeaponryHeight() {
    return 129;
  }

  public IVariableBoxContentEncoder[] getAdditionalFirstPageEncoders() {
    return new IVariableBoxContentEncoder[0];
  }

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[0];
  }

  public boolean isEncodeAttributeAsFavorable() {
    return false;
  }
}
