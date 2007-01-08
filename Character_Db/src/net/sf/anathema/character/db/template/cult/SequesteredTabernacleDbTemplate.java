package net.sf.anathema.character.db.template.cult;

import net.sf.anathema.character.db.template.AbstractDbTemplate;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.template.magic.CustomizableFreePicksPredicate;
import net.sf.anathema.character.generic.impl.template.magic.CustomizableMagicTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class SequesteredTabernacleDbTemplate extends AbstractDbTemplate {
  public static final IIdentificate SEQUESTERED_TABERNACLE_SUBTYPE = new Identificate("SequesteredTabernacleSubtype"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.DB, SEQUESTERED_TABERNACLE_SUBTYPE);

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public SequesteredTabernacleDbTemplate(CharmCache charmProvider, IAdditionalRules rules) {
    super(charmProvider, rules, new SequesteredTabernacleDbTraitTemplateFactory());
  }

  @Override
  protected IMagicTemplate createMagicTemplate(ICharmTemplate charmTemplate, ISpellMagicTemplate spellMagic) {
    CustomizableFreePicksPredicate predicate = new CustomizableFreePicksPredicate(false);
    predicate.addIdException("Dragon-Blooded.Ox-BodyTechnique"); //$NON-NLS-1$
    predicate.addIdException("Dragon-Blooded.Walker-Among-IrisesPerception"); //$NON-NLS-1$
    predicate.addIdException("Dragon-Blooded.Iris-BulbDiscourse"); //$NON-NLS-1$
    predicate.addCharmGroupException("FallingBlossomStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("EbonShadowStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("TigerStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("MantisStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("SnakeStyle"); //$NON-NLS-1$
    return new CustomizableMagicTemplate(predicate, charmTemplate, spellMagic);
  }

  @Override
  protected boolean getHighLevelMartialArtsAtCreation() {
    return true;
  }

  public ICreationPoints getCreationPoints() {
    return new SequesteredTabernacleDbCreationPoints();
  }
}