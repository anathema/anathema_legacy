package net.sf.anathema.character.db.template.pirates;

import net.sf.anathema.character.db.template.AbstractDbTemplate;
import net.sf.anathema.character.db.template.dynastic.DynasticDbCreationPoints;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identificate;

public class PirateRealmDbTemplate extends AbstractDbTemplate {
  public static Identificate PIRATE_REALM_SUBTYPE = new Identificate("PirateRealmSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, PIRATE_REALM_SUBTYPE);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public PirateRealmDbTemplate(CharmCache charmProvider, IAdditionalRules rules) throws PersistenceException {
    super(charmProvider, rules, new PirateRealmDbTraitTemplateFactory());
  }

  public ICreationPoints getCreationPoints() {
    return new DynasticDbCreationPoints();
  }
}