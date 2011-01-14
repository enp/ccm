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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="calls")
public class Call implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="system_id",length=100,unique=true)
	private String systemId;

	@Column(name="source",length=25)
	private String source;

	@Column(name="destination",length=25)
	private String destination;

	@Column(name="fifo_name",length=10)
	private String fifoName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="connect_time")
	private Date connectTime;

	@Column(name="user_name",length=10)
	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="answer_time")
	private Date answerTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="hangup_time")
	private Date hangupTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="abort_time")
	private Date abortTime;

	@OneToMany(mappedBy="call",cascade=CascadeType.ALL)
	@OnDelete(action=OnDeleteAction.CASCADE)
	List<CallFail> fails = new ArrayList<CallFail>();

	public Call() {}

	public Call(String systemId, String source, String destination, String fifoName) {
		this.systemId = systemId;
		this.source = source;
		this.destination = destination;
		this.fifoName = fifoName;
		this.connectTime = new Date();
	}
	
	public void answer(String userName) {
		if (this.userName == null) {
			this.userName = userName;
			this.answerTime = new Date();
		}
	}

	public void hangup() {
		this.hangupTime = new Date();
	}

	public void abort() {
		this.abortTime = new Date();
	}

	public void fail(String userName, String reason) {
		fails.add(new CallFail(this, userName, reason));
	}

	public String toString() {
		return "Call { "+id+" : "+ systemId +" : "+ userName +" }";
	}

	public String getDestination() {
		return destination;
	}

	public String getSource() {
		return source;
	}
}
