package net.sf.anathema.character.db.template.outcaste;

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

public class LowerClassOutcasteDbTemplate extends AbstractDbTemplate {

  public static final IIdentificate LOWER_CLASS_OUTCASTE_SUBTYPE = new Identificate("LowerClassOutcasteSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, LOWER_CLASS_OUTCASTE_SUBTYPE);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public LowerClassOutcasteDbTemplate(CharmCache charmProvider, IAdditionalRules rules) throws PersistenceException {
    super(charmProvider, rules, new LowClassOutcasteDbTraitTemplateFactory());
  }

  public ICreationPoints getCreationPoints() {
    return new LowClassOutcasteDbCreationPoints();
  }
}
