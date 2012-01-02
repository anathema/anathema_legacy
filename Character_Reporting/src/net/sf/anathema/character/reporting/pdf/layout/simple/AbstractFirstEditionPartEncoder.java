package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.FirstEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractFirstEditionPartEncoder implements ISimplePartEncoder {

  private final IResources resources;

  public AbstractFirstEditionPartEncoder(IResources resources) {
    this.resources = resources;
  }

  public final IResources getResources() {
    return resources;
  }

  public IBoxContentEncoder getSocialCombatEncoder() {
    return new HorizontalLineBoxContentEncoder(1, "MeritsFlaws"); //$NON-NLS-1$
  }

  public IBoxContentEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry) {
    return new HorizontalLineBoxContentEncoder(1, "Notes"); //$NON-NLS-1$
  }

  public IBoxContentEncoder getHealthAndMovementEncoder() {
    return new FirstEditionHealthAndMovementEncoder(getResources());
  }

  public float getWeaponryHeight() {
    return 129;
  }

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[0];
  }

  public boolean isEncodeAttributeAsFavorable() {
    return false;
  }
}
