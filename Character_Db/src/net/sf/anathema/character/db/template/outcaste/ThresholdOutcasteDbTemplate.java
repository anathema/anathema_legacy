package net.sf.anathema.character.db.template.outcaste;

import net.sf.anathema.character.db.template.AbstractDbTemplate;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.Identificate;

public class ThresholdOutcasteDbTemplate extends AbstractDbTemplate {

  public ThresholdOutcasteDbTemplate(CharmCache charmProvider, IAdditionalRules rules) {
    super(charmProvider, rules, new ExaltTraitTemplateFactory());
  }

  public static Identificate THRESHOLD_OUTCASTE_SUB_TYPE = new Identificate("ThresholdOutcasteSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, THRESHOLD_OUTCASTE_SUB_TYPE);

  public ICreationPoints getCreationPoints() {
    return new ThresholdOutcasteDbCreationPoints();
  }

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }
}