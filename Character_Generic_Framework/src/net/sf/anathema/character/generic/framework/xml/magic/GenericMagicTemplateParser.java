package net.sf.anathema.character.generic.framework.xml.magic;

import java.util.List;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.template.magic.CharmSet;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultFreePicksPredicate;
import net.sf.anathema.character.generic.impl.template.magic.ICharmSet;
import net.sf.anathema.character.generic.impl.template.magic.NullCharmSet;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GenericMagicTemplateParser extends AbstractXmlTemplateParser<GenericMagicTemplate> {

  private static final String TAG_FREE_PICKS_PREDICATE = "freePicksPredicate"; //$NON-NLS-1$
  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final Object VALUE_DEFAULT = "Default"; //$NON-NLS-1$
  private static final String TAG_CHARM_TEMPLATE = "charmTemplate"; //$NON-NLS-1$
  private static final String ATTRIB_MARTIAL_ARTS_LEVEL = "martialArtsLevel"; //$NON-NLS-1$
  private static final String ATTRIB_HIGH_LEVEL_MARTIAL_ARTS = "highLevelMartialArts"; //$NON-NLS-1$
  private static final String ATTRIB_CHARM_TYPE = "charmType"; //$NON-NLS-1$
  private static final Object VALUE_NONE = "None"; //$NON-NLS-1$
  private static final String TAG_SPELL_TEMPLATE = "spellTemplate"; //$NON-NLS-1$
  private static final String ATTRIB_MAXIMUM_SORCERY_CIRCLE = "maximumSorceryCircle"; //$NON-NLS-1$
  private static final String TAG_FAVORING_TRAIT_TYPE = "favoringTraitType"; //$NON-NLS-1$
  private static final String ATTRIB_MAXIMUM_NECROMANCY_CIRCLE = "maximumNecromancyCircle"; //$NON-NLS-1$
  private static final String TAG_CASTE = "caste"; //$NON-NLS-1$
  private static final String TAG_ALIEN_CHARMS = "alienCharms"; //$NON-NLS-1$
  private final IExaltedEdition edition;

  public GenericMagicTemplateParser(IXmlTemplateRegistry<GenericMagicTemplate> templateRegistry, IExaltedEdition edition) {
    super(templateRegistry);
    this.edition = edition;
  }

  @Override
  protected GenericMagicTemplate createNewBasicTemplate() {
    return new GenericMagicTemplate();
  }

  public GenericMagicTemplate parseTemplate(Element element) throws PersistenceException {
    GenericMagicTemplate basicTemplate = getBasicTemplate(element);
    setFreePicksPredicate(basicTemplate, element);
    setFavoringTraitType(basicTemplate, element);
    setCharmTemplate(basicTemplate, element);
    setSpellTemplate(basicTemplate, element);
    return basicTemplate;
  }

  private void setFavoringTraitType(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element traitTypeElement = element.element(TAG_FAVORING_TRAIT_TYPE);
    if (traitTypeElement == null) {
      return;
    }
    String type = ElementUtilities.getRequiredAttrib(traitTypeElement, ATTRIB_TYPE);
    FavoringTraitType traitTypeType = FavoringTraitType.valueOf(type);
    basicTemplate.setFavoringTraitType(traitTypeType);
  }

  private void setSpellTemplate(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element spellTemplateElement = element.element(TAG_SPELL_TEMPLATE);
    if (spellTemplateElement == null) {
      return;
    }
    String maximumSorceryCircleId = ElementUtilities.getRequiredAttrib(
        spellTemplateElement,
        ATTRIB_MAXIMUM_SORCERY_CIRCLE);
    CircleType maximumSorceryCircle = null;
    if (!maximumSorceryCircleId.equals(VALUE_NONE)) {
      maximumSorceryCircle = CircleType.valueOf(maximumSorceryCircleId);
    }
    String maximumNecromancyCircleId = ElementUtilities.getRequiredAttrib(
        spellTemplateElement,
        ATTRIB_MAXIMUM_NECROMANCY_CIRCLE);
    CircleType maximumNecromancyCircle = null;
    if (!maximumNecromancyCircleId.equals(VALUE_NONE)) {
      maximumNecromancyCircle = CircleType.valueOf(maximumNecromancyCircleId);
    }
    basicTemplate.setSpellTemplate(new SpellMagicTemplate(
        CircleType.getSorceryCirclesUpTo(maximumSorceryCircle),
        CircleType.getNecromancyCirclesUpTo(maximumNecromancyCircle)));
  }

  private void setCharmTemplate(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element charmTemplateElement = element.element(TAG_CHARM_TEMPLATE);
    if (charmTemplateElement == null) {
      return;
    }
    String martialArtsLevelId = ElementUtilities.getRequiredAttrib(charmTemplateElement, ATTRIB_MARTIAL_ARTS_LEVEL);
    boolean highLevelAtCreation = ElementUtilities.getBooleanAttribute(
        charmTemplateElement,
        ATTRIB_HIGH_LEVEL_MARTIAL_ARTS,
        false);
    MartialArtsLevel level = MartialArtsLevel.valueOf(martialArtsLevelId);
    String charmType = ElementUtilities.getRequiredAttrib(charmTemplateElement, ATTRIB_CHARM_TYPE);
    ICharmSet charmSet;
    if (charmType.equals(VALUE_NONE)) {
      charmSet = new NullCharmSet();
    }
    else {
      charmSet = CharmSet.createRegularCharmSet(CharmCache.getInstance(), CharacterType.getById(charmType), edition);
    }
    CharmTemplate charmTemplate = new CharmTemplate(level, highLevelAtCreation, charmSet);
    setAlienAllowedCastes(charmTemplate, charmTemplateElement);
    basicTemplate.setCharmTemplate(charmTemplate);
  }

  private void setAlienAllowedCastes(CharmTemplate charmTemplate, Element charmTemplateElement)
      throws PersistenceException {
    Element alienCharmsElement = charmTemplateElement.element(TAG_ALIEN_CHARMS);
    if (alienCharmsElement == null) {
      return;
    }
    List<Element> casteElements = ElementUtilities.elements(alienCharmsElement, TAG_CASTE);
    for (Element casteElement : casteElements) {
      charmTemplate.setCasteAlienAllowed(ElementUtilities.getRequiredAttrib(casteElement, ATTRIB_TYPE));
    }
  }

  private void setFreePicksPredicate(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element freePicksElement = element.element(TAG_FREE_PICKS_PREDICATE);
    if (freePicksElement == null) {
      return;
    }
    String type = ElementUtilities.getRequiredAttrib(freePicksElement, ATTRIB_TYPE);
    if (type.equals(VALUE_DEFAULT)) {
      basicTemplate.setFreePicksPredicate(new DefaultFreePicksPredicate());
    }
    else {
      throw new UnsupportedOperationException("VALUE NOT YET SUPPORTED"); //$NON-NLS-1$
    }
  }
}