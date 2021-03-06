/*
 * Copyright (c) 2010 Eugene Prokopiev <enp@itx.ru>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.itx.ccm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="sessions")
public class Session implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="system_id",length=100,unique=true)
	private String systemId;
	
	@Column(name="user_name",length=10)
	private String userName;
	
	@Column(name="user_agent",length=25)
	private String userAgent;

	@Column(name="host",length=15)
	private String host;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="connect_time")
	private Date connectTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="disconnect_time")
	private Date disconnectTime;

	public Session() {}

	public Session(String systemId, String userName, String userAgent, String host) {
		this.systemId = systemId;
		this.userName = userName;
		this.userAgent = userAgent;
		this.host = host;
		this.connectTime = new Date();
	}
	
	public void disconnect() {
		this.disconnectTime = new Date();
	}
	public String toString() {
		return "Session { "+id+" : "+ systemId +" : "+ userName +" }";
	}

	public String getUserName() {
		return userName;
	}
}
