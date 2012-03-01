package net.sf.anathema.charmtree.filters;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EssenceLevelCharmFilterPage implements ICharmFilterPage
{
	IResources resources;
	boolean[] enabled;
	int[] essence;
	
	public EssenceLevelCharmFilterPage(IResources resources, boolean[] enabled, int[] essence)
	{
		this.resources = resources;
		this.enabled = enabled;
		this.essence = essence;
	}
	
	@Override
	public JPanel getContent()
	{
		JPanel panel = new JPanel();
		
		final SpinnerNumberModel model = new SpinnerNumberModel(essence[0], 1, EssenceTemplate.SYSTEM_ESSENCE_MAX, 1);
		JPanel stringPanel = new JPanel();
		JSpinner essenceSpinner = new JSpinner(model);
		stringPanel.add(essenceSpinner);
		
		essenceSpinner.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				essence[0] = model.getNumber().intValue();
			}
		});
		
		JCheckBox checkBox = new JCheckBox(resources.getString("CharmFilters.EssenceLevel.ShowOnly"));
		checkBox.setSelected(enabled[0]);
		checkBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent arg0)
			{
				enabled[0] = arg0.getStateChange() == ItemEvent.SELECTED;
			}	
		});
		
		panel.add(checkBox);
		panel.add(stringPanel);
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder(resources.getString("CharmFilters.EssenceLevel.Filter"));
		panel.setBorder(title);
		
		return panel;
	}

}
