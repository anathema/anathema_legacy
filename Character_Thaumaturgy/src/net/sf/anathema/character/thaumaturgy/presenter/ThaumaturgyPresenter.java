package net.sf.anathema.character.thaumaturgy.presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.util.ProxyComboBoxEditor;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyMagicType;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyMagic;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyModel;
import net.sf.anathema.character.thaumaturgy.view.IProcedureEditView;
import net.sf.anathema.character.thaumaturgy.view.IThaumaturgyMagicView;
import net.sf.anathema.character.thaumaturgy.view.IThaumaturgyView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class ThaumaturgyPresenter implements IPresenter {

  private final Comparator<String> comparator = new Comparator<String>() {
    public int compare(String o1, String o2) {
      return o1.compareToIgnoreCase(o2);
    }
  };

  private final Map<String, ThaumaturgyMagicType> modeMap
    = new HashMap<String, ThaumaturgyMagicType>();
  private final Map<IThaumaturgyMagic, IThaumaturgyMagicView> magicMap
  	= new HashMap<IThaumaturgyMagic, IThaumaturgyMagicView>();
  private final IResources resources;
  private final IThaumaturgyView view;
  private final IThaumaturgyModel model;

  public ThaumaturgyPresenter(
      IThaumaturgyModel model,
      IThaumaturgyView view,
      IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    initArtSelectionView();
    initProcedureSelectionView();
    initModeSelection();
  }
  
  private void initModeSelection()
  {
	  view.addModeListener(getModes(),
			  resources.getString("Thaumaturgy.Mode.Degree"),
			  new IObjectValueChangedListener<String>()
			  {
				@Override
				public void valueChanged(String newValue) {
					model.clear();
					model.setCurrentType(modeMap.get(newValue));
					view.setCurrentMode(modeMap.get(newValue));
					view.clear();
				}
			  });
  }
  
  private String[] getModes()
  {
	  int length = ThaumaturgyMagicType.values().length;
	  String[] modes = new String[length];
	  for (int i = 0; i != length; i++)
	  {
		  String mode = resources.getString("Thaumaturgy.Mode." + 
				  ThaumaturgyMagicType.values()[i].name());
		  modeMap.put(mode, ThaumaturgyMagicType.values()[i]);
		  modes[i] = mode;
	  }
	  return modes;
  }
  
  private void initArtSelectionView()
  {
    Icon addIcon = new BasicUi(resources).getAddIcon();
    
    String labelText = resources.getString("Thaumaturgy.SelectionView.ArtLabel"); //$NON-NLS-1$
    ProxyComboBoxEditor editor = new ProxyComboBoxEditor() {
      @Override
      public void setItem(Object anObject) {
        super.setItem(anObject);
      }
    };
    ListCellRenderer renderer = new DefaultListCellRenderer() {
		private static final long serialVersionUID = 1L;

	@Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      }
    };
    final IButtonControlledObjectSelectionView<String> artSelectionView = view.addArtSelectionView(
        labelText,
        editor,
        renderer,
        addIcon);
    setArtObjects(artSelectionView);
    artSelectionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setCurrentArt(newValue);
      }
    });
    artSelectionView.addButtonListener(new IObjectValueChangedListener<String>()
    	{
    		public void valueChanged(String value)
    		{
    			model.setCurrentArt(value);
    			IThaumaturgyMagic magic = model.commitSelection();
    			if (magic != null)
    				addMagicView(magic);
    			checkProcedures();
    			model.recalculate();
    			reset(artSelectionView);
    		}
    	});
    model.addSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
    	  artSelectionView.setButtonEnabled(model.isEntryComplete());
      }
    });
    for (IThaumaturgyMagic magic : model.getLearnedDegrees())
    	addMagicView(magic);
    reset(artSelectionView);
    model.addCharacterChangeListener(new GlobalCharacterChangeAdapter() {
        @Override
        public void experiencedChanged(boolean experienced) {
          updateMagicViewButtons();
        }
      });
    updateMagicViewButtons();
  }
  
  public void initProcedureSelectionView() {
	    Icon addIcon = new BasicUi(resources).getAddIcon();
	    ProxyComboBoxEditor artEditor = new ProxyComboBoxEditor() {
	        @Override
	        public void setItem(Object anObject) {
	          super.setItem(anObject);
	        }
	      };
	    ProxyComboBoxEditor procedureEditor = new ProxyComboBoxEditor() {
		        @Override
		        public void setItem(Object anObject) {
		          super.setItem(anObject);
		        }
		      };
	    final IProcedureEditView<String> procedureSelectionView = view.addProcedureSelectionView(
	    	model.getProcedureControl(),
	        new AbstractSelectCellRenderer<String>(resources) {
				private static final long serialVersionUID = 1L;

			@Override
	          protected String getCustomizedDisplayValue(String value) {
	            return value;
	          }
	        },
	        artEditor,
	        procedureEditor,
	        addIcon);
	    setProcedureObjects(procedureSelectionView);
	    procedureSelectionView.addSelectionChangedListener(new IObjectValueChangedListener<String>() {
	      public void valueChanged(String newValue) {
	        model.setCurrentArt(newValue);
	        procedureSelectionView.setProcedures(getProcedures(newValue));
	      }
	    });
	    procedureSelectionView.addEditChangedListener(new IObjectValueChangedListener<String>() {
	      public void valueChanged(String newProcedureName) {
	    	  model.setCurrentProcedure(newProcedureName);
	    	  procedureSelectionView.setButtonEnabled(model.isEntryComplete());
	      }
	    });
	    procedureSelectionView.addButtonListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	IThaumaturgyMagic magic = model.commitSelection();
  			if (magic != null)
  				addMagicView(magic);
  			checkProcedures();
  			model.recalculate();
	        reset(procedureSelectionView);
	      }
	    });
		model.getProcedureControl().addCurrentValueListener(new IIntValueChangedListener()
		{
			@Override
			public void valueChanged(int newValue) {
				procedureSelectionView.setButtonEnabled(model.isEntryComplete());
			}
		});
	    model.addSelectionChangeListener(new IChangeListener() {
	      public void changeOccured() {
	        procedureSelectionView.setButtonEnabled(model.isEntryComplete());
	      }
	    });
	    reset(procedureSelectionView);
	    for (IThaumaturgyMagic magic : model.getLearnedProcedures())
	    	addMagicView(magic);
	    model.addCharacterChangeListener(new GlobalCharacterChangeAdapter() {
	    	
	        @Override
	        public void characterChanged()
	        {
	        	setProcedureObjects(procedureSelectionView);
	        }
	        
	        @Override
	        public void experiencedChanged(boolean experienced) {
	          updateMagicViewButtons();
	        }
	      });
	    updateMagicViewButtons();
	  }
  
  private String[] getArts()
  {
	  String[] arts = model.getArts();
	  for (int i = 0; i != arts.length; i++)
		  arts[i] = resources.getString("Thaumaturgy." + arts[i]);
	  return arts;
  }
  
  private String[] getProcedures(String art)
  {
	  String[] procedures = model.getProcedures(art);
	  for (int i = 0; i != procedures.length; i++)
		  procedures[i] = resources.getString("Thaumaturgy." + art + "." +
				  procedures[i]);
	  return procedures;
  }

  private void setArtObjects(IButtonControlledObjectSelectionView<String> selectionView) {
    String[] allArts = getArts();
    Arrays.sort(allArts, comparator);
    selectionView.setObjects(allArts);
  }
  
  private void setProcedureObjects(IButtonControlledComboEditView<String> selectionView) {
	    String[] allArts = getArts();
	    Arrays.sort(allArts, comparator);
	    selectionView.setObjects(allArts);
	  }
  
  private void checkProcedures()
  {
	  List<IThaumaturgyMagic> proceduresToRemove = model.checkRedundantProcedures();
	  for (IThaumaturgyMagic magic : proceduresToRemove)
		  removeMagicView(magic);
  }

  private void reset(final IButtonControlledComboEditView<?> view) {
    model.clear();
    view.clear();
  }
  
  private void reset(final IButtonControlledObjectSelectionView<String> view) {
	model.clear();
	view.setSelectedObject("");
  }

  protected void removeMagicView(IThaumaturgyMagic magic) {
    IThaumaturgyMagicView view = magicMap.get(magic);
    magicMap.remove(magic);
    view.delete();
  }

  private void updateMagicViewButtons() {
    for (IThaumaturgyMagic magic : magicMap.keySet()) {
        IThaumaturgyMagicView view = magicMap.get(magic);
        view.setDeleteButtonEnabled(magic.getCreationValue() == 0 || !model.isExperienced());
    }
  }

  private void addMagicView(final IThaumaturgyMagic magic) {
    String artName = magic.getArt();
    String procedureName = magic.getProcedure();
    Icon deleteIcon = new BasicUi(resources).getRemoveIcon();
    final IThaumaturgyMagicView magicView = view.addMagicView(
        artName,
        procedureName,
        deleteIcon,
        magic.getCurrentValue(),
        magic.getMaximalValue());
    new TraitPresenter(magic, magicView).initPresentation();
    magicView.addIntValueChangedListener(new IIntValueChangedListener()
    {
		@Override
		public void valueChanged(int newValue) {
			checkProcedures();
			model.recalculate();
		}
    });
    magicView.addDeleteListener(new IChangeListener() {
      public void changeOccured() {
    	model.removeMagic(magic);
    	model.recalculate();
    	magicView.delete();
      }
    });
    magicMap.put(magic, magicView);
  }
}
