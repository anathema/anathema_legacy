package net.sf.anathema.character.lunar.reporting.sheet;

import com.lowagie.text.pdf.BaseFont;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionHealthAndMovemenTableEncoder;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionLunarHealthAndMovementEncoder extends FirstEditionHealthAndMovementEncoder
{
	final IGenericCharacter character;

	public FirstEditionLunarHealthAndMovementEncoder(IResources resources,
			BaseFont baseFont, BaseFont symbolBaseFont, IGenericCharacter character)
	{
		super(resources, baseFont, symbolBaseFont);
		this.character = character;
	}
	
	@Override
	protected final IPdfTableEncoder createTableEncoder()
	{
		return new FirstEditionHealthAndMovemenTableEncoder(getResources(), getBaseFont())
		{
			protected IGenericTraitCollection getTraits(IGenericCharacter character)
			{
				return ((IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID)).getBeastTraitCollection();
			}
		};
	}

}
