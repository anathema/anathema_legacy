package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.lib.resources.Resources;

public class AttributesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AttributesPresenter(ICharacter character, Resources resources, IGroupedFavorableTraitConfigurationView view) {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = character.getTraitConfiguration().getAttributeTypeGroups();
    view.initGui(new ColumnCount(1));
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, character, view, resources);
  }

  public void initPresentation() {
    presenter.init("AttributeGroupType.Name");
  }
}
