package net.sf.anathema.character.infernal.traits;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.impl.model.traits.RegisteredTrait;
import net.sf.anathema.character.impl.model.traits.TraitRegistrar;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

@RegisteredTrait
public class YoziRegistrar implements TraitRegistrar {

  @Override
  public void addTraits(ICharacterTemplate template, ICoreTraitConfiguration configuration) {
    ICasteCollection casteCollection = template.getCasteCollection();
    GroupedTraitType[] yoziGroups = template.getYoziGroups();
    IIdentifiedCasteTraitTypeGroup[] yoziTraitGroups = new YoziTypeGroupFactory().createTraitGroups(casteCollection, yoziGroups);
    IIncrementChecker incrementChecker = YoziFavoredIncrementChecker.create(configuration);
    configuration.addFavorableTraits(yoziTraitGroups, incrementChecker);
  }
}