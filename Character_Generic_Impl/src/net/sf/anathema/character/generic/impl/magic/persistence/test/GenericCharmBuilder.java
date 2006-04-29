package net.sf.anathema.character.generic.impl.magic.persistence.test;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.impl.magic.MagicSource;
import net.sf.anathema.character.generic.impl.magic.PermanentCostList;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.PrerequisiteListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class GenericCharmBuilder {

  public Charm buildCharm(Element charmElement, AbilityType type) throws PersistenceException {
    String id = new GenericIdStringBuilder(type).build(charmElement);
    PrerequisiteListBuilder builder = new PrerequisiteListBuilder(new GenericTraitPrerequisitesBuilder(type));
    CharmPrerequisiteList list = builder.buildPrerequisiteList(charmElement.element(ICharmXMLConstants.TAG_PREREQUISITE_LIST));
    String groupdId = type.getId();
    return getCharm(id, groupdId, CharmType.ExtraAction, list);
  }

  private Charm getCharm(String id, String groupdId, CharmType type, CharmPrerequisiteList list) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(type);
    final Charm charm = new Charm(
        CharacterType.SOLAR,
        id,
        groupdId,
        list,
        new CostList(null, null, null),
        new PermanentCostList(null, null, null, null),
        new ComboRestrictions(),
        Duration.INSTANT_DURATION,
        model,
        new IMagicSource[] { MagicSource.CUSTOM_SOURCE });
    return charm;
  }
}