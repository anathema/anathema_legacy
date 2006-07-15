package net.sf.anathema.character.equipment.character.view;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface IEquipmentAdditionalView extends ISimpleTabView {

  public IListObjectSelectionView<IEquipmentObject> getEquipmentObjectPickList();
}