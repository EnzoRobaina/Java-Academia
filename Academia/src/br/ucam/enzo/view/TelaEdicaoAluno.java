package br.ucam.enzo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.ucam.enzo.model.DAO.AlunoDAO;
import br.ucam.enzo.model.DAO.ModalidadeDAO;
import br.ucam.enzo.model.bean.Aluno;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;

public class TelaEdicaoAluno {

	private JFrame frmTelaEdicaoAluno;
	private JTextField nomeTxt;
	private JTextField dataNascTxt;
	private Aluno aluno;
	
	public JFrame getFrmTelaEdicaoAluno() {
		return frmTelaEdicaoAluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
		//System.out.println(this.aluno.getNome());
	}
	
	
	public Aluno getAluno() {
		return aluno;
	}

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdicaoAluno window = new TelaEdicaoAluno();
					window.frmTelaEdicaoAluno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public TelaEdicaoAluno() {
		initialize();
	}
	
	public TelaEdicaoAluno(Aluno aluno) {
		 
		this.aluno = aluno;
			
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		System.out.println(aluno.getNome());
		frmTelaEdicaoAluno = new JFrame();
		frmTelaEdicaoAluno.setResizable(false);
		frmTelaEdicaoAluno.setTitle("Tela Edi\u00E7\u00E3o Aluno");
		frmTelaEdicaoAluno.setBounds(100, 100, 330, 214);
		frmTelaEdicaoAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTelaEdicaoAluno.getContentPane().setLayout(null);
		
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 8, 46, 14);
		frmTelaEdicaoAluno.getContentPane().add(lblNome);
		
		nomeTxt = new JTextField();
		nomeTxt.setBounds(6, 23, 315, 20);
		frmTelaEdicaoAluno.getContentPane().add(nomeTxt);
		nomeTxt.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("Data nasc.:");
		lblDataNasc.setBounds(10, 46, 90, 14);
		frmTelaEdicaoAluno.getContentPane().add(lblDataNasc);
		
		//FORMATA A DATA
		
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		JFormattedTextField dataNascTxt = new JFormattedTextField(format);
		dataNascTxt.setBounds(7, 61, 133, 20);
		frmTelaEdicaoAluno.getContentPane().add(dataNascTxt);
		dataNascTxt.setColumns(10);
		
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
	        dateMask.install(dataNascTxt);
	    } catch (ParseException ex) {
	    	JOptionPane.showMessageDialog(null, "Data Inv�lida");
	      }
		
		JLabel lblModalidade = new JLabel("Modalidade: ");
		lblModalidade.setBounds(10, 85, 90, 14);
		frmTelaEdicaoAluno.getContentPane().add(lblModalidade);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 101, 118, 25);
		frmTelaEdicaoAluno.getContentPane().add(comboBox);
		
		//PREENCHENDO COMBOBOX
		ModalidadeDAO mDao = new ModalidadeDAO();
		ArrayList modalidades = mDao.fillComboModalidades();
				
		for(int i = 0; i<modalidades.size();i++) {
			comboBox.addItem(modalidades.get(i));
		}
		
		nomeTxt.setText(aluno.getNome());
		dataNascTxt.setText(aluno.getDataNasc());
		
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunoDAO dao = new AlunoDAO();
				
				aluno.setId(aluno.getId());
				aluno.setNome(nomeTxt.getText());
				aluno.setDataNasc(dataNascTxt.getText());
				aluno.setModalidade(comboBox.getSelectedItem().toString());
				
				dao.update(aluno);
				//JOptionPane.showMessageDialog(null, "teste");
			}
		});
		btnSalvar.setBounds(6, 139, 313, 39);
		frmTelaEdicaoAluno.getContentPane().add(btnSalvar);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaEdicaoAluno.class.getResource("/imagens/user_icon.png")));
		lblNewLabel.setBounds(236, 50, 76, 76);
		frmTelaEdicaoAluno.getContentPane().add(lblNewLabel);
	}
}
