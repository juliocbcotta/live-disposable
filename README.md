# live-disposable
Sometimes you need to use `LiveData.observeForever`, but this is a terrible method name, so use the `LiveData.subscribe` together `LiveCompositeDisposable.add`and `LiveCompositeDisposable.clear` just like with RxJava.

NOTE: `LiveData.subscribe` will ignoe null emission values.
