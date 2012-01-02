package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.CombatStatsContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.SecondEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.SecondEditionCombatValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.essence.SimpleEssenceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.SecondEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.social.SocialCombatStatsBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSecondEditionPartEncoder implements ISimplePartEncoder {

  private final IResources resources;

  public AbstractSecondEditionPartEncoder(IResources resources) {
    this.resources = resources;
  }

  public final IResources getResources() {
    return resources;
  }

  public boolean hasSecondPage() {
    return true;
  }

  public IBoxContentEncoder getEssenceEncoder() {
    return new SimpleEssenceBoxContentEncoder();
  }

  public final IBoxContentEncoder getCombatStatsEncoder() {
    IContentEncoder valueEncoder = new SecondEditionCombatValueEncoder();
    ITableEncoder rulesEncoder = new SecondEditionCombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(rulesEncoder, valueEncoder);
  }

  public IBoxContentEncoder getSocialCombatEncoder() {
    return new SocialCombatStatsBoxEncoder(resources);
  }

  public IBoxContentEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry) {
    return registry.getIntimaciesEncoder();
  }

  public IBoxContentEncoder getHealthAndMovementEncoder() {
    return new SecondEditionHealthAndMovementEncoder(resources);
  }

  public float getWeaponryHeight() {
    return 102;
  }

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[0];
  }

  public boolean isEncodeAttributeAsFavorable() {
    return false;
  }
}
