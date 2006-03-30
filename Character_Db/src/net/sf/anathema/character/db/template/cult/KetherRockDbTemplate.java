package net.sf.anathema.character.db.template.cult;

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

public class KetherRockDbTemplate extends AbstractDbTemplate {
  public static final IIdentificate KETHER_ROCK_SUBTYPE = new Identificate("KetherRockSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, KETHER_ROCK_SUBTYPE);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public KetherRockDbTemplate(CharmCache charmProvider, IAdditionalRules rules) throws PersistenceException {
    super(charmProvider, rules, new KetherRockDbTraitTemplateFactory());
  }

  public ICreationPoints getCreationPoints() {
    return new CultDbCreationPoints();
  }
}