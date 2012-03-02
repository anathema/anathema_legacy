package net.sf.anathema.character.db.magic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;

public class SurgingMastery extends AbstractGenericCharm
{

	@Override
	public String getCostString(IResources resources) {
		return "6m, 0+ wp";
	}

	@Override
	public String getType(IResources resources) {
		CharmTypeModel model = new CharmTypeModel();
	    model.setCharmType(CharmType.Reflexive);
	    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
	}

	@Override
	public String getDurationString(IResources resources) {
		return new QualifiedAmountDuration("1", "action").getText(resources); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected ExaltedSourceBook getSourceBook() {
		return ExaltedSourceBook.ThousandCorrectActions;
	}

	@Override
	protected String getId() {
		return "Dragon-Blooded.SurgingMastery";
	}

	@Override
	protected boolean isComboOk() {
		return true;
	}

	@Override
	protected ICharacterType getCharacterType() {
		return CharacterType.DB;
	}
}
