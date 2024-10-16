package view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import controller.EntradaFinanceiraController;
import model.EntradaFinanceira;
import utils.enums.Classifications;

import java.util.Date;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class App{

    public App() {

        JFrame frame = new JFrame("Finances");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLayout(new GridLayout(10,5));

        JPanel tela = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tela.setSize(1920,1080);

        JLabel labelNome = new JLabel();
        labelNome.setText("Nome");
        JTextField inputNome = new JTextField(10);

        JLabel labelClassif = new JLabel();
        labelClassif.setText("Classificação:");
        JComboBox<Classifications> inputClassif = new JComboBox<>(new Classifications[] {Classifications.ALIMENTACAO, Classifications.AUTOMOVEL, Classifications.BEM_ESTAR, Classifications.CASA, Classifications.COMPRAS, Classifications.INVESTIMENTO, Classifications.OUTROS, Classifications.SALARIO, Classifications.SAUDE});

        JLabel labelValor = new JLabel();
        labelValor.setText("Valor");
        JTextField inputValor = new JTextField(10);

        JLabel labelData = new JLabel();
        labelData.setText("Data da entrada");
        JTextField inputData = new JTextField(10);

        JButton cadastrar = new JButton("Cadastrar");

        JCheckBox receber = new JCheckBox("Ganho");

        JCheckBox pagar = new JCheckBox("Gasto");

        DefaultTableModel tabelModel = montarTabela();

        JTable tabela = new JTable(tabelModel);

        JScrollPane scrollPane = new JScrollPane(tabela);

        frame.add(tela);

        tela.add(labelNome);
        tela.add(inputNome);

        tela.add(labelClassif);
        tela.add(inputClassif);

        tela.add(labelValor);
        tela.add(inputValor);
        
        tela.add(receber);
        tela.add(pagar);

        tela.add(labelData);
        tela.add(inputData);

        tela.add(cadastrar);

        frame.add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Excluir");
        deleteButton.setEnabled(false);
        deleteButton.setVisible(false);
        tela.add(deleteButton);


        cadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                try{
                    double valorEntrada = Double.parseDouble(inputValor.getText());

                    String tipoEntrada;

                    if(receber.isSelected()){
                        tipoEntrada = receber.getText();
                    } else{
                        tipoEntrada = receber.getText();
                    }

                    EntradaFinanceira entrada = new EntradaFinanceira(inputNome.getText(), (Classifications) inputClassif.getSelectedItem(), valorEntrada, inputData.getText(), LocalDate.now(), tipoEntrada);

                    EntradaFinanceiraController.createEntry(entrada);
                
                    JOptionPane.showMessageDialog(frame, "Entrada cadastrada com sucesso!");

                    tabelModel.addRow(new Object[]{inputNome.getText(), (Classifications) inputClassif.getSelectedItem(), "R$" +valorEntrada, inputData.getText(), LocalDate.now()});

                    //resetar os valores dos campos.
                    inputNome.setText("");
                    inputValor.setText("");
                    inputData.setText("");

                } catch(NumberFormatException error){
                    JOptionPane.showMessageDialog(frame, "Favor preencher todos os campos!");
                    System.out.println(error.getMessage());
                }
            }
        });

        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event){
                if(!event.getValueIsAdjusting() && tabela.getSelectedRow() != -1){

                    deleteButton.setVisible(true);
                    deleteButton.setEnabled(true);
                    
                    int selectedRow = tabela.getSelectedRow();

                    String nome = (String) tabelModel.getValueAt(selectedRow, 0);

                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event){

                            EntradaFinanceiraController.deleteEntry(nome);

                            tabelModel.removeRow(selectedRow);

                            deleteButton.setEnabled(false);

                            deleteButton.setVisible(false);
                        }
                    });
                }
            }
        });

        
        frame.setVisible(true);
    }

    //método para criar a tabela
    public DefaultTableModel montarTabela(){
        
        DefaultTableModel tabelModel = new DefaultTableModel();
        
        tabelModel.addColumn("Nome");
        tabelModel.addColumn("Classificação");
        tabelModel.addColumn("Valor");
        tabelModel.addColumn("Data da Entrada");
        tabelModel.addColumn("Data de Cadastro");

        List<Document> entradas = EntradaFinanceiraController.getEntries();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        for(Document entrada : entradas){
            String nome = entrada.getString("nome");
            String classif = entrada.getString("classificacao");
            String valor = String.valueOf(entrada.getDouble("valor"));
            String dataEntrada = entrada.getString("dataEntrada");
            Date dataCadastro = entrada.getDate("dataCadastro");

            String dataFormatada = (dataCadastro != null) ? dateFormatter.format(dataCadastro) : "";

            tabelModel.addRow(new Object[]{nome, classif, "R$"+valor, dataEntrada, dataFormatada});
        }

        return tabelModel;

    }

}
