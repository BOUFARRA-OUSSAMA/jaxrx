package ma.emsi.jaxrs.providers;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import ma.emsi.jaxrs.entities.CompteListWrapper;

import java.io.OutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class CompteListWrapperMessageBodyWriter implements MessageBodyWriter<CompteListWrapper> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == CompteListWrapper.class;
    }

    @Override
    public void writeTo(
            CompteListWrapper compteListWrapper,
            Class<?> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CompteListWrapper.class);
            jaxbContext.createMarshaller().marshal(compteListWrapper, entityStream);
        } catch (JAXBException e) {
            throw new RuntimeException("Error serializing CompteListWrapper to XML", e);
        }
    }

    @Override
    public long getSize(CompteListWrapper compteListWrapper, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }
}
