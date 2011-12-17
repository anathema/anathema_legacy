package net.sf.anathema.character.infernal.reporting.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.reporting.extended.common.FavorableTraitEncoder;
import net.sf.anathema.character.reporting.encoder.IPdfVariableContentBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

public class InfernalYoziListEncoder extends FavorableTraitEncoder implements IPdfVariableContentBoxEncoder
{
	private final float lineHeight = 16;
	
	public InfernalYoziListEncoder(BaseFont baseFont, IResources resources) {
		super(baseFont, resources, 0);
	}

	@Override
	public float getRequestedHeight(IGenericCharacter character, float width) {
		return lineHeight * YoziType.values().length;
	}

	@Override
	public String getHeaderKey(IGenericCharacter character,
			IGenericDescription description) {
		return "Infernal.Yozis";
	}

	@Override
	public boolean hasContent(IGenericCharacter character) {
		return true;
	}

	@Override
	protected String getGroupNamePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups(
			IGenericCharacter character) {
		return character.getYoziTypeGroups();
	}

}
