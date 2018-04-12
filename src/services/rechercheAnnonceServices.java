/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Annonce;
import Entities.rechercheAnnonce;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static services.UserService.conn;
import utils.MyConnection;

/**
 *
 * @author Win10
 */
public class rechercheAnnonceServices {
    private final Connection c = MyConnection.getInstance().getConnection();

    public rechercheAnnonceServices() {}
    
    public void AjouterRAnnonce(rechercheAnnonce a) {
     PreparedStatement st;
     String query="insert into recherche (recherche,idUser) values(?,?)";
        try {
            st= c.prepareStatement(query);
            st.setString(1,a.getRecherche());
            st.setInt(2,conn);
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
            
        } 
        catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de la recherche l'annonce " + ex.getMessage());
        }
    }
    public List<rechercheAnnonce> AfficherAllRAnnonceC()
    {
        List<rechercheAnnonce> Ann= new ArrayList<>();
        
       String query="select recherche,idUser from recherche ";
       int e;
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                rechercheAnnonce A=new rechercheAnnonce();
                A.setRecherche(rs.getString(1));
                A.setIdUser(rs.getInt(2));
                Ann.add(A);
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }
    public int Count2() {
        AnnonceServices a= new AnnonceServices();
        List<Annonce> l=a.AfficherAllAnnonce();
        int i = 0;
        try {
            for (Annonce b : l){
            PreparedStatement pt;
            String query = "select * from recherche where recherche=?";
            pt=c.prepareStatement(query);
            pt.setString(1,b.getNomAnnonce());
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                i+=1;
            }
            }
            System.out.println(i);
            return i;
            
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return 0;
    }
    
     public int Count() {
        int i=0;
        try {
            PreparedStatement pt;
            String query = "select * from recherche where idUser='"+conn+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                i+=1;
            }
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return i;
    }
     public void SupprimerAnnonceA() {
        try {
            PreparedStatement st;
            String query = "delete from recherche where idUser='"+conn+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de la recherche de l'annonce " + ex.getMessage());
        }
    }
    
    
}
