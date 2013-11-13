/**
 * 
 */
package singh.jatinder.executor;

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
