package net.sf.anathema.character.db.template.lookshy;

import net.sf.anathema.character.db.template.AbstractDbTemplate;
import net.sf.anathema.character.db.template.dynastic.DynasticDbCreationPoints;
import net.sf.anathema.character.db.template.dynastic.DynasticDbTraitTemplateFactory;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class LookshyRealmDbTemplate extends AbstractDbTemplate {
  public static final IIdentificate LOOKSHY_REALM_DB_SUBTYPE = new Identificate("LookshyRealmSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, LOOKSHY_REALM_DB_SUBTYPE);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public LookshyRealmDbTemplate(CharmCache charmProvider, IAdditionalRules rules) throws PersistenceException {
    super(charmProvider, rules, new DynasticDbTraitTemplateFactory());
  }

  public ICreationPoints getCreationPoints() {
    return new DynasticDbCreationPoints();
  }
}