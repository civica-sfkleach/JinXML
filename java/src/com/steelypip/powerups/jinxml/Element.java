package com.steelypip.powerups.jinxml;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.jdt.annotation.NonNull;

import com.steelypip.powerups.util.multimap.MultiMap;

public interface Element {
	
	/**
	 * Returns true if the element will refuse modifications.
	 * @return
	 */
	boolean isFrozen();

	/**
	 * Returns a frozen element that compares as equal with the subject. It is not
	 * guaranteed to be newly allocated but it is guaranteed to leave the subject unaffected.
	 * Identical to .freeze( false )
	 * @return frozen element that is not identical to the subject (but may not be new). 
	 */
	default Element freeze() {
		return this.freeze( false );
	}
	
	/**
	 * Returns a frozen element that compares as equal with the subject. If the
	 * element is already frozen it always returns itself. If the returnSelf flag
	 * is true, the element freezes itself then returns itself. If returnSelf is
	 * false then a non-identical element is returned - typically a frozen copy. 
	 * @param returnSelf flag to indicate whether the returned value must be the subject or different.
	 * @return a frozen element that is equal to the subject 
	 */
	Element freeze( boolean returnSelf );
	
	/**
	 * returns the name of the object, which is typically intended to be a type-name.
	 * @return the name of the object
	 */
	String getName();
	
	/**
	 * Sets the element name to a non-empty string.
	 * @param _name the new name, which may not be null
	 */
	void setName( String _name );

	
	/**
	 * returns the total number of key-value attributes, including duplicates.
	 * @return number of key-value attributes
	 */
	int countAttributes();

	/**
	 * returns an iterator over the attribute entries of an element
	 * @return the iterator
	 */
	Iterator< Entry< String, String > > getAttributesIterator();

	
	/**
	 * Sets the attributes of an element sequentially to the entries of a multi-map
	 * preserving the order.
	 * @param attributes the multi-map containing the entries to add
	 */
	void setAttributes( MultiMap< String, String > attributes );
	
	/**
	 * Sets the attributes of an element to the values supplied  - all sharing
	 * the same key. Any previous attributes are removed.
	 * @param key the shared key
	 * @param values a set of values provided as an iterable
	 */
	void setValues( String key, Iterable< String > values );
	
	/**
	 * Sets the first attribute of an element with the given key to the given value
	 * the same key. 
	 * @param key the shared key
	 * @param value the value to add
	 */
	void setValue( String key, String value );
	
	//	TODO
	void setValue( String key, int position, String value );

	
	
	/**
	 * Adds an entry to the attributes of an element.
	 * @param key key of the entry being added
	 * @param value value of the entry being added
	 */
	void addLastValue( String key, String value );
	
	/**
	 * Same as getAttributesAsMultiMap( false, false )
	 * @return 
	 */
	MultiMap< String, String > getAttributesAsMultiMap();
	/**
	 * Same as getAttributesAsMultiMap( false, mutable )
	 * @param mutable a flag indicating if the result is mutable or not
	 * @return a multi-map based on the attributes of the element 
	 */
	MultiMap< String, String > getAttributesAsMultiMap( boolean mutable );
	/**
	 * returns a multi-map representing the attributes of the object. 
	 * If view is true then the multi-map is a mutable view onto the attributes 
	 * and changes to the multi-map immediately affect the attributes. If view 
	 * is false then the list is a copy. The result is mutable or immutable 
	 * depending on the value of mutable
	 * @param view a flag indicating if the result is a view on the Element or a clean copy
	 * @param mutable a flag indicating if the result is mutable or not
	 * @return a multi-map based on the attributes of the element
	 */
	MultiMap< String, String > getAttributesAsMultiMap( boolean view, boolean mutable );

	/**
	 * Gets the first attribute value with given key , if such an attribute
	 * exists. If not, it returns null.  
	 * @param key the key of the attribute entry to filter by
	 * @return the attribute value
	 */
	String getValue( @NonNull String key );
	
	/**
	 * Gets the first attribute value with given key , if such an attribute
	 * exists. If not, it returns the fallack value otherwise.  
	 * @param key the key of the attribute entry to filter by
	 * @param otherwise fallback value	 
	 * @return the attribute value
	 */
	String getValue( @NonNull String key, String otherwise );
	
	/**
	 * Gets the attribute value with given key at a given position, if such an attribute
	 * exists. If not, it returns null.  
	 * @param key the key of the attribute entry to filter by
	 * @param position attributes with the same key are ordered
	 * @return the attribute value
	 */
	String getValue( @NonNull String key, int position );
	
	/**
	 * Gets the attribute value with given key at a given position, if such an attribute
	 * exists. If not, it returns the fallack value otherwise.  
	 * @param key the key of the attribute entry to filter by
	 * @param position attributes with the same key are ordered
	 * @param otherwise fallback value
	 * @return the attribute value
	 */
	String getValue( @NonNull String key, int position, String otherwise );
	
