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
@Table(name="call_fails")
public class CallFail implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@ManyToOne
	@JoinColumn(name="call_id")
	private Call call;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fail_time")
	private Date failTime;

	@Column(name="user_name",length=10)
	private String userName;

	@Column(name="reason",length=25)
	private String reason;

	public CallFail() {}

	public CallFail(Call call, String userName, String reason) {
		this.call = call;
		this.userName = userName;
		this.reason = reason;
		this.failTime = new Date();
	}

	public String toString() {
		return "CallFail { "+id+" : "+ userName +" : "+ reason +" }";
	}

}
