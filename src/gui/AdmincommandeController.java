/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static utils.util.somme;
import Entities.Produit;
import Entities.User;
import Entities.confcommande;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;
import services.Serviceconfcommande1;
import services.ServiceProduit;
import services.ServiceProduitm;
import services.UserService;
import static services.UserService.conn;
import utils.SmsInscriConfirmCom;
import utils.util;

/**
 * FXML Controller class
 *
 * @author maryem
 */
public class AdmincommandeController implements Initializable 
{
    ObservableList<confcommande> listconfcommande = FXCollections.observableArrayList();
  
  
    @FXML
    private Button confirmer;
    @FXML
    private Button refuser;
    @FXML
    private Button afficher;
    @FXML
    private Text artisan;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> idProduit;
    @FXML
    private TableView<confcommande> affichage;
    @FXML
    private TableColumn<?, ?> idUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {  nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idProduit.setCellValueFactory(new PropertyValueFactory<>("idP"));
        idUser.setCellValueFactory(new PropertyValueFactory<>("User"));
        
       Serviceconfcommande1 sp= new Serviceconfcommande1();
        try {
            listconfcommande = sp.afficherconfcommande(); 
           // System.out.println(listconfcommande);
                  affichage.setItems(listconfcommande);
        } catch (SQLException ex) {
            Logger.getLogger(AdmincommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    @FXML
    private void confirmercommande(ActionEvent event) throws SQLException {
        
      ServiceProduitm sp = new ServiceProduitm();
      Serviceconfcommande1 c= new Serviceconfcommande1();
      affichage.setEditable(true);
      int selectedIndex = affichage.getSelectionModel().getSelectedIndex();
       confcommande a = affichage.getSelectionModel().getSelectedItem();
       int u;
        ServiceProduit us=new ServiceProduit();
       u=(int) us.RechercherAnnonceById(a.getIdP());
       if (selectedIndex >= 0) {
          affichage.getItems().remove(selectedIndex);
          // System.out.println(x);
System.out.println(a.getNom());
         sp.approuverdelate2(u);
         c.supprimercommande(a.getNom());
           somme -=u;
        
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection un produit");
            alert.setHeaderText("vous n'avez pas sélectionner un produit !");
            alert.setContentText("veuillez sélectionner un produit dans la table");
            alert.showAndWait();

        }
    //   int x=p.getId();
    }

    @FXML
    private void refusercommande(ActionEvent event) throws SQLException {
        ServiceProduitm sp = new ServiceProduitm();
     Serviceconfcommande1 c= new Serviceconfcommande1();
      affichage.setEditable(true);
        int selectedIndex = affichage.getSelectionModel().getSelectedIndex();

         confcommande a = affichage.getSelectionModel().getSelectedItem();
         ServiceProduit spk=new ServiceProduit();
         
         double z =spk.RechercherAnnonceById(a.getIdP());
    //   int x=p.getId();

        if (selectedIndex >= 0) {
           affichage.getItems();
              affichage.getItems().remove(selectedIndex);
            System.out.println(z);
            sp.approuverdelate2(z);   
            c.supprimercommande(a.getNom());
         // System.out.println(a);
//System.out.println(p.getLongitude());
        }
        else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection");
            alert.setHeaderText("vous n'avez pas sélectionner une commande!");
            alert.setContentText("veuillez sélectionner une commande la table");
            alert.showAndWait();

        }

    }

    @FXML
    private void affichercommande(ActionEvent event) {
        
    }
}
//********************************************************************
// @FXML
//    private void affichercommande(ActionEvent event) 
//    
//      {  
//        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//       prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//        
//     
// //       Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
//       
//       ServiceProduit sp= new ServiceProduit();
//   try{
//    //   listconfcommande = sp.afficherproduitcommande();
//  
//    affichage.setItems(listconfcommande);
//
//       
//         
//    }catch (SQLException ex) {
//            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
//       }
//    
//    }
//*******************************************************************    
//
//    @FXML
//    private void confirmercommande(ActionEvent event)
//    {
//    }
// ****************************************************************
//
//    @FXML
//    private void refusercommande(ActionEvent event) throws SQLException 
//    {  ServiceProduit sp = new ServiceProduit();
//     ServiceConfcommande c= new ServiceConfcommande();
//      affichage.setEditable(true);
//        int selectedIndex = affichage.getSelectionModel().getSelectedIndex();
//
//         confcommande a = affichage.getSelectionModel().getSelectedItem();
//         
//         Double z=a.getProduit().getPrix();
//       int x=p.getId();
//
//        if (selectedIndex >= 0) {
//           affichage.getItems();
//              affichage.getItems().remove(selectedIndex);
//            System.out.println(z);
//              sp.approuverdelate(z);     
//        c.supprimercommande(a.getNom());
//          System.out.println(a);
//System.out.println(p.getLongitude());
//            
//          
//        
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Pas de Selection");
//            alert.setHeaderText("vous n'avez pas sélectionner une commande !");
//            alert.setContentText("veuillez sélectionner une commande dans la table");
//            alert.showAndWait();
//
//        }
//
//        
//    }
//
// //************************************************************************  

  