	/**
	 * Gets the attribute value with given key at a given position, if such an attribute
	 * exists. If not, it returns the fallack value otherwise.  
	 * @param key the key of the attribute entry to filter by
	 * @param reverse if true then the position indexes from the last entry, not the first
	 * @param position attributes with the same key are ordered
	 * @param otherwise fallback value
	 * @return the attribute value
	 */
	String getValue( @NonNull String key, boolean reverse, int position, String otherwise );
	
	/**
	 * Gets the first attribute value with given key, if such an attribute
	 * exists. If not, it returns the fallack value otherwise.  
	 * @param key the key of the attribute entry to filter by
	 * @param otherwise fallback value
	 * @return the attribute value
	 */
	String getFirstValue( @NonNull String key );

	/**
	 * Gets the first attribute value with given key, if such an attribute
	 * exists. If not, it returns null.  
	 * @param key the key of the attribute entry to filter by
	 * @return the attribute value
	 */
	String getFirstValue( @NonNull String key, String otherwise );
	
	/**
	 * Gets the last attribute value with given key, if such an attribute
	 * exists. If not, it returns the fallack value otherwise.  
	 * @param key the key of the attribute entry to filter by
	 * @param otherwise fallback value
	 * @return the attribute value
	 */
	String getLastValue( @NonNull String key );

	/**
	 * Gets the last attribute value with given key, if such an attribute
	 * exists. If not, it returns null.  
	 * @param key the key of the attribute entry to filter by
	 * @return the attribute value
	 */
	String getLastValue( @NonNull String key, String otherwise );
	
	/**
	 * Retunrs the number of entries for a given key 
	 * @param key
	 * @return
	 */
	int countValues( @NonNull String key );
	
	//Method getValuesAsList( String key, Boolean view = false, Boolean mutable = false ) -> List< String > - returns all the values that are in maplets with key key. If view is true then the multi-map is a mutable view onto the attributes and changes to the multi-map immediately affect the attributes. If view is false then the list is a copy. The result is mutable or immutable depending on the value of mutable.

	/**
	 * Given a key, return an ordered list of the values of entries with matching key. 
	 * @param key the given key
	 * @param view a flag indicating whether the list is a dynamic view on the entries of the element
	 * @param mutable a flag indicating whether the returned list supports update operations
	 * @return the ordered list
	 */
	List< String > getValuesAsList( @NonNull String key, boolean view, boolean mutable );

	/**
	 * Given a key, return an immutable ordered list of the values of entries with matching key.
	 * The list that is returned is independent of the element. Same as .getValuesAsList( key, false, false ) 
	 * @param key the given key
	 * @return the ordered list
	 */
	List< String > getValuesAsList( @NonNull String key );

	//	TODO:
	int countMembers();
	
	//	TODO:
	int countChildren( @NonNull String selector  ); 
	
	//	TODO:
	void addLastChild( @NonNull String selector, @NonNull Element e );

	//Method getMembersAsMultiMap() -> MultiMap< String, Element > - returns a multi-map representing the members of the object. If view is true then the multi-map is a mutable view onto the members and changes to the multi-map immediately affect the membrs. If view is false then the list is a copy. The result is mutable or immutable depending on the value of mutable.
	/**
	 * Same as getMembersAsMultiMap( false, false )
	 * @return 
	 */
	MultiMap< String, Element > getMembersAsMultiMap();
	/**
	 * Same as getMembersAsMultiMap( false, mutable )
	 * @param mutable a flag indicating if the result is mutable or not
	 * @return a multi-map based on the attributes of the element 
	 */
	MultiMap< String, Element > getMembersAsMultiMap( boolean mutable );
	/**
	 * returns a multi-map representing the members of the object. 
	 * If view is true then the multi-map is a mutable view onto the attributes 
	 * and changes to the multi-map immediately affect the attributes. If view 
	 * is false then the list is a copy. The result is mutable or immutable 
	 * depending on the value of mutable
	 * @param view a flag indicating if the result is a view on the Element or a clean copy
	 * @param mutable a flag indicating if the result is mutable or not
	 * @return a multi-map based on the attributes of the element
	 */
	MultiMap< String, Element > getMembersAsMultiMap( boolean view, boolean mutable );	

	/**
	 * returns an iterator over the member entries of an element
	 * @return the iterator
	 */
	Iterator< Entry< String, Element > > getMembersIterator();

	/**
	 * Gets the child from member with given selector at a given position, if such an member
	 * exists. If not, it returns the fallack value otherwise.  
	 * @param selector the selector of the member entry to filter by
	 * @param reverse if true then the position indexes from the last entry, not the first
	 * @param position members with the same key are ordered
	 * @param otherwise fallback value
	 * @return the child
	 */
	Element getChild( @NonNull String selector, boolean reverse, int position, Element otherwise );
		
	/**
	 * Gets the first child from member with given selector, if such an member
	 * exists. If not, it returns the fallack value otherwise.  
	 * @param selector the selector of the member entry to filter by
	 * @param otherwise fallback value
	 * @return the child
	 */
	Element getChild( @NonNull String selector, Element otherwise );
	
	/**
	 * Gets the child from member with given selector at a given position, if such an member
	 * exists. If not, it returns null.  
	 * @param selector the selector of the member entry to filter by
	 * @param position members with the same key are ordered
	 * @return the child or null
	 */
	Element getChild( @NonNull String selector, int position );
	
