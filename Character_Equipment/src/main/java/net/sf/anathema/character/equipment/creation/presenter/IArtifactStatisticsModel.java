package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IArtifactStatisticsModel extends IEquipmentStatisticsModel
{
	IIntValueModel getAttuneCostModel();
	
	BooleanValueModel getForeignAttunementModel();
	
	BooleanValueModel getRequireAttunementModel();
}
