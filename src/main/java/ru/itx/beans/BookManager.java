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

package ru.itx.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BookManager {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
    private EntityManager em;

    public List<Book> getBooks() {
		return (List<Book>)em.createQuery("select b from Book b").getResultList();
	}
	
	public Book createBook(String name, String author, Date date) {
        Book book = new Book(name, author, date);
		em.persist(book);
        return book;
    }

}