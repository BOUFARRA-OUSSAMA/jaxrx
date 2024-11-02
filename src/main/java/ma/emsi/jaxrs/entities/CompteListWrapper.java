package ma.emsi.jaxrs.entities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import java.util.List;

@XmlRootElement(name = "comptes") // Root element for the XML document
@XmlAccessorType(XmlAccessType.FIELD) // Enables JAXB to directly access fields
public class CompteListWrapper {

    @XmlElement(name = "compte") // Specifies that each item in the list should be wrapped in <compte> tags
    private List<Compte> comptes;

    // No-arg constructor required by JAXB
    public CompteListWrapper() {}

    public CompteListWrapper(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
