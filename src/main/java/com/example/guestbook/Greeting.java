package com.example.guestbook;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;

import java.lang.String;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * The @Entity tells Objectify about our entity.  We also register it in {@link OfyHelper}
 * Our primary key @Id is set automatically by the Google Datastore for us.
 *
 * We add a @Parent to tell the object about its ancestor. We are doing this to support many
 * guestbooks.  Objectify, unlike the AppEngine library requires that you specify the fields you
 * want to index using @Index.  Only indexing the fields you need can lead to substantial gains in
 * performance -- though if not indexing your data from the start will require indexing it later.
 *
 * NOTE - all the properties are PUBLIC so that can keep the code simple.
 **/
@Entity
@XmlRootElement(name="greeting")
@XmlAccessorType(XmlAccessType.FIELD)
public class Greeting {

  @Parent Key<Guestbook> theBook;
  
  @XmlElement
  @Id @Index public Long id;

  @XmlElement
  @Index public String author_email;
  @XmlElement
  public String author_id;
  @XmlElement
  public String content;
  @XmlElement
  @Index public Date date;

  /**
   * Simple constructor just sets the date
   **/
  public Greeting() {
    date = new Date();
  }

  /**
   * A connivence constructor
   **/
  public Greeting(String book, String content) {
    this();
    if( book != null ) {
      theBook = Key.create(Guestbook.class, book);  // Creating the Ancestor key
    } else {
      theBook = Key.create(Guestbook.class, "default");
    }
    this.content = content;
  }

  /**
   * Takes all important fields
   **/
  public Greeting(String book, String content, String id, String email) {
    this(book, content);
    author_email = email;
    author_id = id;
  }
  
  public Greeting(String book, long id, String content, String author_id, String email) {
	    this(book, content, author_id, email);
	    this.id = id;
	  }
  
  public String stringRep() {
	  return "Id: " + this.id + "; email: " + this.author_email + "; author: " + this.author_id + "; content: " + this.content + "; date: " + this.date.toString();
  
  }

}