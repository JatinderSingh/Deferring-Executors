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
package singh.jatinder.executor;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.stumbleupon.async.Deferred;

/**
 * @author Jatinder
 *
 */
public class DeferringExecutorService {
	private final ExecutorService delegate;
	public DeferringExecutorService(ExecutorService executorService) {
		delegate = executorService;
	}
	
	public <V> Deferred<V> execute(Callable<V> callable) {
		Deferred<V> deferred = new Deferred<>();
		DeferrableFutureTask<V> dft = new DeferrableFutureTask<V>(callable, deferred);
		delegate.execute(dft);
		return deferred;
	}
	
	public void shutdown() {
		delegate.shutdown();
	}

	public List<Runnable> shutdownNow() {
		return delegate.shutdownNow();
	}

	public boolean isShutdown() {
		return delegate.isShutdown();
	}

	public boolean isTerminated() {
		return delegate.isTerminated();
	}

	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return delegate.awaitTermination(timeout, unit);
	}
}
