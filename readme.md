```plantuml
Client -> Proxy : method1()
activate Proxy

Proxy -> MethodRequest : canExecute()
Proxy <-- MethodRequest

Proxy -> ExecutorService : submit()
activate ExecutorService
Proxy <-- ExecutorService
deactivate ExecutorService

Client <-- Proxy : Future
deactivate Proxy

...

ExecutorService -> ExecutorService
activate ExecutorService
activate MethodRequest
ExecutorService -> MethodRequest : execute() 

MethodRequest -> ActionServant :  .   action()
deactivate MethodRequest

ExecutorService --> Client : complete future
deactivate ExecutorService
```