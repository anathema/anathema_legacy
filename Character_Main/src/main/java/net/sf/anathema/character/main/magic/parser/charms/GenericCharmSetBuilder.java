package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.combos.GenericComboRulesBuilder;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.Collection;
import java.util.List;

import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.TAG_GENERIC_CHARM;

public class GenericCharmSetBuilder extends AbstractCharmSetBuilder {
  private final GenericCharmBuilder genericsBuilder;
  private TraitType[] types;

  public GenericCharmSetBuilder(CharacterTypes characterTypes, ReflectionSpecialCharmBuilder specialCharmBuilder) {
    this.genericsBuilder =
            new GenericCharmBuilder(new GenericIdStringBuilder(), new GenericTraitPrerequisitesBuilder(), new GenericAttributeRequirementBuilder(),
                    new GenericComboRulesBuilder(), new GenericCharmPrerequisiteBuilder(), characterTypes, specialCharmBuilder);
  }

  @Override
  protected void buildCharms(Collection<CharmImpl> allCharms, List<ISpecialCharm> specialCharms, Element charmListElement) throws PersistenceException {
    List<Element> elements = ElementUtilities.elements(charmListElement, TAG_GENERIC_CHARM);
    for (TraitType type : types) {
      genericsBuilder.setType(type);
      for (Element charmElementObject : elements) {
        createCharm(allCharms, specialCharms, genericsBuilder, charmElementObject);
      }
    }
  }

  public void setTypes(TraitType[] types) {
    this.types = types;
  }
}