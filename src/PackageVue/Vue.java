package PackageVue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import PackageModele.Modele;
import PackageModele.ModeleAvion;
import PackageModele.ModelePlace;
import PackageModele.ModeleReservation;
import PackageModele.ModeleVol;

public class Vue {

	private JFrame frmRservationDePlace;
	private JTextField villeDepart;
	private JTextField villeArrivee;
	private JTextField frais;
	private JTable table;
	DefaultTableModel model;
	DefaultTableModel model_1;
	DefaultTableModel model_2;
	DefaultTableModel model_3;
	DefaultTableModel model_4;
	private JTextField design;
	private JTextField nbPlaces;
	private JTable table_1;
	private JTextField dateReservation;
	private JTextField nomVoyageur;
	private JTable table_2;
	private JTable table_3;
	private JTextField nbPlacesDisponibles;
	private JTextField nbPlacesOccupes;
	private JTable table_4;
	private Modele modele = new Modele();
	private ModeleVol modelVol = new ModeleVol();
	private ModelePlace modelPlace = new ModelePlace();
	private ModeleAvion modeleAvion = new ModeleAvion();
	private ModeleReservation modeleReservation = new ModeleReservation();

	public Vue() throws SQLException {
		frmRservationDePlace = new JFrame();
		frmRservationDePlace.setTitle("R\u00E9servation de place en avion");
		frmRservationDePlace.setBounds(100, 100, 910, 481);
		frmRservationDePlace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRservationDePlace.getContentPane().setLayout(null);
		Object[] column = { "Numéro vol", "Ville de départ", "Ville d'arrivée", "Frais" };
		model = new DefaultTableModel(modelVol.getData(), column);
		Object[] column_1 = { "Numéro avion", "Numéro vol", "Nombre de places", "Design" };
		model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
		Object[] column_2 = { "Numéro de réservation", "Numéro avion", "Numéro de place", "Date de réservation",
				"Nom du voyageur" };
		model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
		Object[] column_3 = { "Nom du voyageur", "Numéro place", "Numéro vol", "Date de réservation" };
		Object[] column_4 = { "Numéro de l'avion", "Recette" };

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 894, 442);
		frmRservationDePlace.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("VOL", null, panel, null);
		panel.setLayout(null);

		JLabel lblVilleDeDpart = new JLabel("Ville de d\u00E9part :");
		lblVilleDeDpart.setBounds(10, 50, 103, 14);
		panel.add(lblVilleDeDpart);

		JLabel lblVilleDarrive = new JLabel("Ville d'arriv\u00E9e :");
		lblVilleDarrive.setBounds(10, 89, 103, 14);
		panel.add(lblVilleDarrive);

		JLabel lblFrais = new JLabel("Frais :");
		lblFrais.setBounds(10, 124, 103, 14);
		panel.add(lblFrais);

		villeDepart = new JTextField();
		villeDepart.setBounds(141, 47, 157, 20);
		panel.add(villeDepart);
		villeDepart.setColumns(10);

		villeArrivee = new JTextField();
		villeArrivee.setColumns(10);
		villeArrivee.setBounds(141, 86, 157, 20);
		panel.add(villeArrivee);

