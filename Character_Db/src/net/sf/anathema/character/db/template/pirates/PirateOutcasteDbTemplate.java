package net.sf.anathema.character.db.template.pirates;

import net.sf.anathema.character.db.template.AbstractDbTemplate;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class PirateOutcasteDbTemplate extends AbstractDbTemplate {
  public static final IIdentificate PIRATE_OUTCASTE_SUBTYPE = new Identificate("PirateOutcasteSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, PIRATE_OUTCASTE_SUBTYPE);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public PirateOutcasteDbTemplate(CharmCache charmProvider, IAdditionalRules rules) {
    super(charmProvider, rules, new PirateOutcasteDbTraitTemplateFactory());
  }

  public ICreationPoints getCreationPoints() {
    return new PirateOutcasteDbCreationPoints();
  }
}