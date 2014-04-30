package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public interface IArtifactStatisticsModel extends IEquipmentStatisticsModel
{
	IIntValueModel getAttuneCostModel();
	
	BooleanValueModel getForeignAttunementModel();
	
	BooleanValueModel getRequireAttunementModel();
}
