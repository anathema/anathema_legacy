package net.sf.anathema.campaign.music.impl.view.selection;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.music.impl.view.categorization.MusicCategorizationView;
import net.sf.anathema.campaign.music.presenter.ITrackDetailsProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.campaign.music.view.selection.ITrackDetailsView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledStringValueView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class TrackDetailsView implements ITrackDetailsView {

  private JPanel content;
  private LabelledStringValueView titleView;
  private LabelledStringValueView albumView;
  private LabelledStringValueView trackNumberView;
  private LabelledStringValueView artistView;
  private final MusicCategorizationView musicCategorizationView = new MusicCategorizationView();
  private final JPanel trackDetailsPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel noTrackPanel = new JPanel(new GridDialogLayout(1, false));
  private final ITextView givenNameView = new LineTextView(30);
  private final JPanel playerPanel = new JPanel(new BorderLayout());

  public JComponent getContent(
      IMusicCategorizationProperties categoryProperties,
      ITrackDetailsProperties detailsProperties) {
    if (content == null) {
      content = createContent(categoryProperties, detailsProperties);
    }
    return content;
  }

  private JPanel createContent(
      IMusicCategorizationProperties categoryProperties,
      ITrackDetailsProperties detailsProperties) {
    fillTrackDetailsPanel(categoryProperties, detailsProperties);
    fillNoContentPanel(detailsProperties);
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    panel.add(noTrackPanel);
    return panel;
  }

  private void fillNoContentPanel(ITrackDetailsProperties detailsProperties) {
    noTrackPanel.add(new JLabel(detailsProperties.getNoContentString(), SwingConstants.CENTER));
  }

  private void fillTrackDetailsPanel(
      IMusicCategorizationProperties categoryProperties,
      ITrackDetailsProperties detailsProperties) {
    JPanel infoPlayerPanel = new JPanel(new GridDialogLayout(3, true));
    GridDialogLayoutData infoData = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    infoData.setHorizontalSpan(2);
    infoPlayerPanel.add(createTrackInfoPanel(detailsProperties), infoData);
    infoPlayerPanel.add(playerPanel, GridDialogLayoutData.FILL_BOTH);
    trackDetailsPanel.add(infoPlayerPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    trackDetailsPanel.add(musicCategorizationView.getContent(categoryProperties), GridDialogLayoutData.FILL_BOTH);
  }

  private JPanel createTrackInfoPanel(ITrackDetailsProperties properties) {
    titleView = new LabelledStringValueView(properties.getOriginalNameString() + ":"); //$NON-NLS-1$
    albumView = new LabelledStringValueView(properties.getAlbumLabel() + ":"); //$NON-NLS-1$
    trackNumberView = new LabelledStringValueView(properties.getTrackNumberLabel() + ":"); //$NON-NLS-1$
    artistView = new LabelledStringValueView(properties.getArtistLabel() + ":"); //$NON-NLS-1$
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    new LabelTextView(properties.getGivenNameLabel() + ":", givenNameView).addToStandardPanel(panel); //$NON-NLS-1$
    titleView.addToStandardPanel(panel);
    artistView.addToStandardPanel(panel);
    albumView.addToStandardPanel(panel);
    trackNumberView.addToStandardPanel(panel);
    panel.setBorder(new TitledBorder(properties.getInfoBorder()));
    return panel;
  }

  public void setOriginalTitle(String title) {
    titleView.setValue(title);
  }

  public void setAlbumTitle(String album) {
    albumView.setValue(album);
  }

  public void setTrackNumber(String track) {
    trackNumberView.setValue(track);
  }

  public void setArtistName(String artist) {
    artistView.setValue(artist);
  }

  public void showTrackInfo(boolean show) {
    content.removeAll();
    if (show) {
      content.add(trackDetailsPanel, GridDialogLayoutData.FILL_BOTH);
    }
    else {
      content.add(noTrackPanel, GridDialogLayoutData.FILL_BOTH);
    }
    content.revalidate();
  }

  public IMusicCategorizationView getMusicCategorizationView() {
    return musicCategorizationView;
  }

  public ITextView getGivenNameView() {
    return givenNameView;
  }

  public void setPlayerComponent(JComponent component) {
    playerPanel.removeAll();
    playerPanel.add(component);
  }
}