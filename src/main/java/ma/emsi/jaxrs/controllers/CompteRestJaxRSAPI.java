package ma.emsi.jaxrs.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.emsi.jaxrs.entities.Compte;
import ma.emsi.jaxrs.entities.CompteListWrapper;
import ma.emsi.jaxrs.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {

    @Autowired
    private CompteRepository compteRepository;

    @Path("/comptes")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public CompteListWrapper listComptes() {
        return new CompteListWrapper(compteRepository.findAll()); // Wrap the list in CompteListWrapper
    }

    @Path("/comptes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte getCompte(@PathParam("id") Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    @Path("/comptes")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte save(Compte c) {
        return compteRepository.save(c);
    }

    @Path("/comptes/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte updateCompte(@PathParam("id") Long id, Compte c) {
        Compte existingCompte = compteRepository.findById(id).orElse(null);
        if (existingCompte != null) {
            existingCompte.setSolde(c.getSolde());
            existingCompte.setDateCreation(c.getDateCreation());
            existingCompte.setTypeCompte(c.getTypeCompte());
            return compteRepository.save(existingCompte);
        }
        return null;
    }

    @Path("/comptes/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteCompte(@PathParam("id") Long id) {
        compteRepository.deleteById(id);
    }
}
