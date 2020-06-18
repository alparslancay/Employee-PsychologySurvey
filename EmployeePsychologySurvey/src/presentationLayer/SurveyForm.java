/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;
import businessLayer.AnalystOperations;
import entityLayer.EmployeeSurvey;
import entityLayer.SurveyQuestion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Stack;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author alp_1
 */
public class SurveyForm extends javax.swing.JFrame {

    AnalystOperations analystOperations = new AnalystOperations();
    /** Creates new form SurveyForm */
    public SurveyForm() {
        initComponents();
        SetListModel();
        txtbx_Question.setText("");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    private void SetListModel(){
        DefaultListModel<SurveyQuestion> listModel = new DefaultListModel<SurveyQuestion>();
        lst_Questions.setModel(listModel);  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtbx_Question = new javax.swing.JTextField();
        btn_AddQuestion = new javax.swing.JButton();
        btn_CreateSurvey = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst_Questions = new javax.swing.JList<>();
        btn_DeleteQuestion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chanket Analysis Software");

        btn_AddQuestion.setText("Add Question!");
        btn_AddQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddQuestionActionPerformed(evt);
            }
        });

        btn_CreateSurvey.setText("Create Survey!");
        btn_CreateSurvey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateSurveyActionPerformed(evt);
            }
        });

        lst_Questions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lst_Questions);

        btn_DeleteQuestion.setText("Delete Question!");
        btn_DeleteQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteQuestionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addComponent(txtbx_Question))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_DeleteQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_AddQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(btn_CreateSurvey)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_DeleteQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbx_Question, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_AddQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_CreateSurvey, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CreateSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateSurveyActionPerformed

    DefaultListModel<SurveyQuestion> listModel =(DefaultListModel<SurveyQuestion>)lst_Questions.getModel();
    
    if(listModel.size()!=0){
        
    List<SurveyQuestion> questions = new Stack<>();
    EmployeeSurvey employeeSurvey = new EmployeeSurvey();
    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    
    String currentTime = dtf.format(dateTime);
    
    employeeSurvey.surveyDate = currentTime;
    
    analystOperations.InsertSurvey(employeeSurvey);
    int surveyID = analystOperations.GetSurveyID(dateTime);
    

        for (int questionRecorder = 0; questionRecorder < listModel.size(); questionRecorder++) {
            listModel.getElementAt(questionRecorder).SurveyID = surveyID;
            questions.add(listModel.get(questionRecorder));
        }
    analystOperations.InsertQuestions(questions);
    
    dispose();
    }
    else
        JOptionPane.showMessageDialog(this, "Not found a question in list!");
  
    }//GEN-LAST:event_btn_CreateSurveyActionPerformed

    private void btn_DeleteQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteQuestionActionPerformed
        if(lst_Questions.getSelectedIndex()!=-1){
        DefaultListModel<SurveyQuestion> listModel =(DefaultListModel<SurveyQuestion>)lst_Questions.getModel();
        listModel.remove(lst_Questions.getSelectedIndex());
        }
        else 
            JOptionPane.showMessageDialog(this, "Not found a selected question!");
    }//GEN-LAST:event_btn_DeleteQuestionActionPerformed

    private void btn_AddQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddQuestionActionPerformed
        DefaultListModel<SurveyQuestion> listModel =(DefaultListModel<SurveyQuestion>)lst_Questions.getModel();
        if(!"".equals(txtbx_Question.getText())){
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.questionText = txtbx_Question.getText();
        txtbx_Question.setText("");
        listModel.addElement(surveyQuestion);
        }
        else
            JOptionPane.showMessageDialog(this, "Not found a question text!");
    }//GEN-LAST:event_btn_AddQuestionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AddQuestion;
    private javax.swing.JButton btn_CreateSurvey;
    private javax.swing.JButton btn_DeleteQuestion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<SurveyQuestion> lst_Questions;
    private javax.swing.JTextField txtbx_Question;
    // End of variables declaration//GEN-END:variables

}