	/**
	 * Gets the first child from member with the empty selector, if such an member
	 * exists. If not, it returns the fallack value otherwise  
	 * @param otherwise fallback value
	 * @return the child
	 */
	Element getChild( Element otherwise );

	/**
	 * Gets the child from members with the empty selector at a given position, if such an member
	 * exists. If not, it returns null.  
	 * @param position members with the same key are ordered
	 * @return the child or null
	 */
	Element getChild( int position );

	/**
	 * Gets the child from first members with the empty selector, if such an member
	 * exists. If not, it returns null. Equivalent to this.getFirstChild().
	 * @return the child or null
	 */	
	Element getChild();
	
	// Method getFirstChild( String sel, String? default = null ) -> Element? 

	Element getFirstChild( @NonNull String selector, Element otherwise );
	Element getFirstChild( Element otherwise );
	Element getFirstChild();
	
	// Method getLastChild String sel, String? default = null ) -> String? 
	Element getLastChild( @NonNull String selector, Element otherwise );
	Element getLastChild( Element otherwise );
	Element getLastChild();

	
/**
Method getChild( String sel = "", Int position = 0, Element? default = null ) -> Element? - returns the object associated with the maplet with selector sel and position position. If there is no such maplet then default is returned instead. If reverse is true then the position is taken to be countChildren( sel ) - 1 - position.

Method getFirstChild( String sel, String? default = null ) -> String? - returns the first child associated with the maplet with selector sel. If there is no such maplet then default is returned instead.

Method getLastChild String sel, String? default = null ) -> String? - returns the last child associated with the maplet with selector sel. If there is no such maplet then default is returned instead.

Method countChildren( String sel ) -> Int - returns the number of children that share the selector sel.

Method getChildrenAsMultiMap( Boolean view = false, Boolean mutable = false ) -> MultiMap< String, String > - returns a multi-map representing the children of the object. If view is true then the multi-map is a mutable view onto the children and changes to the multi-map immediately affect the children. If view is false then the list is a copy. The result is mutable or immutable depending on the value of mutable.

Method getChildrenAsList( String? key = "", Boolean view = false, Boolean mutable = false ) -> List< Element > - returns all the children that are in maplets with key key. If view is true then the multi-map is a mutable view onto the attributes and changes to the multi-map immediately affect the attributes. If view is false then the list is a copy. The result is mutable or immutable depending on the value of mutable.

Method isIntValue() -> Boolean - returns true if the object represents an integer. This test is only required to check the name of the object.

Method getIntValue( Int? default = null ) -> Int? - if the object represents an integer then this returns its integral value. If it is not an integer, the default should be returned. N.B. There is no size limit on integers in JSON and conforming implementations are required to support arbitrary-width integers. The majority of programming languages give small width integers special significance and they should provide special variants of getIntValue (e.g. getInt32Value, getInt64Value) for that purpose. If the integer value is out of range, returning the default value is an acceptable action in these variants.

Method isFloatValue( Boolean strict = false ) -> Boolean - returns true if the object represents a floating-point number. This test is only required to check the name of the object. If strict is false then integral values will return true for this test.

Method getFloatValue( Boolean strict = false, Float? default = null ) -> Float? - if the object represents an float then this returns its value using the common representation for the host programming language. This will typically be double precision on today's machines. Libraries may provide variants (e.g. getFloat32Value, getFloatDoubleValue()) at their own discretion. Note that floating point numbers in the syntax have arbitrary precision but there is no requirement to retain this exact precision. If the value is not a Float then implementation should return the default value. If strict is false then integer values are allowed and automatically converted to a floating point value.

Method isStringValue() -> Boolean

Method getStringValue( String? default = null ) -> String?

Method isBooleanValue() -> Boolean

Method getBooleanValue( Boolean? default = null ) -> Boolean?

Method isNullValue() -> Boolean

Method getNullValue( Null? default = null ) -> Null?

Mutators Methods
Method setName( String name )

Method setAttributes( MultiMap< String, String > attributes )

Methid setValues( String key, Iterable< String > )

Method setValue( String key, Boolean reverse = false, Int position = 0, String value )

Method addFirstValue( String key, String value )

Method addLastValue( String key, String value )

Method removeFirstValue( String key, String? value = null ) -> String?

Method removeLastValue( String key, String? value = null ) -> String?

Method setMembers( MultiMap< String, Element > members )

Method setChildren( String sel, Iterable< Element > )

Method setChild( String sel, Boolean reverse = false, Int position = 0, Element child )

Method addFirstChild( String sel, Element child )

Method addLastChild( String sel, Element child )

Method removeFirstChild( String sel, Element? value = null ) -> Element?

Method removeLastValue( String sel, Element? value = null ) -> Element?

Builder Related Methods
Method toEventIterator() -> Iterator< PushParser.Event > - generates a stream of events that performs an immune 'walk' over the Element. The walk is insensitive in the sense that the stream it returns is immune to all updates to the Element that might occur while the walk is in-progress.
*/

}
