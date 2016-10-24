package org.fieldenbriggs.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GSONMapper implements MessageBodyWriter<Object>, MessageBodyReader<Object> {
    private static final String UTF_8 = "UTF-8";

    private Gson gson;

    private Gson getGson() {
        if(gson == null) {
            gson = new Gson();
        }

        return gson;
    }

    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public Object readFrom(Class<Object> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        System.out.println(aClass.toString());
        System.out.println(type.toString());

        InputStreamReader streamReader = null;

        try {
            streamReader = new InputStreamReader(inputStream, UTF_8);
            Type jsonType;

            if(aClass.equals(type)) {
                jsonType = aClass;
            } else {
                jsonType = type;
            }

            return getGson().fromJson(streamReader, jsonType);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            streamReader.close();
        }

        return null;
    }

    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public long getSize(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, UTF_8);

        try {
            Type jsonType;

            if(aClass.equals(type)) {
                jsonType = aClass;
            } else {
                jsonType = type;
            }

            getGson().toJson(o, type, writer);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}