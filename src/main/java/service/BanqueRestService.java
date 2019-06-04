package service;

import entite.Compte;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;


@Path("/banque")
public class BanqueRestService {
    // simuler une bdd - la map comptes sera chargée une seule fois car static
    private static Map<Integer, Compte> comptes = new HashMap<>();

    @GET
    @Path("/conversion/{montant}")
    @Produces(MediaType.APPLICATION_JSON)
    public double conversion(@PathParam(value="montant") double mt) {
        return mt * 4000;
    }

    @GET
    @Path("/comptes/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte getCompte(@PathParam(value="code") int code) {
        return comptes.get(code);
    }

    @GET
    @Path("/comptes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compte> getListeCompte() {
        return new ArrayList<>(comptes.values());
   }

   @POST
   @Path("/comptes")
   @Produces(MediaType.APPLICATION_JSON)
   public Compte save(Compte compte) {
        compte.setDateCreation(new Date());
        comptes.put(compte.getCode(), compte);
        return compte;
   }

    @PUT
    @Path("/comptes/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte update(Compte compte, @PathParam(value="code") int code) {
        comptes.put(code, compte);
        return compte;
    }

    @DELETE
    @Path("/comptes/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam(value="code") int code) {
        comptes.remove(code);
    }
}
