package com.steelypip.powerups.util.phoenixmultimap.mutable;

import java.util.Iterator;

import com.steelypip.powerups.util.phoenixmultimap.PhoenixMultiMap;
import com.steelypip.powerups.util.phoenixmultimap.frozen.EmptyFrozenPMMap;

public class EmptyMutablePMMap< K, V > extends AbsEmptyMutablePMMap< K, V > {
	
	public static EmptyMutablePMMap< Object, Object > INSTANCE = new EmptyMutablePMMap< Object, Object >();
	
	@SuppressWarnings("unchecked")
	public static < K, V > EmptyMutablePMMap< K, V > getInstance() { return (EmptyMutablePMMap< K, V >) INSTANCE; }

	@Override
	public EmptyMutablePMMap< K, V > clearAllEntries() {
		return this;
	}


	@Override
	public SingleEntryMutablePMMap< K, V > add( K key, V value ) {
		return new SingleEntryMutablePMMap< K, V >( key, value );
	}

	@Override
	public PhoenixMultiMap< K, V > addAll( K key, Iterable< ? extends V > values ) {
		final Iterator< ? extends V > it = values.iterator();
		if ( ! it.hasNext() ) return this;
		V value = it.next();
		if ( it.hasNext() ) {
			return new SharedKeyMutablePMMap< K, V >( key ).add( value ).addAll( values );
		} else {
			return new SingleEntryMutablePMMap< K, V >( key, value );
		}
	}

	@Override
	public EmptyMutablePMMap< K, V > removeEntry( Object key, Object value ) {
		return this;
	}

	@Override
	public EmptyMutablePMMap< K, V > removeEntryAt( Object key, int N ) {
		return this;
	}

	@Override
	public EmptyMutablePMMap< K, V > removeEntries( Object key ) {
		return this;
	}

	@Override
	public PhoenixMultiMap< K, V > setValues( K key, Iterable< ? extends V > values ) {
		return this.addAll( key, values );
	}

	@Override
	public PhoenixMultiMap< K, V > setSingletonValue( K key, V value ) {
		return new SingleEntryMutablePMMap< K, V >( key, value );
	}

	@Override
	public PhoenixMultiMap< K, V > updateValue( K key, int n, V value ) throws IllegalArgumentException {
		throw new IllegalArgumentException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PhoenixMultiMap< K, V > freezeByMutation() {
		return EmptyFrozenPMMap.INSTANCE;
	}

	
	
}
