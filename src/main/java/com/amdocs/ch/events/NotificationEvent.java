package com.amdocs.ch.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Generic notification event. Vendor id is required parameter.
 * 
 * @author YANG (2012)
 */
@XmlRootElement(name = "event")
public class NotificationEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	private String vendor;
	private String gateway;
	private String mprm;
	private String device;
	private String eventId;
	private String type;
	private String user;
	private EventSeverity severity;
	private long timestamp;
	private Map<String, String> properties;

	public NotificationEvent() {
		properties = new TreeMap<>();
	}

	public NotificationEvent(NotificationEvent o) {
		this();
		this.vendor = o.vendor;
		this.gateway = o.gateway;
		this.mprm = o.mprm;
		this.device = o.device;
		this.eventId = o.eventId;
		this.type = o.type;
		this.user = o.user;
		this.severity = o.severity;
		this.timestamp = o.timestamp;
		if (o.getProperties() != null) {
			properties.putAll(o.getProperties());
		} else {
			properties = null;
		}
	}

	/**
	 * @return vendor unique identifier
	 */
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return gateway unique identifier
	 */
	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * @return mprm unique identifier
	 */
	public String getMprm() {
		return mprm;
	}

	public void setMprm(String mprm) {
		this.mprm = mprm;
	}

	/**
	 * @return gateway device unique identifier (serial)
	 */
	public String getDevice() {
		return device;
	}

	public void setDevice(String deviceId) {
		this.device = deviceId;
	}

	/**
	 * @return external event identifier
	 */
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return event type
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return event timestamp in milliseconds
	 */
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return user identifier (optional)
	 */
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return event severity
	 */
	public EventSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(EventSeverity severity) {
		this.severity = severity;
	}

	/**
	 * @return event properties Map
	 */
	@XmlTransient
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * Set event properties Map
	 * 
	 * @param properties
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	/**
	 * @return event attributes
	 * Needed for MPRM events backward compatibility - should not be JSON ignored !!!
	 */
	@XmlElementWrapper(name = "attributes")
	@XmlElement(name = "attribute")
	public List<EventAttribute> getAttributes() {
		if (properties != null) {
			List<EventAttribute> eventAttributes = new ArrayList<EventAttribute>();
			for (Map.Entry<String, String> entry : properties.entrySet()) {
				EventAttribute eventAttribute = new EventAttribute(entry.getKey(), entry.getValue());
				eventAttributes.add(eventAttribute);
			}
			return eventAttributes;
		}
		return null;
	}

	/**
	 * Set event attributes
	 * 
	 * @param evetAttributes
	 */
	public void setAttributes(List<EventAttribute> evetAttributes) {
		if (evetAttributes != null) {
			for (EventAttribute evetAttribute : evetAttributes) {
				addAttribute(evetAttribute);
			}
		} else {
			properties = null;
		}
	}

	/**
	 * Add attribute
	 * 
	 * @param attribute
	 */
	public synchronized void addAttribute(EventAttribute attribute) {
		if (properties == null) {
			properties = new TreeMap<String, String>();
		}
		if (attribute != null) {
			properties.put(attribute.getKey(), attribute.getValue());
		}
	}

	/**
	 * Add attribute
	 * 
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key, String value) {
		addAttribute(new EventAttribute(key, value));
	}

	/**
	 * Get attribute value
	 * 
	 * @param key
	 * @return
	 */
	public String getAttributeValue(String key) {
		if (key == null || properties == null) {
			return null;
		} else {
			return properties.get(key);
		}
	}

	/**
	 * Find attributes
	 * 
	 * @param key
	 * @return
	 */
	public EventAttribute findAttribute(String key) {
		if (properties == null) {
			return null;
		}
		if (key != null && properties.containsKey(key)) {
			return new EventAttribute(key, properties.get(key));
		}
		return null;
	}

	public List<EventAttribute> findAttributesByPrefix(String keyPrefix) {
		List<EventAttribute> result = null;
		if (keyPrefix != null && !keyPrefix.isEmpty() && properties != null && !properties.isEmpty()) {
			for (Map.Entry<String, String> entry : properties.entrySet()) {
				if (entry.getKey() != null && entry.getKey().startsWith(keyPrefix)) {
					EventAttribute eventAttribute = new EventAttribute(entry.getKey(), entry.getValue());
					if (result == null) {
						result = new ArrayList<EventAttribute>();
					}
					result.add(eventAttribute);
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("vendor=" + vendor);
		if (gateway != null) {
			sb.append(", gateway=" + gateway);
		}
		if (mprm != null) {
			sb.append(", mprm=" + mprm);
		}
		if (device != null) {
			sb.append(", device=" + device);
		}
		if (severity != null) {
			sb.append(", severity=" + severity);
		}
		if (eventId != null) {
			sb.append(", eventId=" + eventId);
		}
		if (type != null) {
			sb.append(", type=" + type);
		}
		if (user != null) {
			sb.append(", user=" + user);
		}
		if (timestamp > 0) {
			sb.append(", timestamp=" + timestamp);
		}
		if (properties != null) {
			sb.append(", properties=" + properties);
		}
		return sb.toString();
	}
}
