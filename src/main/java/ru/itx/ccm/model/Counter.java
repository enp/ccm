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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="counters")
public class Counter implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="count_time")
	private Date countTime;

	@Column(name="fifo",length=5)
	private String fifo;

	@Column(name="members")
	private int members;

	@Column(name="active_members")
	private int activeMembers;

	@Column(name="callers")
	private int callers;

	@Column(name="bridges")
	private int bridges;

	public Counter() {}

	public Counter(String fifo, int members, int activeMembers, int callers, int bridges) {
		this.fifo = fifo;
		this.members = members;
		this.activeMembers = activeMembers;
		this.callers = callers;
		this.bridges = bridges;
		this.countTime = new Date();
	}

	public String toString() {
		return "Counter { "+id+" : "+fifo+" : "+" : "+members+" : "+activeMembers+" : "+callers+" : "+bridges+" }";
	}

}
