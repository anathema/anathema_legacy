package net.sf.anathema.character.db.template.dynastic;

import net.sf.anathema.character.db.template.AbstractDbTemplate;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public class DynasticDbTemplate extends AbstractDbTemplate {
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public DynasticDbTemplate(CharmCache charmProvider, IAdditionalRules rules) throws PersistenceException {
    super(charmProvider, rules, new DynasticDbTraitTemplateFactory());
  }

  public ICreationPoints getCreationPoints() {
    return new DynasticDbCreationPoints();
  }
}