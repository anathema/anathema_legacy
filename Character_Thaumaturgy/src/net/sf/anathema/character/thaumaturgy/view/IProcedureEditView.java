package net.sf.anathema.character.thaumaturgy.view;

import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;

public interface IProcedureEditView<K> extends IButtonControlledComboEditView<K>
{
	public void setProcedures(K[] procedures);
}
