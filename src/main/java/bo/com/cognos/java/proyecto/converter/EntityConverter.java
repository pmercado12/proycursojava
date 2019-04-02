package bo.com.cognos.java.proyecto.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="entityConverter")
public class EntityConverter implements Converter {

	private static Map<Object, String> items = new WeakHashMap<Object, String>();
	
	public Object getAsObject(FacesContext context, UIComponent component, 
			String uuid) {
		for (Entry<Object, String> entry : items.entrySet()) {
            if (entry.getValue().equals(uuid)) {
            	System.out.println("Encontró objeto: " + entry.getKey() + " para el uuid "+ uuid);
				return entry.getKey();
            }
        }
		System.out.println("No encontró objeto para el uuid: " + uuid);
        return null;
	}

	public String getAsString(FacesContext context, UIComponent component, 
			Object value) {
		synchronized (items) {
            if (!items.containsKey(value)) {
                String uuid = UUID.randomUUID().toString();
                items.put(value, uuid);
                System.out.println("Retorna el uuid " + uuid + " para el objeto " + value);
				return uuid;
            } else {
            	System.out.println("Retorna el uuid " + items.get(value) + " para el objeto " + value);
				return items.get(value);
            }
        }
	}
	
}
