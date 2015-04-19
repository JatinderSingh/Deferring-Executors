/*
 * 
 * This file is part of Deferring-Executors
 * Copyright (C) 2013  Jatinder
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this library; If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package io.maelstorm.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.stumbleupon.async.Deferred;


/**
 * @author Jatinder
 *
 */
public class DeferrableFutureTask<V> extends FutureTask<V> {

	private final Deferred<V> deferred;
	public DeferrableFutureTask(Callable<V> callable, Deferred<V> deferred) {
		super(callable);
		this.deferred = deferred;
	}
	
	@Override
	protected void done() {
		Object result;
		try {
			result = super.get();
		} catch (InterruptedException | ExecutionException e) {
			result = e;
		}
		deferred.callback(result);
	 }
}
