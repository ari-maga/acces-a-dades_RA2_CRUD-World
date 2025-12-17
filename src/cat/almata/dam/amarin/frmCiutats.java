package cat.almata.dam.amarin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCiutats {

	private JFrame finestraCiutats;
	private JTable tblCiutats;
	private JTextField txfCercaCiutat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCiutats window = new frmCiutats();
					window.finestraCiutats.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmCiutats() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		finestraCiutats = new JFrame();
		finestraCiutats.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					fillCiutats(DataActions.getAllCiutats());
				}catch (SQLException e1){
					JOptionPane.showMessageDialog(finestraCiutats,  "ERROR! En llistar les ciutat (frmCiutats, lin. 61)\n\n"+e1.getMessage(),"Error de SQL", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		finestraCiutats.setTitle("Ciutats");
		finestraCiutats.setBounds(100, 100, 742, 614);
		finestraCiutats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel pnlSuperor = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlSuperor.getLayout();
		flowLayout.setVgap(15);
		finestraCiutats.getContentPane().add(pnlSuperor, BorderLayout.NORTH);
		
		txfCercaCiutat = new JTextField();
		pnlSuperor.add(txfCercaCiutat);
		txfCercaCiutat.setColumns(20);
		
		JButton btnCercaCiutat = new JButton("Cerca Ciutat");
		btnCercaCiutat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fillCiutats(DataActions.getCiutats(txfCercaCiutat.getText()));
				}catch (SQLException e1){
					JOptionPane.showMessageDialog(finestraCiutats,  "ERROR! En llistar les ciutat (frmCiutats, lin. 85)\n\n"+e1.getMessage(),"Error de SQL", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pnlSuperor.add(btnCercaCiutat);
		
		JPanel pnlInferior = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlInferior.getLayout();
		flowLayout_1.setVgap(15);
		finestraCiutats.getContentPane().add(pnlInferior, BorderLayout.SOUTH);
		
		JButton btnNovaCiutat = new JButton("Nova Ciutat");
		btnNovaCiutat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlInferior.add(btnNovaCiutat);
		
		JButton btnModificaCiutat = new JButton("Modifica Ciutat");
		btnModificaCiutat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlInferior.add(btnModificaCiutat);
		
		JButton btnEsborraCiutat = new JButton("Esborra Ciutat");
		btnEsborraCiutat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pnlInferior.add(btnEsborraCiutat);
		
		JScrollPane scpTaulaCiutats = new JScrollPane();
		finestraCiutats.getContentPane().add(scpTaulaCiutats, BorderLayout.CENTER);
		
		tblCiutats = new JTable();
		tblCiutats.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nom", "Pa\u00EDs", "Districte", "Poblaci\u00F3"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCiutats.getColumnModel().getColumn(0).setResizable(false);
		tblCiutats.getColumnModel().getColumn(0).setPreferredWidth(114);
		tblCiutats.getColumnModel().getColumn(0).setMinWidth(24);
		tblCiutats.getColumnModel().getColumn(1).setResizable(false);
		tblCiutats.getColumnModel().getColumn(1).setPreferredWidth(116);
		tblCiutats.getColumnModel().getColumn(2).setResizable(false);
		tblCiutats.getColumnModel().getColumn(2).setPreferredWidth(65);
		tblCiutats.getColumnModel().getColumn(3).setResizable(false);
		tblCiutats.getColumnModel().getColumn(3).setPreferredWidth(54);
		scpTaulaCiutats.setViewportView(tblCiutats);
	}

	protected void fillCiutats(List<DTOMainformCiutat> llistaCiutats) {
		//Obtenim el model
		DefaultTableModel model = (DefaultTableModel)tblCiutats.getModel();
		//Esborrem totes les files
		for(int i = model.getRowCount()-1;i>=0;i--) {
			model.removeRow(i);
		}
		//Y omplim amb les noves
		for(DTOMainformCiutat ciutat: llistaCiutats) {
			model.addRow(new Object[] {ciutat.getNom(),ciutat.getPais(),ciutat.getDistricte(), ciutat.getPoblacio()});
		}
	}

}
