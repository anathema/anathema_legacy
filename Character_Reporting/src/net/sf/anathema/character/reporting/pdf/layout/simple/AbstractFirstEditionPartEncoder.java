package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.FirstEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.FirstEditionCombatValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.CombatStatsContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.FirstEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractFirstEditionPartEncoder implements ISimplePartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;

  public AbstractFirstEditionPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
  }

  public final IResources getResources() {
    return resources;
  }

  public final BaseFont getBaseFont() {
    return baseFont;
  }

  public final BaseFont getSymbolBaseFont() {
    return symbolBaseFont;
  }

  public final IBoxContentEncoder getCombatStatsEncoder() {
    IContentEncoder valueEncoder = new FirstEditionCombatValueEncoder();
    ITableEncoder rulesEncoder = new FirstEditionCombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(rulesEncoder, valueEncoder);
  }

  public IBoxContentEncoder getSocialCombatEncoder() {
    return new HorizontalLineBoxContentEncoder(1, "MeritsFlaws"); //$NON-NLS-1$
  }

  public IBoxContentEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry) {
    return new HorizontalLineBoxContentEncoder(1, "Notes"); //$NON-NLS-1$
  }

  public IBoxContentEncoder getHealthAndMovementEncoder() {
    return new FirstEditionHealthAndMovementEncoder(getResources(), getBaseFont(), symbolBaseFont);
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
