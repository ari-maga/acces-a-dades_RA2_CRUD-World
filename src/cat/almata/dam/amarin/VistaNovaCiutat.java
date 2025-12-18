package cat.almata.dam.amarin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaNovaCiutat extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlContingut = new JPanel();
	private JTextField txfNom;
	private JTextField txfPoblacio;
	private VistaNovaCiutat altaCiutat;
	private JComboBox<String> cbxPais;
	private JComboBox<String> cbxPais_1;
	private JComboBox<String> cbxDistricte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VistaNovaCiutat dialog = new VistaNovaCiutat(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VistaNovaCiutat(JFrame owner) {
		super(owner);
		altaCiutat = this;
		setTitle("Nova Ciutat");
		setBounds(100, 100, 306, 239);
		getContentPane().setLayout(new BorderLayout());
		pnlContingut.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContingut, BorderLayout.CENTER);
		GridBagLayout gbl_pnlContingut = new GridBagLayout();
		gbl_pnlContingut.columnWidths = new int[] {0, 0};
		gbl_pnlContingut.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pnlContingut.columnWeights = new double[]{0.0, 1.0};
		gbl_pnlContingut.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlContingut.setLayout(gbl_pnlContingut);
		{
			JLabel lblTitol = new JLabel("Introdueix les dades de la nova ciutat");
			lblTitol.setFont(new Font("Tahoma", Font.BOLD, 15));
			GridBagConstraints gbc_lblTitol = new GridBagConstraints();
			gbc_lblTitol.ipady = 20;
			gbc_lblTitol.gridwidth = 2;
			gbc_lblTitol.insets = new Insets(0, 0, 5, 0);
			gbc_lblTitol.gridx = 0;
			gbc_lblTitol.gridy = 0;
			pnlContingut.add(lblTitol, gbc_lblTitol);
		}
		{
			JLabel lblNom = new JLabel("Nom:");
			GridBagConstraints gbc_lblNom = new GridBagConstraints();
			gbc_lblNom.ipadx = 5;
			gbc_lblNom.insets = new Insets(0, 0, 5, 5);
			gbc_lblNom.anchor = GridBagConstraints.EAST;
			gbc_lblNom.gridx = 0;
			gbc_lblNom.gridy = 1;
			pnlContingut.add(lblNom, gbc_lblNom);
		}
		{
			txfNom = new JTextField();
			txfNom.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(txfNom.getText().length()>33) {
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_txfNom = new GridBagConstraints();
			gbc_txfNom.insets = new Insets(0, 0, 5, 0);
			gbc_txfNom.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfNom.gridx = 1;
			gbc_txfNom.gridy = 1;
			pnlContingut.add(txfNom, gbc_txfNom);
			txfNom.setColumns(10);
		}
		{
			JLabel lblPais = new JLabel("País:");
			GridBagConstraints gbc_lblPais = new GridBagConstraints();
			gbc_lblPais.ipadx = 5;
			gbc_lblPais.anchor = GridBagConstraints.EAST;
			gbc_lblPais.insets = new Insets(0, 0, 5, 5);
			gbc_lblPais.gridx = 0;
			gbc_lblPais.gridy = 2;
			pnlContingut.add(lblPais, gbc_lblPais);
		}
		{
			cbxPais = new JComboBox<String>();
			try {
				cbxPais_1 = new JComboBox<String>(DataActions.getVectorPaisos());
				cbxPais_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							cbxDistricte.setModel( new JComboBox<String>(DataActions.getVectorDistrictes((String) cbxPais_1.getSelectedItem())).getModel());
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(altaCiutat,  "ERROR! En llistar els Districtes (VistaAltaCiutat, lin. 1)\n\n"+e1.getMessage(),"Error de SQL", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(altaCiutat,  "ERROR! En llistar els Paisos (VistaAltaCiutat, lin. 107)\n\n"+e.getMessage(),"Error de SQL", JOptionPane.ERROR_MESSAGE);
			}
			GridBagConstraints gbc_cbxPais = new GridBagConstraints();
			gbc_cbxPais.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxPais.insets = new Insets(0, 0, 5, 0);
			gbc_cbxPais.gridx = 1;
			gbc_cbxPais.gridy = 2;
			pnlContingut.add(cbxPais_1, gbc_cbxPais);
		}
		{
			JLabel lblDistricte = new JLabel("Districte:");
			GridBagConstraints gbc_lblDistricte = new GridBagConstraints();
			gbc_lblDistricte.ipadx = 5;
			gbc_lblDistricte.anchor = GridBagConstraints.EAST;
			gbc_lblDistricte.insets = new Insets(0, 0, 5, 5);
			gbc_lblDistricte.gridx = 0;
			gbc_lblDistricte.gridy = 3;
			pnlContingut.add(lblDistricte, gbc_lblDistricte);
		}
		{
			try {
				cbxDistricte = new JComboBox<String>(DataActions.getVectorDistrictes((String)cbxPais_1.getSelectedItem()));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(altaCiutat,  "ERROR! En llistar els Districtes (VistaAltaCiutat, lin. 1)\n\n"+e.getMessage(),"Error de SQL", JOptionPane.ERROR_MESSAGE);
			}
			GridBagConstraints gbc_cbxDistricte = new GridBagConstraints();
			gbc_cbxDistricte.insets = new Insets(0, 0, 5, 0);
			gbc_cbxDistricte.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxDistricte.gridx = 1;
			gbc_cbxDistricte.gridy = 3;
			pnlContingut.add(cbxDistricte, gbc_cbxDistricte);
		}
		{
			JLabel lblPoblacio = new JLabel("Població:");
			GridBagConstraints gbc_lblPoblacio = new GridBagConstraints();
			gbc_lblPoblacio.anchor = GridBagConstraints.EAST;
			gbc_lblPoblacio.insets = new Insets(0, 0, 0, 5);
			gbc_lblPoblacio.gridx = 0;
			gbc_lblPoblacio.gridy = 4;
			pnlContingut.add(lblPoblacio, gbc_lblPoblacio);
		}
		{
			txfPoblacio = new JTextField();
			txfPoblacio.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(!esNumero(e.getKeyChar())||txfPoblacio.getText().length()>9) {
						e.consume();
					}
				}
				private boolean esNumero(char caracter) {
					if(caracter == '0'||
							caracter == '1' ||
							caracter == '2' ||
							caracter == '3' ||
							caracter == '4' ||
							caracter == '5' ||
							caracter == '6' ||
							caracter == '7' ||
							caracter == '8' ||
							caracter == '9')return true;
					return false;
				}
			});
			GridBagConstraints gbc_txfPoblacio = new GridBagConstraints();
			gbc_txfPoblacio.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfPoblacio.gridx = 1;
			gbc_txfPoblacio.gridy = 4;
			pnlContingut.add(txfPoblacio, gbc_txfPoblacio);
			txfPoblacio.setColumns(10);
		}
		{
			JPanel pnlBotons = new JPanel();
			pnlBotons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlBotons, BorderLayout.SOUTH);
			{
				JButton btnAfegir = new JButton("Afegeix");
				btnAfegir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Comprovar que la ciutat no estigui repetida
						//Comprovar que la població estigui entre els limits establerts
						if(comprovacioCiutatRepetida() && comprovacioPoblacio()) {
							//Crear la ciutat
							try {
								DataActions.createCiutat(new DTOMainformCiutat(
										txfNom.getText(),
										(String) cbxPais_1.getSelectedItem(),
										(String) cbxDistricte.getSelectedItem(),
										Long.parseLong(txfPoblacio.getText())
										));
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(altaCiutat,  "ERROR! En crear la nova ciutat (VistaAltaCiutat, lin. 219)\n\n"+e1.getMessage(),"Error de SQL", JOptionPane.ERROR_MESSAGE);
							}
							
							
							//Torna a la taula de ciutats
							altaCiutat.dispose();
							owner.setEnabled(true);
							owner.requestFocus();
						}
						
					}
					private boolean comprovacioCiutatRepetida() {
						try {
							if(!DataActions.esCiutatRepetida(txfNom.getText(), (String)  cbxPais_1.getSelectedItem())) {
								return true;
							}else {
								JOptionPane.showMessageDialog(altaCiutat,  "ERROR! Ja hi ha una ciutat en aquest país amb aquest nom!","Nom no vàlid", JOptionPane.WARNING_MESSAGE);
								txfNom.setText("");
								txfNom.requestFocus();
							}
						} catch (HeadlessException e) {
							JOptionPane.showMessageDialog(altaCiutat,  "ERROR! Excepió no prevista (VistaAltaCiutat, lin. 233)\n\n"+e.getMessage(),"Error de Headless Exception", JOptionPane.ERROR_MESSAGE);
							return false;
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(altaCiutat,  "ERROR! En buscar si la ciutat esta repeticda en base de dades (VistaAltaCiutat, lin. 233)\n\n"+e.getMessage(),"Error de SQL", JOptionPane.ERROR_MESSAGE);
							return false;
						}
						return false;
						
					}
					private boolean comprovacioPoblacio() {
						long num;
						//miro que sigui un numero amb el que pugi tractar
						try {
							num  = Long.parseLong(txfPoblacio.getText());
						}catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(altaCiutat,  "ERROR! La població introduïda no és vàlida.\nComprova que la població sigui un numero d'entre\n10.000 i 5.000.000.000","Població no vàlida", JOptionPane.WARNING_MESSAGE);
							return false;
						}
						if(num>=10000&&num<=5000000000L)return true;
						else{
							JOptionPane.showMessageDialog(altaCiutat,  "ERROR! La població introduïda no és vàlida.\nComprova que la població sigui un numero d'entre\n10.000 i 5.000.000.000","Població no vàlida", JOptionPane.WARNING_MESSAGE);
							return false;
						}
					}
				});
				btnAfegir.setActionCommand("OK");
				pnlBotons.add(btnAfegir);
				getRootPane().setDefaultButton(btnAfegir);
			}
			{
				JButton btnCancelar = new JButton("Cancel·lar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						altaCiutat.dispose();;
						owner.setEnabled(true);
						owner.requestFocus();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				pnlBotons.add(btnCancelar);
			}
		}
	}

}
