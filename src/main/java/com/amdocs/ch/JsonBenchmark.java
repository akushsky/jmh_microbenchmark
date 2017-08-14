/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.amdocs.ch;

import com.amdocs.ch.events.NotificationEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Measurement(iterations = 10, time = 3)
@Warmup(iterations = 5, time = 1)
@Timeout(time = 10, timeUnit = TimeUnit.MINUTES)
@Threads(16)
public class JsonBenchmark {

	@State(Scope.Benchmark)
	public static class MyState {
		String message = "{\"properties\":{\"ch.security\":\"true\",\"state.event.device.uid\":\"hdm:tyco:TYCO_SECURITY_PANEL/1\",\"state.event.property.value\":\"true\",\"locationIdsRequest\":\"true\",\"device.timestamp.added\":\"1463402030916\",\"deviceClasses\":\"com.prosyst.mbs.services.hdm.deviceclasses.BinarySensor,com.prosyst.mbs.services.hdm.deviceclasses.Key\",\"eventProtocol\":\"Simulator\",\"state.event.property.name\":\"sendAllPropertiesStateEvent\",\"isParent\":\"true\",\"eventSource\":\"syncHdmDevice\",\"sendAllPropertiesStateEvent\":\"true\",\"type\":\"tyco_powermaster360_security_panel\",\"device.firmware.version\":\"P-Max: \\\"JS703296 L19.412\\\", P-Default: \\\"J-703302 L19.412\\\", P-Link: \\\"8.0.92.3\\\"-\\\"raw\\\", EEPROM: 13.150\",\"device.external.serial\":\"13DFE5\"},\"gateway\":\"1116308256\",\"timestamp\":1502690866119,\"device\":\"hdm:tyco:TYCO_SECURITY_PANEL/1\",\"vendor\":\"PROSYST\",\"type\":\"DEVICE_STATE_CHANGED\",\"severity\":\"INFO\", \"gateway\":\"1116308256\"}";

		// POJO to JSON mapper
		ObjectMapper jsonObjectMapper = new ObjectMapper();
	}

	@Benchmark
	public NotificationEvent jsoniterTest(MyState state) {
		// place your benchmarked code here
		return JsonIterator.deserialize(state.message, NotificationEvent.class);
	}

	@Benchmark
	public NotificationEvent jacksonTest(MyState state) throws IOException {
		// place your benchmarked code here
		return state.jsonObjectMapper.readValue(state.message, NotificationEvent.class);
	}
}
