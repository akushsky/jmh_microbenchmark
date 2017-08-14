package com.amdocs.ch.events;

import java.io.Serializable;

/**
 * Generic event attribute
 * 
 * @author YANG (2012)
 */
public class EventAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	private String key;
	private String value;

	public EventAttribute() {
	}

	public EventAttribute(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public EventAttribute(EventAttribute o) {
		this.key = o.key;
		this.value = o.value;
	}

	/**
	 * @return attribute key
	 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return attribute value
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EventAttribute)) {
			return false;
		}
		EventAttribute test = (EventAttribute) obj;
		if (this.key != null && this.value != null && test.key != null && test.value != null
				&& this.key.equals(test.key) && this.value.equals(test.value)) {
			return true;
		}
		return false;
	}

}
