# orchestration-service

This is a service which is a part of microservice project where it connects to other service (via http call) and get some output ffom those service.
there are 2 end points 


(1) /api/status  (GET API)
     this returns a string UP
(2) /api/process (POST API)
     Accepts  payload of name and surname and concate them by calling to other services and return the concatinated string.
    
