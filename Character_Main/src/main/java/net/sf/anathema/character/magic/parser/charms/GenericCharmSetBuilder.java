package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.character.magic.charm.CharmImpl;
import net.sf.anathema.character.magic.parser.charms.prerequisite.GenericAttributePrerequisiteBuilder;
import net.sf.anathema.character.magic.parser.charms.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.magic.parser.charms.special.ReflectionSpecialCharmParser;
import net.sf.anathema.character.magic.parser.combos.GenericComboRulesBuilder;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.character.framework.type.CharacterTypes;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.Collection;
import java.util.List;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_GENERIC_CHARM;

public class GenericCharmSetBuilder extends AbstractCharmSetBuilder {
  private final GenericCharmBuilder genericsBuilder;
  private TraitType[] types;

  public GenericCharmSetBuilder(CharacterTypes characterTypes, ReflectionSpecialCharmParser specialCharmParser) {
    this.genericsBuilder =
            new GenericCharmBuilder(new GenericIdStringBuilder(), new GenericTraitPrerequisitesBuilder(), new GenericAttributePrerequisiteBuilder(),
                    new GenericComboRulesBuilder(), new GenericCharmPrerequisiteBuilder(), characterTypes, specialCharmParser);
  }

  @Override
  protected void buildCharms(Collection<CharmImpl> allCharms, List<SpecialCharmDto> specialCharms, Element charmListElement) throws PersistenceException {
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