		frais = new JTextField();
		frais.setColumns(10);
		frais.setBounds(141, 121, 157, 20);
		panel.add(frais);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 17, 554, 386);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				villeDepart.setText(table.getValueAt(i, 1).toString());
				villeArrivee.setText(table.getValueAt(i, 2).toString());
				frais.setText(table.getValueAt(i, 3).toString());
			}
		});
		table.setModel(model);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("AVION", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblDesign = new JLabel("Design :");
		lblDesign.setBounds(10, 107, 89, 14);
		panel_1.add(lblDesign);

		JLabel lblNombreDePlaces = new JLabel("Nombre de places :");
		lblNombreDePlaces.setBounds(10, 143, 116, 14);
		panel_1.add(lblNombreDePlaces);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 66, 151, 20);
		String[] data = modelVol.getAllID();
		int i = 0;
		while (i < data.length) {
			comboBox.addItem(data[i]);
			i++;
		}
		panel_1.add(comboBox);

		design = new JTextField();
		design.setBounds(146, 104, 151, 20);
		panel_1.add(design);
		design.setColumns(10);

		nbPlaces = new JTextField();
		nbPlaces.setColumns(10);
		nbPlaces.setBounds(146, 140, 151, 20);
		panel_1.add(nbPlaces);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(331, 11, 548, 392);
		panel_1.add(scrollPane_1);

		table_1 = new JTable();

		scrollPane_1.setViewportView(table_1);
		table_1.setModel(model_1);

		JLabel lblNumeroVol = new JLabel("Numéro vol :");
		lblNumeroVol.setBounds(10, 69, 89, 14);
		panel_1.add(lblNumeroVol);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("RESERVATION", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNumeroAvion = new JLabel("Numéro avion :");
		lblNumeroAvion.setBounds(10, 50, 129, 14);
		panel_2.add(lblNumeroAvion);

		JLabel lblNumeroDePlace = new JLabel("Numéro de place :");
		lblNumeroDePlace.setBounds(10, 91, 129, 14);
		panel_2.add(lblNumeroDePlace);

		JLabel lblDateDeRservation = new JLabel("Date de r\u00E9servation :");
		lblDateDeRservation.setBounds(10, 129, 129, 14);
		panel_2.add(lblDateDeRservation);

		JLabel lblNomDuVoyageur = new JLabel("Nom du voyageur :");
		lblNomDuVoyageur.setBounds(10, 166, 129, 14);
		panel_2.add(lblNomDuVoyageur);

		JComboBox listeNAvion = new JComboBox();
		listeNAvion.setBounds(182, 47, 156, 20);
		String[] data_1 = modeleAvion.getAllID();
		int i_1 = 0;
		while (i_1 < data_1.length) {
			listeNAvion.addItem(data_1[i_1]);
			i_1++;
		}
		panel_2.add(listeNAvion);

		JComboBox listeNPlace = new JComboBox();
		listeNPlace.setBounds(182, 88, 156, 20);
		try {
			String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
			int i_2 = 0;
			while (i_2 < data_2.length) {
				listeNPlace.addItem(data_2[i_2]);
				i_2++;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		panel_2.add(listeNPlace);

		dateReservation = new JTextField();
		dateReservation.setBounds(182, 126, 156, 20);
		panel_2.add(dateReservation);
		dateReservation.setColumns(10);

		nomVoyageur = new JTextField();
		nomVoyageur.setColumns(10);
		nomVoyageur.setBounds(182, 163, 156, 20);
		panel_2.add(nomVoyageur);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(385, 11, 494, 392);
		panel_2.add(scrollPane_2);

		table_2 = new JTable();

		table_2.setModel(model_2);
		scrollPane_2.setViewportView(table_2);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("DETAILS", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblAvionNumero = new JLabel("Avion numéro :");
		lblAvionNumero.setBounds(283, 39, 128, 14);
		panel_3.add(lblAvionNumero);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(421, 36, 128, 20);
		String[] data_3 = modeleAvion.getAllID();
		int i_3 = 0;
		while (i_3 < data_3.length) {
			comboBox_1.addItem(data_3[i_3]);
			i_3++;
		}
		panel_3.add(comboBox_1);
		model_3 = new DefaultTableModel();
		model_3.setColumnIdentifiers(column_3);
		try {
			model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()), column_3);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		JLabel lblNombreDePlaces_1 = new JLabel("Nombre de places disponibles :");
		lblNombreDePlaces_1.setBounds(10, 117, 178, 14);
		panel_3.add(lblNombreDePlaces_1);

		JLabel lblNombreDePlaces_2 = new JLabel("Nombre de places occup\u00E9es :");
		lblNombreDePlaces_2.setBounds(10, 152, 178, 14);
		panel_3.add(lblNombreDePlaces_2);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(319, 87, 560, 316);
		panel_3.add(scrollPane_3);

		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		table_3.setModel(model_3);

		nbPlacesDisponibles = new JTextField();
		nbPlacesDisponibles.setEditable(false);
		try {
			String placeDispo = modelPlace.NombrePlace(comboBox_1.getSelectedItem().toString(), "false");
			nbPlacesDisponibles.setText(placeDispo);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		nbPlacesDisponibles.setBounds(189, 114, 106, 20);
		panel_3.add(nbPlacesDisponibles);
		nbPlacesDisponibles.setColumns(10);

		nbPlacesOccupes = new JTextField();
		nbPlacesOccupes.setColumns(10);
		nbPlacesOccupes.setEditable(false);
		try {
			String placeOcc = modelPlace.NombrePlace(comboBox_1.getSelectedItem().toString(), "true");
			nbPlacesOccupes.setText(placeOcc);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		nbPlacesOccupes.setBounds(189, 149, 106, 20);
		panel_3.add(nbPlacesOccupes);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("RECETTE", null, panel_4, null);
		panel_4.setLayout(null);

		JComboBox annee = new JComboBox();
		annee.setBounds(150, 64, 109, 20);
		String[] data_4 = modeleReservation.getYear();
		int i_4 = 0;
		while (i_4 < data_4.length) {
			annee.addItem(data_4[i_4]);
			i_4++;
		}
		panel_4.add(annee);

		JLabel lblAnnee = new JLabel("Année :");
		lblAnnee.setBounds(100, 64, 100, 20);
		panel_4.add(lblAnnee);

		JComboBox mois = new JComboBox();
		mois.setBounds(340, 64, 137, 20);
		mois.setEnabled(true);
		String[] data_5 = modeleReservation.getMonth();
		int i_5 = 0;
		while (i_5 < data_5.length) {
			mois.addItem(data_5[i_5]);
			i_5++;
		}
		panel_4.add(mois);

		JCheckBox checkMois = new JCheckBox();
		checkMois.setBounds(280, 64, 20, 20);
		checkMois.setSelected(true);
		panel_4.add(checkMois);

		JLabel lblMois = new JLabel("Mois :");
		lblMois.setBounds(300, 64, 100, 20);
		panel_4.add(lblMois);

		model_4 = new DefaultTableModel();
		model_4.setColumnIdentifiers(column_4);
		try {
			model_4 = new DefaultTableModel(
					modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()), column_4);

		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 110, 869, 293);
		panel_4.add(scrollPane_4);

		table_4 = new JTable();
		scrollPane_4.setViewportView(table_4);
		table_4.setModel(model_4);

		// event
		checkMois.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (mois.isEnabled()) {
					mois.setEnabled(false);
				} else {
					mois.setEnabled(true);
				}
			}

		});

		mois.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				try {
					model_4 = new DefaultTableModel(
							modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
							column_4);
					table_4.setModel(model_4);
				} catch (SQLException | NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		annee.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (mois.isEnabled()) {
					try {
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						model_4 = new DefaultTableModel(modele.rechercheAnnee(annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

		listeNAvion.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				listeNPlace.removeAllItems();
				String[] data_2;
				try {
					data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
					// if (data_2.length == 0) {
					// JOptionPane.showMessageDialog(null, "Aucune place disponible");
					// }
					int i_2 = 0;
					while (i_2 < data_2.length) {
						listeNPlace.addItem(data_2[i_2]);
						i_2++;
					}
				} catch (SQLException | NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		comboBox_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String placeOcc;
				String placeDispo;
				try {
					placeDispo = modelPlace.NombrePlace(comboBox_1.getSelectedItem().toString(), "false");
					nbPlacesDisponibles.setText(placeDispo);
					placeOcc = modelPlace.NombrePlace(comboBox_1.getSelectedItem().toString(), "true");
					nbPlacesOccupes.setText(placeOcc);
					model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
							column_3);
					table_3.setModel(model_3);
				} catch (SQLException | NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table_1.getSelectedRow();
				comboBox.setSelectedItem(model_1.getValueAt(i, 1));
				nbPlaces.setText(model_1.getValueAt(i, 2).toString());
				design.setText(model_1.getValueAt(i, 3).toString());
			}
		});

		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table_2.getSelectedRow();
				listeNPlace.removeAllItems();
				String[] data_2;
				try {
					data_2 = modelPlace.getAllId(listeNAvion.getSelectedItem().toString());
					int i_2 = 0;
					while (i_2 < data_2.length) {
						listeNPlace.addItem(data_2[i_2]);
						i_2++;
					}
					panel_2.add(listeNPlace);
				} catch (SQLException | NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listeNAvion.setSelectedItem(model_2.getValueAt(i, 1));
				listeNPlace.setSelectedItem(model_2.getValueAt(i, 2));
				dateReservation.setText(model_2.getValueAt(i, 3).toString());
				nomVoyageur.setText(model_2.getValueAt(i, 4).toString());
			}
		});

		// event_table

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (villeDepart.getText().equals("") || villeArrivee.getText().equals("")
						|| frais.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez renseigner toutes les champs");
				} else {
					try {
						modelVol.Ajout(villeDepart.getText(), villeArrivee.getText(), frais.getText());
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						e.printStackTrace();
					}
					villeDepart.setText("");
					villeArrivee.setText("");
					frais.setText("");
					JOptionPane.showMessageDialog(null, "Donnée enregistré");
				}
			}
		});
		btnNewButton.setBounds(30, 197, 108, 23);
		panel.add(btnNewButton);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					try {
						String x = model.getValueAt(i, 0).toString();
						modelVol.Supprimer(x);
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Donnée supprimé");
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnSupprimer.setBounds(30, 260, 108, 23);
		panel.add(btnSupprimer);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				String x = model.getValueAt(i, 0).toString();
				if (i >= 0) {
					try {
						modelVol.Update(villeDepart.getText(), villeArrivee.getText(), frais.getText(), x);
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Mise à jour effectué");
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnModifier.setBounds(174, 197, 108, 23);
		panel.add(btnModifier);

		JButton btnReset = new JButton("R\u00E9initialiser");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				villeDepart.setText("");
				villeArrivee.setText("");
				frais.setText("");
			}
		});
		btnReset.setBounds(174, 260, 108, 23);
		panel.add(btnReset);

		// event table_1

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (design.getText().equals("") || nbPlaces.getText().equals("")
						|| comboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Veuillez renseigner toutes les champs");
				} else {
					try {
						modeleAvion.Ajout(design.getText(), nbPlaces.getText(), comboBox.getSelectedItem().toString());
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						e.printStackTrace();
					}
					design.setText("");
					nbPlaces.setText("");
					JOptionPane.showMessageDialog(null, "Donnée enregistré");
				}
			}
		});
		btnAjouter.setBounds(30, 206, 106, 23);
		panel_1.add(btnAjouter);

		JButton btnSupprimer_1 = new JButton("Supprimer");
		btnSupprimer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table_1.getSelectedRow();
				if (i >= 0) {
					try {
						String x = model_1.getValueAt(i, 0).toString();
						String y = model_1.getValueAt(i, 1).toString();
						modeleAvion.Supprimer(x, y);
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Donnée supprimé");
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnSupprimer_1.setBounds(30, 264, 106, 23);
		panel_1.add(btnSupprimer_1);

		JButton btnModifier_1 = new JButton("Modifier");
		btnModifier_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table_1.getSelectedRow();
				String x = model_1.getValueAt(i, 0).toString();
				String y = comboBox.getSelectedItem().toString();
				if (i >= 0) {
					try {
						modeleAvion.Update(design.getText(), nbPlaces.getText(), y, x);
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						e.printStackTrace();
					}
					model_1.setValueAt(y, i, 1);
					model_1.setValueAt(nbPlaces.getText(), i, 2);
					model_1.setValueAt(design.getText(), i, 3);
					JOptionPane.showMessageDialog(null, "Mise à jour effectué");
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnModifier_1.setBounds(167, 206, 107, 23);
		panel_1.add(btnModifier_1);

		JButton btnRinitialiser = new JButton("R\u00E9initialiser");
		btnRinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				design.setText("");
				nbPlaces.setText("");
			}
		});
		btnRinitialiser.setBounds(167, 264, 107, 23);
		panel_1.add(btnRinitialiser);

		// event table_2

		JButton btnAjouter_1 = new JButton("Ajouter");
		btnAjouter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dateReservation.getText().equals("") || nomVoyageur.getText().equals("")
						|| listeNAvion.getSelectedItem() == null || listeNPlace.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Veuillez renseigner toutes les champs");
				} else {
					try {
						if (modelPlace.getOccupation(listeNAvion.getSelectedItem().toString(),
								listeNPlace.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null, "Cette place est déja prise");
						} else {
							modeleReservation.Ajout(listeNAvion.getSelectedItem().toString(),
									listeNPlace.getSelectedItem().toString(), dateReservation.getText(),
									nomVoyageur.getText());
							model = new DefaultTableModel(modelVol.getData(), column);
							table.setModel(model);
							model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
							table_1.setModel(model_1);
							model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
							table_2.setModel(model_2);
							model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
									column_3);
							table_3.setModel(model_3);
							comboBox.removeAllItems();
							String[] data = modelVol.getAllID();
							int w = 0;
							while (w < data.length) {
								comboBox.addItem(data[w]);
								w++;
							}
							panel_1.add(comboBox);
							listeNAvion.removeAllItems();
							String[] data_1 = modeleAvion.getAllID();
							int i_1 = 0;
							while (i_1 < data_1.length) {
								listeNAvion.addItem(data_1[i_1]);
								i_1++;
							}
							panel_2.add(listeNAvion);
							listeNPlace.removeAllItems();
							String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
							int i_2 = 0;
							while (i_2 < data_2.length) {
								listeNPlace.addItem(data_2[i_2]);
								i_2++;
							}
							panel_2.add(listeNPlace);
							comboBox_1.removeAllItems();
							String[] data_3 = modeleAvion.getAllID();
							int i_3 = 0;
							while (i_3 < data_3.length) {
								comboBox_1.addItem(data_3[i_3]);
								i_3++;
							}
							panel_3.add(comboBox_1);
							annee.removeAllItems();
							String[] data_4 = modeleReservation.getYear();
							int i_4 = 0;
							while (i_4 < data_4.length) {
								annee.addItem(data_4[i_4]);
								i_4++;
							}
							panel_4.add(annee);
							mois.removeAllItems();
							String[] data_5 = modeleReservation.getMonth();
							int i_5 = 0;
							while (i_5 < data_5.length) {
								mois.addItem(data_5[i_5]);
								i_5++;
							}
							panel_4.add(mois);
							model_4 = new DefaultTableModel(modele.recherche(mois.getSelectedItem().toString(),
									annee.getSelectedItem().toString()), column_4);
							table_4.setModel(model_4);
							dateReservation.setText("");
							nomVoyageur.setText("");
							JOptionPane.showMessageDialog(null, "Donnée enregistré");
						}
					} catch (SQLException | NullPointerException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnAjouter_1.setBounds(50, 250, 113, 23);
		panel_2.add(btnAjouter_1);

		JButton btnSupprimer_2 = new JButton("Supprimer");
		btnSupprimer_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table_2.getSelectedRow();
				if (i >= 0) {
					try {
						String x = table_2.getValueAt(i, 0).toString();
						modeleReservation.Supprimer(x, listeNAvion.getSelectedItem().toString(),
								listeNPlace.getSelectedItem().toString());
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Donnée supprimé");
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnSupprimer_2.setBounds(50, 297, 113, 23);
		panel_2.add(btnSupprimer_2);

		JButton btnModifier_2 = new JButton("Modifier");
		btnModifier_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table_2.getSelectedRow();
				String x = table_2.getValueAt(i, 0).toString();
				String y = table_2.getValueAt(i, 1).toString();
				String z = table_2.getValueAt(i, 2).toString();
				if (i >= 0) {
					try {
						modeleReservation.Update(y, z, dateReservation.getText(), nomVoyageur.getText(), x);
						model = new DefaultTableModel(modelVol.getData(), column);
						table.setModel(model);
						model_1 = new DefaultTableModel(modeleAvion.getData(), column_1);
						table_1.setModel(model_1);
						model_2 = new DefaultTableModel(modeleReservation.getData(), column_2);
						table_2.setModel(model_2);
						model_3 = new DefaultTableModel(modele.getAllData(comboBox_1.getSelectedItem().toString()),
								column_3);
						table_3.setModel(model_3);
						comboBox.removeAllItems();
						String[] data = modelVol.getAllID();
						int w = 0;
						while (w < data.length) {
							comboBox.addItem(data[w]);
							w++;
						}
						panel_1.add(comboBox);
						listeNAvion.removeAllItems();
						String[] data_1 = modeleAvion.getAllID();
						int i_1 = 0;
						while (i_1 < data_1.length) {
							listeNAvion.addItem(data_1[i_1]);
							i_1++;
						}
						panel_2.add(listeNAvion);
						listeNPlace.removeAllItems();
						String[] data_2 = modelPlace.getIdFalse(listeNAvion.getSelectedItem().toString());
						int i_2 = 0;
						while (i_2 < data_2.length) {
							listeNPlace.addItem(data_2[i_2]);
							i_2++;
						}
						panel_2.add(listeNPlace);
						comboBox_1.removeAllItems();
						String[] data_3 = modeleAvion.getAllID();
						int i_3 = 0;
						while (i_3 < data_3.length) {
							comboBox_1.addItem(data_3[i_3]);
							i_3++;
						}
						panel_3.add(comboBox_1);
						annee.removeAllItems();
						String[] data_4 = modeleReservation.getYear();
						int i_4 = 0;
						while (i_4 < data_4.length) {
							annee.addItem(data_4[i_4]);
							i_4++;
						}
						panel_4.add(annee);
						mois.removeAllItems();
						String[] data_5 = modeleReservation.getMonth();
						int i_5 = 0;
						while (i_5 < data_5.length) {
							mois.addItem(data_5[i_5]);
							i_5++;
						}
						panel_4.add(mois);
						model_4 = new DefaultTableModel(
								modele.recherche(mois.getSelectedItem().toString(), annee.getSelectedItem().toString()),
								column_4);
						table_4.setModel(model_4);
					} catch (SQLException | NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Mise à jour effectué");
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnModifier_2.setBounds(195, 250, 108, 23);
		panel_2.add(btnModifier_2);

		JButton btnRinitialiser_1 = new JButton("R\u00E9initialiser");
		btnRinitialiser_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dateReservation.setText("");
				nomVoyageur.setText("");
			}
		});
		btnRinitialiser_1.setBounds(195, 297, 108, 23);
		panel_2.add(btnRinitialiser_1);

		frmRservationDePlace.setVisible(true);
	}
}
