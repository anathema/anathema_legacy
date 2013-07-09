package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.model.charm.CharmImpl;
import net.sf.anathema.character.main.magic.model.charm.CharmData;
import net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants;
import net.sf.anathema.character.main.magic.parser.combos.IComboRulesBuilder;
import net.sf.anathema.character.main.magic.parser.magic.CostListBuilder;
import net.sf.anathema.character.main.magic.parser.magic.ICostListBuilder;
import net.sf.anathema.character.main.magic.parser.magic.SourceBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.IAttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.ICharmPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.ITraitPrerequisitesBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.PrerequisiteListBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.model.charm.CharmException;
import net.sf.anathema.character.main.magic.model.charm.ICharmAttribute;
import net.sf.anathema.character.main.magic.model.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.model.charm.duration.IDuration;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.model.magic.ICostList;
import net.sf.anathema.character.main.magic.parser.magic.IExaltedSourceBook;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.ATTRIB_EXALT;
import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.TAG_COST;
import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.TAG_DURATION;
import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.TAG_PREREQUISITE_LIST;

public class CharmBuilder implements ICharmBuilder {

  private final CharmTypeBuilder charmTypeBuilder = new CharmTypeBuilder();
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final DurationBuilder durationBuilder = new DurationBuilder();
  private final GroupStringBuilder groupBuilder = new GroupStringBuilder();
  private final SourceBuilder sourceBuilder = new SourceBuilder();
  private final CharmAttributeBuilder attributeBuilder = new CharmAttributeBuilder();
  private final SpecialCharmBuilder specialCharmBuilder;
  private final IIdStringBuilder idBuilder;
  private final ITraitPrerequisitesBuilder traitsBuilder;
  private final IAttributeRequirementBuilder attributeRequirementsBuilder;
  private final IComboRulesBuilder comboBuilder;
  private final ICharmPrerequisiteBuilder charmPrerequisiteBuilder;
  private final CharacterTypes characterTypes;

  public CharmBuilder(IIdStringBuilder idBuilder, ITraitPrerequisitesBuilder traitsBuilder, IAttributeRequirementBuilder attributeRequirementsBuilder,
                      IComboRulesBuilder comboBuilder, ICharmPrerequisiteBuilder charmPrerequisiteBuilder, CharacterTypes characterTypes,
                      SpecialCharmBuilder specialCharmBuilder) {
    this.idBuilder = idBuilder;
    this.traitsBuilder = traitsBuilder;
    this.attributeRequirementsBuilder = attributeRequirementsBuilder;
    this.comboBuilder = comboBuilder;
    this.charmPrerequisiteBuilder = charmPrerequisiteBuilder;
    this.characterTypes = characterTypes;
    this.specialCharmBuilder = specialCharmBuilder;
  }

  @Override
  public CharmImpl buildCharm(Element charmElement) throws PersistenceException {
    return buildCharm(charmElement, new ArrayList<ISpecialCharm>());
  }

  @Override
  public CharmImpl buildCharm(Element charmElement, List<ISpecialCharm> specialCharms) throws PersistenceException {
    String id = idBuilder.build(charmElement);
    try {
      ICharacterType characterType = getCharacterType(charmElement);
      ICostList temporaryCost;
      try {
        temporaryCost = costListBuilder.buildCostList(charmElement.element(TAG_COST));
      } catch (PersistenceException e) {
        throw new CharmException("Error building costlist for charm " + id, e);
      }
      IComboRestrictions comboRules = comboBuilder.buildComboRules(charmElement);
      IDuration duration = buildDuration(charmElement);
      ICharmTypeModel charmTypeModel = charmTypeBuilder.build(charmElement);
      IExaltedSourceBook[] sources = sourceBuilder.buildSourceList(charmElement);
      CharmPrerequisiteList prerequisiteList = getPrerequisites(charmElement);
      ValuedTraitType[] prerequisites = prerequisiteList.getPrerequisites();
      ValuedTraitType primaryPrerequisite = prerequisites.length != 0 ? prerequisites[0] : null;
      String group = groupBuilder.build(charmElement, primaryPrerequisite);
      CharmImpl charm =
              new CharmImpl(characterType, id, group, isBuildingGenericCharms(), prerequisiteList, temporaryCost, comboRules, duration, charmTypeModel,
                      sources);
      for (ICharmAttribute attribute : attributeBuilder.buildCharmAttributes(charmElement, primaryPrerequisite)) {
        charm.addCharmAttribute(attribute);
      }
      loadSpecialLearning(charmElement, charm);

      ISpecialCharm special = specialCharmBuilder.readCharm(charmElement, id);
      if (special != null) {
        specialCharms.add(special);
      }
      return charm;
    } catch (PersistenceException e) {
      throw new PersistenceException("Parsing error for Charm " + id, e);
    }
  }

  private IDuration buildDuration(Element charmElement) throws CharmException {
    IDuration duration;
    try {
      duration = durationBuilder.buildDuration(charmElement.element(TAG_DURATION));
    } catch (PersistenceException e) {
      throw new CharmException("Error in Charm duration.", e);
    }
    return duration;
  }

  private CharmPrerequisiteList getPrerequisites(Element charmElement) throws CharmException {
    try {
      Element prerequisiteListElement = ElementUtilities.getRequiredElement(charmElement, TAG_PREREQUISITE_LIST);
      return new PrerequisiteListBuilder(traitsBuilder, attributeRequirementsBuilder, charmPrerequisiteBuilder)
              .buildPrerequisiteList(prerequisiteListElement);
    } catch (PersistenceException e) {
      throw new CharmException("Error in Charm prerequisites.", e);
    }
  }

  private ICharacterType getCharacterType(Element charmElement) throws CharmException {
    String typeAttribute = charmElement.attributeValue(ATTRIB_EXALT);
    ICharacterType characterType;
    try {
      characterType = characterTypes.findById(typeAttribute);
    } catch (IllegalArgumentException e) {
      throw new CharmException("No chararacter type given.", e);
    }
    return characterType;
  }

  private void loadSpecialLearning(Element charmElement, CharmImpl charm) {
    for (ICharmAttribute attribute : charm.getAttributes()) {
      if (attribute.getId().startsWith(CharmData.FAVORED_CASTE_PREFIX)) {
        String casteId = attribute.getId().substring(CharmData.FAVORED_CASTE_PREFIX.length());
        charm.addFavoredCasteId(casteId);
      }
    }
    Element learningElement = charmElement.element(ICharmXMLConstants.TAG_LEARNING);
    if (learningElement == null) {
      return;
    }
    for (Element favoredElement : ElementUtilities.elements(learningElement, ICharmXMLConstants.ATTRB_FAVORED)) {
      String casteId = favoredElement.attributeValue(ICharmXMLConstants.TAG_CASTE);
      charm.addFavoredCasteId(casteId);
    }
  }

  protected boolean isBuildingGenericCharms() {
    return false;
  }
}