package net.sf.anathema.character.db.template.dynastic;

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

public class ImmaculateDbTemplate extends AbstractDbTemplate {
  private static final IIdentificate IMMACULATE_SUBTYPE = new Identificate("ImmaculateSubtype"); //$NON-NLS-1$
  public static final ITemplateType IMMACULATE_TEMPLATE_TYPE = new TemplateType(CharacterType.DB, IMMACULATE_SUBTYPE);

  @Override
  public ITemplateType getTemplateType() {
    return IMMACULATE_TEMPLATE_TYPE;
  }

  public ImmaculateDbTemplate(CharmCache charmProvider, IAdditionalRules rules) {
    super(charmProvider, rules, new DynasticDbTraitTemplateFactory());
  }

  @Override
  protected IMagicTemplate createMagicTemplate(ICharmTemplate charmTemplate, ISpellMagicTemplate spellMagic) {
    CustomizableFreePicksPredicate predicate = new CustomizableFreePicksPredicate(false);
    predicate.addIdException("Dragon-Blooded.Ox-BodyTechnique"); //$NON-NLS-1$
    predicate.addIdException("Dragon-Blooded.SpiritSight"); //$NON-NLS-1$
    predicate.addIdException("Dragon-Blooded.SpiritWalking"); //$NON-NLS-1$
    predicate.addCharmGroupException("AirDragonStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("EarthDragonStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("FireDragonStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("WaterDragonStyle"); //$NON-NLS-1$
    predicate.addCharmGroupException("WoodDragonStyle"); //$NON-NLS-1$
    return new CustomizableMagicTemplate(predicate, charmTemplate, spellMagic);
  }

  @Override
  protected boolean getHighLevelMartialArtsAtCreation() {
    return true;
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return new ImmaculateDbCreationPoints();
  }
}