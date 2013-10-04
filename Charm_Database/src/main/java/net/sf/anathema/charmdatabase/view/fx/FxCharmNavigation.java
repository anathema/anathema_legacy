package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.charmdatabase.view.CharmNavigation;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.fx.Navigation;

public class FxCharmNavigation extends Navigation implements CharmNavigation {

  private ListSelectionView<Charm> listView = new ListSelectionView<>();

  public FxCharmNavigation(final Resources resources) {
	listView.setCellRenderer(new AgnosticUIConfiguration<Charm>() {

		@Override
		public RelativePath getIconsRelativePath(Charm value) {
			return null;
		}

		@Override
		public String getLabel(Charm charm) {
			return charm != null ? resources.getString(charm.getId()) : null;
		}

		@Override
		public void configureTooltip(Charm item,
				ConfigurableTooltip configurableTooltip) {
			// nothing to do
		}
		
	});
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        addContainerToNavigation(listView.getNode());
      }
    });
  }
  
  @Override
  public ITextView addTextualFilter(String label) {
    final FxTextView view = FxTextView.SingleLine(label);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
    	  addContainerToNavigation(view.getNode());
      }
    });
    return view;
  }

  @Override
  public VetoableObjectSelectionView<Charm> getTemplateListView() {
    return listView;
  }

  @Override
  public Tool addEditTemplateTool() {
    return addTool();
  }
}