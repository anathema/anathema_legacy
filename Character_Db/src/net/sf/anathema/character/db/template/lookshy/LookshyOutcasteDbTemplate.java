package net.sf.anathema.character.db.template.lookshy;

import net.sf.anathema.character.db.template.AbstractDbTemplate;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class LookshyOutcasteDbTemplate extends AbstractDbTemplate {
  public static final IIdentificate LOOKSHY_OUTCASTE_SUBTYPE = new Identificate("LookshyOutcasteSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, LOOKSHY_OUTCASTE_SUBTYPE);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public LookshyOutcasteDbTemplate(CharmCache charmProvider, IAdditionalRules rules) throws PersistenceException {
    super(charmProvider, rules, new LookshyOutcasteDbTraitTemplateFactory());
  }

  public ICreationPoints getCreationPoints() {
    return new LookshyOutcasteDbCreationPoints();
  }
}